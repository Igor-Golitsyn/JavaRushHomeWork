package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> fi = new HashMap<>();
        fi.put("Иванов", "Иван");
        fi.put("Косенко", "Алексей");
        fi.put("Иванов", "Кирил");
        fi.put("Лавров", "Иван");
        fi.put("Петухов", "Иван");
        fi.put("Лавров", "Игорь");
        fi.put("Джигурда", "Жи");
        fi.put("Путин", "Василий");
        fi.put("Пометелин", "Иван");
        fi.put("Паюк", "Максим");
        return fi;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {        int i = 0;
        for (Map.Entry<String, String> entrySet : map.entrySet()) {
            if (entrySet.getValue().equalsIgnoreCase(name)) {
                i++;
            }
        }
        return i;
           }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        int i = 0;
        for (Map.Entry<String, String> entrySet : map.entrySet()) {
            if (entrySet.getValue().equalsIgnoreCase(familiya)) {
                i++;
            }
        }
        return i;
        //Напишите тут ваш код

    }
}
