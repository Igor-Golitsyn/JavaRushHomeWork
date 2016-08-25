package com.javarush.test.level17.lesson10.bonus01;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/
public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }
    public static void main(String[] args) {
        //start here - начни тут
        Person human;
        String name;
        Sex sex;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Date birthday = new Date();
        int id;
        if (args[0].matches("-c")||args[0].matches("-с")) {
            name = args[1];
            try {
                birthday = sdf.parse(args[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (args[2].matches("м")) {
                sex = Sex.MALE;
                allPeople.add(Person.createMale(name, birthday));
            } else {
                sex = Sex.FEMALE;
                allPeople.add(Person.createFemale(name, birthday));
            }
            System.out.println(allPeople.size() - 1);
        } else if (args[0].matches("-u")) {
            id = Integer.parseInt(args[1]);
            name = args[2];
            if (args[3].matches("м")) sex = Sex.MALE;
            else sex = Sex.FEMALE;
            try {
                birthday = sdf.parse(args[4]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            human = allPeople.get(id);
            human.setName(name);
            human.setSex(sex);
            human.setBirthDay(birthday);
            allPeople.set(id, human);
        } else if (args[0].matches("-d")) {
            id = Integer.parseInt(args[1]);
            allPeople.set(id, null);
        } else if (args[0].matches("-i")) {
            id = Integer.parseInt(args[1]);
            human = allPeople.get(id);
            name = human.getName();
            sex = human.getSex();
            birthday = human.getBirthDay();
            String s = sex == Sex.MALE ? "м" : "ж";
            System.out.println(name + " " + s + " " + sdfOut.format(birthday));
        }
    }
}
