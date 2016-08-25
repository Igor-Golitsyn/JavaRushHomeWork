package com.javarush.test.level18.lesson03.task02;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String file=reader.readLine();
        reader.close();
        FileInputStream inputStream=new FileInputStream(file);
        int min=inputStream.read();
        while (inputStream.available()>0){
            int temp=inputStream.read();
            if (min>temp)min=temp;
        }
        inputStream.close();
        System.out.println(min);
    }
}
