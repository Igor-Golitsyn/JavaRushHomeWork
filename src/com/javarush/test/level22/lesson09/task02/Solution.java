package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static StringBuilder getCondition(Map<String, String> params) {
        if (params==null||params.isEmpty())return new StringBuilder();
        StringBuilder stringBuilder=new StringBuilder();
        for (String temp:params.keySet()){
            if (params.get(temp)==null)continue;
            stringBuilder.append(temp + " = '" + params.get(temp) + "'"+" and ");
        }
        if (stringBuilder.length()==0)return new StringBuilder();
        stringBuilder.delete(stringBuilder.length()-5,stringBuilder.length());
        return stringBuilder;
    }
    public static void main(String[] args) {
        Map<String,String> mappp=new HashMap<>();
        mappp.put("name",null);
        mappp.put("country",null);
        mappp.put("city",null);
        mappp.put("age",null);
        System.out.println(getCondition(mappp));
    }
}
