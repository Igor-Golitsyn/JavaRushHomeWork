package com.javarush.test.level30.lesson15.big01.client;
import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;
/**
 * Created by Игорь on 23.03.2016.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Input server adress: ");
        return ConsoleHelper.readString();
    }
    protected int getServerPort() {
        ConsoleHelper.writeMessage("Input server port: ");
        return ConsoleHelper.readInt();
    }
    protected String getUserName() {
        ConsoleHelper.writeMessage("Input you name: ");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSentTextFromConsole() {return true;}
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
            clientConnected = false;
        }
    }
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                this.wait();
            }
            catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
                return;
            }
            if (clientConnected) {
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            } else {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
            while (clientConnected) {
                String textForMessage = ConsoleHelper.readString();
                if (textForMessage.equalsIgnoreCase("exit")) {break;}
                if (shouldSentTextFromConsole()) {sendTextMessage(textForMessage);}
            }
        }
    }
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("User " + userName + " edding to chat!");
        }
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("User " + userName + " out from chat!");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                switch (message.getType()) {
                    case NAME_REQUEST:
                        connection.send(new Message(MessageType.USER_NAME, getUserName()));
                        break;
                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (!isInterrupted()) {
                Message message = connection.receive();
                switch (message.getType()) {
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }
        @Override
        public void run() {
            try {
                Socket socket=new Socket(getServerAddress(),getServerPort());
                connection=new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException e) {
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
