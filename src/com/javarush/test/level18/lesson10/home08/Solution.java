package com.javarush.test.level18.lesson10.home08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Не забудьте закрыть все потоки
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true){
                String file=reader.readLine();
                if (file.equals("exit")){
                    reader.close();
                    break;
                }
                new ReadThread(file).start();
            }
        } catch (IOException e) {
        }
    }

    public static class ReadThread extends Thread {
        private String file=null;
        public ReadThread(String fileName) {
            //implement constructor body
            this.file=fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            try {
                Map<Integer,Integer> map=new HashMap<>();
                FileInputStream fis=new FileInputStream(file);
                Integer maxVal=1;
                Integer tempVal=0;
                Integer tempKey=0;
                while (fis.available()>0){
                    tempKey=fis.read();
                    if (map.containsKey(tempKey)){
                        tempVal=map.get(tempKey);
                        tempVal++;
                        if (maxVal<tempVal)maxVal=tempVal;
                        map.put(tempKey,tempVal);
                    }else{
                        map.put(tempKey,1);
                    }
                }
                Set set=map.entrySet();
                Iterator iterator=set.iterator();
                while (iterator.hasNext()){
                    Map.Entry entry= (Map.Entry) iterator.next();
                    if (entry.getValue()==maxVal)resultMap.put(file,(Integer)entry.getKey());
                }
            } catch (IOException e) {

            }
        }
    }
}
