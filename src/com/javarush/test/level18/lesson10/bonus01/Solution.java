package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/
import java.io.*;
public class Solution {
    public static void main(String[] args) {
        if (args[0].equals("-e")) incode(args[1], args[2]);
        if (args[0].equals("-d")) deCode(args[1], args[2]);
    }
    public static void incode(String inputFile, String outFile) {
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            FileWriter fw=new FileWriter(outFile);
            while (fis.available()>0){
                fw.write(13+fis.read()+" ");
            }
            fis.close();
            fw.close();
        } catch (IOException e) {
        }
    }
    public static void deCode (String inputFile, String outFile){
        try {
            BufferedReader reader=new BufferedReader(new FileReader(inputFile));
            FileOutputStream fos=new FileOutputStream(outFile);
            while (reader.ready()) {
                String[] splitString = reader.readLine().split(" ");
                for (String temp:splitString){
                    fos.write(Integer.parseInt(temp)-13);
                }
            }
            reader.close();
            fos.close();
        } catch (IOException e) {
        }
    }
}
