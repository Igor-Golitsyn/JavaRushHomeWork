package com.javarush.test.level17.lesson10.bonus02;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в
соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/
public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }
    private static Person human;
    private static String name;
    private static Sex sex;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private static SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    private static Date birthday = new Date();
    private static int id;

    public synchronized static void main(String[] args) {
        String[] newArgs = new String[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            newArgs[i] = args[i + 1];
        }
        //start here - начни тут
        if (args[0].matches("-c") || args[0].matches("-с")) {
            addPeople(newArgs);
        } else if (args[0].matches("-u")) {
            updatePeople(newArgs);
        } else if (args[0].matches("-d")) {
            delPeople(newArgs);
        } else if (args[0].matches("-i")) {
            printPeople(newArgs);
        }
        /*for (int i=0;i<allPeople.size();i++){
            System.out.println(allPeople.get(i).getName()+" "+allPeople.get(i).getSex()+" "+allPeople.get(i).getBirthDay());
        }*/
    }
    private static void addPeople(String[] a) {
        for (int i = 0; i <= a.length-3; i = i + 3) {
            name = a[i];
            try {
                birthday = sdf.parse(a[i + 2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (a[i + 1].matches("м")) {
                sex = Sex.MALE;
                allPeople.add(Person.createMale(name, birthday));
            } else {
                sex = Sex.FEMALE;
                allPeople.add(Person.createFemale(name, birthday));
            }
            System.out.println(allPeople.size() - 1);
        }
    }
    private static void updatePeople(String[] a) {
        for (int i = 0; i <= a.length-4; i = i + 4) {
            id = Integer.parseInt(a[i]);
            name = a[i + 1];
            if (a[i + 2].matches("м")) sex = Sex.MALE;
            else sex = Sex.FEMALE;
            try {
                birthday = sdf.parse(a[i + 3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            human = allPeople.get(id);
            human.setName(name);
            human.setSex(sex);
            human.setBirthDay(birthday);
            allPeople.set(id, human);
        }
    }
    private static void delPeople(String[] a) {
        for (int i = 0; i <= a.length-1; i++) {
            id = Integer.parseInt(a[i]);
            allPeople.set(id, null);
        }
    }
    private static void printPeople(String[] a) {
        for (int i = 0; i <= a.length-1; i++) {
            id = Integer.parseInt(a[i]);
            human = allPeople.get(id);
            name = human.getName();
            sex = human.getSex();
            birthday = human.getBirthDay();
            String s = sex == Sex.MALE ? "м" : "ж";
            System.out.println(name + " " + s + " " + sdfOut.format(birthday));
        }
    }
}
