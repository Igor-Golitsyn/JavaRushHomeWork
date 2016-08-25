package com.javarush.test.level20.lesson02.task01;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
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
            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.assets.get(0).setPrice(10569.45);
            ivanov.assets.get(1).setPrice(456.89);
            ivanov.save(outputStream);
            outputStream.flush();
            Human sidorov = new Human("Sidorov", new Asset("home"), new Asset("velo"));
            sidorov.assets.get(1).setPrice(400);
            sidorov.save(outputStream);
            outputStream.flush();
            Human somePerson = new Human();
            somePerson.load(inputStream);
            System.out.println(somePerson==ivanov);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            inputStream.close();
            System.out.println(somePerson.name + " " + somePerson.assets.get(0).getName() + " " +
                    somePerson.assets.get(0).getPrice() + " " + somePerson.assets.get(1).getName() + " " +
                    somePerson.assets.get(1).getPrice());
            System.out.println(ivanov.name + " " + ivanov.assets.get(0).getName() + " " +
                    ivanov.assets.get(0).getPrice() + " " + ivanov.assets.get(1).getName() + " " +
                    ivanov.assets.get(1).getPrice());
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
    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();
        public Human() {
        }
        public Human(String name, Asset... assets) {
            this.name = name;
            if (name == null || name.isEmpty())
                return;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }
        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            outputStream.write(this.name.getBytes());
            outputStream.write(32);
            for (Asset temp : assets) {
                outputStream.write(temp.getName().getBytes());
                outputStream.write(32);
                outputStream.write((temp.getPrice() + "").getBytes());
                outputStream.write(32);
            }
            outputStream.write(10);
            outputStream.write(13);
        }
        public void load(InputStream inputStream) throws IOException {
            //implement this method - реализуйте этот метод
            BufferedReader fr = new BufferedReader(new InputStreamReader(inputStream));
            String[] line = fr.readLine().split(" ");
            this.name = line[0];
            int numAss = 0;
            for (int i = 1; i <= line.length - 2; i = i + 2) {
                this.assets.add(new Asset(line[i]));
                this.assets.get(numAss).setPrice(Double.parseDouble(line[i + 1]));
                numAss++;
            }
        }
    }
}
