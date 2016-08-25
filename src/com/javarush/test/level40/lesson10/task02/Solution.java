package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter;
        DateTime dateTime;
        switch (checkFormat(date)) {
            case 0:
                formatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
                dateTime = formatter.parseDateTime(date);
                System.out.println("День: " + dateTime.dayOfMonth().get());
                System.out.println("День недели: " + (dateTime.dayOfWeek().get() + 1));
                System.out.println("День месяца: " + dateTime.dayOfMonth().get());
                System.out.println("День года: " + dateTime.dayOfYear().get());
                System.out.println("Неделя месяца: " + weekOfMonth(dateTime));
                System.out.println("Неделя года: " + weekOfYear(dateTime));
                System.out.println("Месяц: " + (dateTime.monthOfYear().get() - 1));
                System.out.println("Год: " + dateTime.year().get());
                System.out.println("Эра: " + dateTime.era().get());
                System.out.println("AM или PM: " + (dateTime.hourOfDay().get() > 12 ? 1 : 0));
                System.out.println("Часы: " + (dateTime.hourOfDay().get() > 12 ? dateTime.hourOfDay().get() - 12 : dateTime.hourOfDay().get()));
                System.out.println("Часы дня: " + dateTime.hourOfDay().get());
                System.out.println("Минуты: " + dateTime.minuteOfHour().get());
                System.out.println("Секунды: " + dateTime.secondOfMinute().get());
                break;
            case 1:
                formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
                dateTime = formatter.parseDateTime(date);
                System.out.println("День: " + dateTime.dayOfMonth().get());
                System.out.println("День недели: " + (dateTime.dayOfWeek().get() + 1));
                System.out.println("День месяца: " + dateTime.dayOfMonth().get());
                System.out.println("День года: " + dateTime.dayOfYear().get());
                System.out.println("Неделя месяца: " + weekOfMonth(dateTime));
                System.out.println("Неделя года: " + weekOfYear(dateTime));
                System.out.println("Месяц: " + (dateTime.monthOfYear().get() - 1));
                System.out.println("Год: " + dateTime.year().get());
                System.out.println("Эра: " + dateTime.era().get());
                break;
            case 2:
                formatter = DateTimeFormat.forPattern("HH:mm:ss");
                dateTime = formatter.parseDateTime(date);
                System.out.println("AM или PM: " + (dateTime.hourOfDay().get() > 12 ? 1 : 0));
                System.out.println("Часы: " + (dateTime.hourOfDay().get() > 12 ? dateTime.hourOfDay().get() - 12 : dateTime.hourOfDay().get()));
                System.out.println("Часы дня: " + dateTime.hourOfDay().get());
                System.out.println("Минуты: " + dateTime.minuteOfHour().get());
                System.out.println("Секунды: " + dateTime.secondOfMinute().get());
                break;
        }
    }
    private static int checkFormat(String date) {
        if (!date.contains(":")) return 1;
        if (!date.contains(".")) return 2;
        return 0;
    }
    private static int weekOfMonth(DateTime dateTime) {
        DateTime minYearDate = dateTime.dayOfYear().withMinimumValue();
        DateTime minMonthDate = dateTime.dayOfMonth().withMinimumValue();
        int weekDis = (minYearDate.getWeekyear() == dateTime.getYear()) ? 0 : 1;
        int weekOfYear = dateTime.getWeekOfWeekyear() + weekDis;
        if (weekOfYear >= 53) weekOfYear = 1;
        int weekOfMonth = dateTime.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
        if (minMonthDate.getWeekOfWeekyear() >= dateTime.getWeekOfWeekyear())
            weekOfMonth = dateTime.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
        if (dateTime.getMonthOfYear() == 1) weekOfMonth = weekOfYear;
        return weekOfMonth;
    }
    private static int weekOfYear(DateTime dateTime) {
        DateTime minYearDate = dateTime.dayOfYear().withMinimumValue();
        int weekDis = (minYearDate.getWeekyear() == dateTime.getYear()) ? 0 : 1;
        int weekOfYear = dateTime.getWeekOfWeekyear() + weekDis;
        if (weekOfYear >= 53) weekOfYear = 1;
        return weekOfYear;
    }
}
