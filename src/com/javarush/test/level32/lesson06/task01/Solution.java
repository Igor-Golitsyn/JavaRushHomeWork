package com.javarush.test.level32.lesson06.task01;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }
    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch;
        String password;
        Matcher azSmal;
        Matcher azBig;
        Matcher digit;
        do {
            password = new String();
            for (int i = 0; i < 8; i++) {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    ch = ThreadLocalRandom.current().nextInt(48, 58);
                } else {
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        ch = (char) ThreadLocalRandom.current().nextInt(65, 91);
                    } else {
                        ch = (char) ThreadLocalRandom.current().nextInt(97, 123);
                    }
                }
                password += (char) ch;
            }
            azSmal = Pattern.compile("[a-z]+").matcher(password);
            azBig = Pattern.compile("[A-Z]+").matcher(password);
            digit = Pattern.compile("[0-9]+").matcher(password);
        }
        while (!(azBig.find() && azSmal.find() && digit.find()));
        try {
            byteArrayOutputStream.write(password.getBytes());
            byteArrayOutputStream.close();
        }
        catch (IOException e) {
        }
        return byteArrayOutputStream;
    }
}
