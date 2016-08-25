package com.javarush.test.level30.lesson15.big01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by golit on 21.03.2016.
 */
public class ConsoleHelper {
    private static BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString(){
        String line=new String();
        boolean error;
        do {
            try {
                line=bufferedReader.readLine();
                error=false;
            }catch (IOException e){
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                error=true;
            }
        }
        while (error);
        return line;
    }
    public static int readInt(){
        int number=0;
        boolean error;
        do {
            try {
                number=Integer.parseInt(readString());
                error=false;
            }
            catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                error=true;
            }
        }
        while (error);
        return number;
    }
}
