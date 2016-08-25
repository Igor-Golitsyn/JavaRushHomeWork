package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        Charset win1251=Charset.forName("Windows-1251");
        Charset utf8=Charset.forName("UTF-8");
        FileInputStream inputStream=new FileInputStream(args[0]);
        byte[] buff=new byte[inputStream.available()];
        inputStream.read(buff);
        inputStream.close();
        String s=new String(buff,utf8);
        buff=s.getBytes(win1251);
        s=new String(buff,utf8);
        FileOutputStream outputStream=new FileOutputStream(args[1]);
        outputStream.write(buff);
        outputStream.close();
    }
}
