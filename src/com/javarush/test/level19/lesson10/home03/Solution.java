package com.javarush.test.level19.lesson10.home03;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/
public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            while (reader.ready()) {
                int[] date = new int[3];
                String name = new String();
                String[] splitLine;
                Date birthDay;
                Calendar calendar = Calendar.getInstance();
                splitLine = reader.readLine().split(" ");
                int i = 0;
                for (String tt : splitLine) {
                    try {
                        date[i] = Integer.parseInt(tt);
                        i++;
                    } catch (NumberFormatException e) {
                        name += tt + " ";
                    }
                }
                name=name.substring(0,name.length()-1);
                calendar.set(date[2], date[1] - 1, date[0]);
                birthDay = calendar.getTime();
                PEOPLE.add(new Person(name, birthDay));
            }
            reader.close();
        } catch (IOException e) {
        }

    }
}
