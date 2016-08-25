package com.javarush.test.level20.lesson02.task02;
import com.javarush.test.level14.lesson06.home01.Country;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);
            JavaRush javaRush = new JavaRush();
            javaRush.users.add(new User());
            javaRush.users.get(0).setFirstName("Igor");
            javaRush.users.get(0).setLastName("Golitcyn");
            javaRush.users.get(0).setBirthDate(new Date());
            javaRush.users.get(0).setCountry(User.Country.RUSSIA);
            javaRush.users.get(0).setMale(true);
            javaRush.users.add(new User());
            javaRush.users.get(1).setFirstName("Galya");
            javaRush.users.get(1).setLastName("Grigor`eva");
            javaRush.users.get(1).setBirthDate(new Date());
            javaRush.users.get(1).setCountry(User.Country.UKRAINE);
            javaRush.users.get(1).setMale(false);
            JavaRush javaRush1 = new JavaRush();
            javaRush1.users.add(new User());
            javaRush1.users.get(0).setFirstName("Vasiliy");
            javaRush1.users.get(0).setLastName("Petrovich");
            javaRush1.users.get(0).setBirthDate(new Date());
            javaRush1.users.get(0).setCountry(User.Country.OTHER);
            javaRush1.users.get(0).setMale(true);
            javaRush1.users.add(new User());
            javaRush1.users.get(1).setFirstName("Olga");
            javaRush1.users.get(1).setLastName("Tarasova");
            javaRush1.users.get(1).setBirthDate(new Date());
            javaRush1.users.get(1).setCountry(User.Country.UKRAINE);
            javaRush1.users.get(1).setMale(false);
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            javaRush1.save(outputStream);
            outputStream.flush();
            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            outputStream.close();
            inputStream.close();
            System.out.println(javaRush.users.get(0).getLastName() + javaRush.users.get(0).getBirthDate() +
                    javaRush.users.get(0).getCountry());
            System.out.println(javaRush.users.get(1).getLastName() + javaRush.users.get(1).getBirthDate() +
                    javaRush.users.get(1).getCountry());
            System.out.println(loadedObject.users.get(0).getLastName() + loadedObject.users.get(0).getBirthDate() +
                    loadedObject.users.get(0).getCountry());
            System.out.println(loadedObject.users.get(1).getLastName() + loadedObject.users.get(1).getBirthDate() +
                    loadedObject.users.get(1).getCountry());
        }
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        }
        catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }
    public static class JavaRush {
        public List<User> users = new ArrayList<>();
        SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            for (User temp : users) {
                outputStream.write(("parametr:" + temp.getLastName()).getBytes());
                outputStream.write(("parametr:" + temp.getFirstName()).getBytes());
                outputStream.write(("parametr:" + temp.getCountry()).getBytes());
                outputStream.write(("parametr:" + temp.isMale()).getBytes());
                outputStream.write(("parametr:" + dd.format(temp.getBirthDate())).getBytes());
            }
            outputStream.write(10);
            outputStream.write(13);
        }
        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader fr = new BufferedReader(new InputStreamReader(inputStream));
            String[] line = fr.readLine().split("parametr:");
            int numUser = 0;
            for (int i = 1; i <= line.length - 5; i = i + 5) {
                this.users.add(new User());
                this.users.get(numUser).setLastName(line[i]);
                this.users.get(numUser).setFirstName(line[i + 1]);
                this.users.get(numUser).setCountry(User.Country.valueOf(line[i + 2]));
                this.users.get(numUser).setMale(line[i + 3].equals("true") ? true : false);
                this.users.get(numUser).setBirthDate(dd.parse(line[i + 4]));
                numUser++;
            }
        }
    }
}
