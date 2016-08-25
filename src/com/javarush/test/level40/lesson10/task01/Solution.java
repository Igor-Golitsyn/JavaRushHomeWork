package com.javarush.test.level40.lesson10.task01;

/* Работа с датами
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }
    public static void printDate(String date) {
        //напишите тут ваш код
        int format = checkFormat(date);
        SimpleDateFormat sdf;
        Calendar calendar = Calendar.getInstance();
        try {
            switch (format) {
                case 0:
                    sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    calendar.setTime(sdf.parse(date));
                    System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println("День недели: " + calendar.get(Calendar.DAY_OF_WEEK));
                    System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
                    System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
                    System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
                    System.out.println("Месяц: " + calendar.get(Calendar.MONTH));
                    System.out.println("Год: " + calendar.get(Calendar.YEAR));
                    System.out.println("Эра: " + calendar.get(Calendar.ERA));
                    System.out.println("AM или PM: " + calendar.get(Calendar.AM_PM));
                    System.out.println("Часы: " + calendar.get(Calendar.HOUR));
                    System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
                    System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
                    System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
                    break;
                case 1:
                    sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendar.setTime(sdf.parse(date));
                    System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println("День недели: " + calendar.get(Calendar.DAY_OF_WEEK));
                    System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
                    System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
                    System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
                    System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
                    System.out.println("Месяц: " + calendar.get(Calendar.MONTH));
                    System.out.println("Год: " + calendar.get(Calendar.YEAR));
                    System.out.println("Эра: " + calendar.get(Calendar.ERA));
                    break;
                case 2:
                    sdf = new SimpleDateFormat("HH:mm:ss");
                    calendar.setTime(sdf.parse(date));
                    System.out.println("AM или PM: " + calendar.get(Calendar.AM_PM));
                    System.out.println("Часы: " + calendar.get(Calendar.HOUR));
                    System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
                    System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
                    System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
                    break;
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private static int checkFormat(String date) {
        if (!date.contains(":")) return 1;
        if (!date.contains(".")) return 2;
        return 0;
    }
}
