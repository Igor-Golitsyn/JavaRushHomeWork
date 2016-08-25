package com.javarush.test.level19.lesson03.task03;

/* Адаптация нескольких интерфейсов
Адаптировать IncomeData к Customer и Contact.
Классом-адаптером является IncomeDataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
Дополнить телефонный номер нулями до 10 цифр при необходимости
*/
import java.util.HashMap;
import java.util.Map;
public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }
    public static class IncomeDataAdapter implements Contact, Customer {
        private IncomeData incomeData;
        public IncomeDataAdapter(IncomeData incomeData) {
            this.incomeData = incomeData;
        }
        @Override
        public String getName() {
            String text = incomeData.getContactLastName() + ", " + incomeData.getContactFirstName();
            return text;
        }
        @Override
        public String getPhoneNumber() {
            String num = incomeData.getPhoneNumber() + "";
            while (num.length() < 10) {
                num = "0" + num;
            }
            char[] chars=num.toCharArray();
            num = "+"+incomeData.getCountryPhoneCode()+"("+chars[0]+chars[1]+chars[2]+")"+chars[3]+chars[4]+chars[5]+
                    "-"+chars[6]+chars[7]+"-"+chars[8]+chars[9];
            return num;
        }
        @Override
        public String getCompanyName() {
            return incomeData.getCompany();
        }
        @Override
        public String getCountryName() {
            String text = countries.get(incomeData.getCountryCode());
            return text;
        }
    }
    public static interface IncomeData {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        int getCountryPhoneCode();      //example 38
        int getPhoneNumber();           //example 501234567
    }
    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }
    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}