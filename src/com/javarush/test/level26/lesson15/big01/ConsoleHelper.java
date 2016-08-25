package com.javarush.test.level26.lesson15.big01;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
/**
 * Created by Игорь on 15.12.2015.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static String readString() throws InterruptOperationException {
        String line = new String();
        try {
            line = reader.readLine();
        }
        catch (IOException e) {
        }
        if (line.toLowerCase().equals("exit")) throw new InterruptOperationException();
        return line;
    }
    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String line = readString();
            if ((line.length() == 3) && (line.matches("[A-Za-z]*"))) {
                return line.toUpperCase();
            } else {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String line = readString();
            String[] lines = line.split(" ");
            if (lines.length > 2 || lines.length < 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            boolean normData = true;
            for (String ll : lines) {
                try {
                    if (Integer.parseInt(ll) < 0) normData = false;
                }
                catch (NumberFormatException e) {
                    normData = false;
                }
            }
            if (normData) return lines;
            else writeMessage(res.getString("invalid.data"));
        }
    }
    public static Operation askOperation() throws InterruptOperationException {
        int num;
        Operation selectedOperation;
        while (true) {
            try {
                writeMessage(res.getString("choose.operation"));
                num = Integer.parseInt(readString());
                selectedOperation = Operation.getAllowableOperationByOrdinal(num);
                break;
            }
            catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return selectedOperation;
    }
    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
