package com.javarush.test.level22.lesson05.task01;
/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) {
            throw new TooShortStringException();
        }
        int fist = string.indexOf(32);
        int temp = fist;
        for (int i = 0; i < 3; i++) {
            if (string.indexOf(32, temp + 1) == -1 || string.indexOf(32, temp + 1) == string.length() - 1) {
                throw new TooShortStringException();
            } else temp = string.indexOf(32, temp + 1);
        }
        if (string.indexOf(32, temp + 1) == -1) temp = string.length();
        else temp = string.indexOf(32, temp + 1);
        return string.substring(fist + 1, temp);
    }
    public static class TooShortStringException extends Exception {
        public TooShortStringException() {
            super();
        }
    }
}
