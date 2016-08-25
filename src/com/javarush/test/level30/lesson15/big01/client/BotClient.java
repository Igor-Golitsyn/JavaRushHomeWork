package com.javarush.test.level30.lesson15.big01.client;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Created by golit on 23.03.2016.
 */
public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }
    @Override
    protected String getUserName() {
        String nameBot = "date_bot_" + ThreadLocalRandom.current().nextInt(100);
        return nameBot;
    }
    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            Calendar calendar=Calendar.getInstance();
            ConsoleHelper.writeMessage(message);
            String nameFromMessage;
            String textFromMassage;
            if (message.contains(": ")) {
                nameFromMessage = message.split(": ", 2)[0];
                textFromMassage = message.split(": ", 2)[1];
                String answer= "Информация для %s: %s";
                switch (textFromMassage){
                    case "дата":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("d.MM.YYYY").format(calendar.getTime())));
                        break;
                    case "день":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("d").format(calendar.getTime())));
                        break;
                    case "месяц":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("MMMM").format(calendar.getTime())));
                        break;
                    case "год":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("YYYY").format(calendar.getTime())));
                        break;
                    case "время":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("H:mm:ss").format(calendar.getTime())));
                        break;
                    case "час":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("H").format(calendar.getTime())));
                        break;
                    case "минуты":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("m").format(calendar.getTime())));
                        break;
                    case "секунды":sendTextMessage(String.format(answer,nameFromMessage,new SimpleDateFormat("s").format(calendar.getTime())));
                        break;
                }
            }
        }
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
    public static void main(String[] args) {
        new BotClient().run();
    }
}
