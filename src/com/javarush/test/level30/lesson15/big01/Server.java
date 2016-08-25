package com.javarush.test.level30.lesson15.big01;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Created by golit on 21.03.2016.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message messageName;
            boolean wrongName = true;
            do {
                connection.send(new Message(MessageType.NAME_REQUEST));
                messageName = connection.receive();
                if (messageName.getType().equals(MessageType.USER_NAME) && !messageName.getData().isEmpty() && !connectionMap.containsKey(messageName.getData())) {
                    connectionMap.put(messageName.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    wrongName = false;
                }
            }
            while (wrongName);
            return messageName.getData();
        }
        @Override
        public void run() {
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            String errorMessage = "Произошла ошибка обмена данных с удаленным адресом: " + socketAddress;
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + socketAddress);
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(errorMessage);
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage(errorMessage);
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
            ConsoleHelper.writeMessage("Закрыто соединение с удаленным адресом: " + socketAddress);
        }
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message messageFromClient = connection.receive();
                if (messageFromClient.getType().equals(MessageType.TEXT)) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + messageFromClient.getData()));
                } else {
                    ConsoleHelper.writeMessage("ERROR");
                }
            }
        }
    }
    public static void sendBroadcastMessage(Message message) {
        for (String name : connectionMap.keySet()) {
            try {
                connectionMap.get(name).send(message);
            }
            catch (IOException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        ConsoleHelper.writeMessage("Server is RUN");
        while (true) {
            try {
                new Handler(serverSocket.accept()).start();
            }
            catch (Exception e) {
                e.printStackTrace();
                serverSocket.close();
                break;
            }
        }
    }
}
