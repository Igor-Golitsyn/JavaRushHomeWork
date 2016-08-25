package com.javarush.test.level19.lesson03.task04;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1978

Подсказка: воспользуйтесь классом Calendar
*/
public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scanner;
        public PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }
        @Override
        public Person read() throws IOException {
            String[] lineSplit = scanner.nextLine().split(" ");
            String fistN, middleN, lastN;
            fistN = lineSplit[1];
            middleN = lineSplit[2];
            lastN = lineSplit[0];
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(lineSplit[5]), Integer.parseInt(lineSplit[4]) - 1, Integer.parseInt(lineSplit[3]));
            Date date = calendar.getTime();
            Person person = new Person(fistN, middleN, lastN, date);
            return person;
        }
        @Override
        public void close() throws IOException {
            scanner.close();
        }
    }
}
