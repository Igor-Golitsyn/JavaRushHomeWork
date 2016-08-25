package com.javarush.test.level14.lesson08.bonus02;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int fist = Integer.parseInt(reader.readLine());
        int second = Integer.parseInt(reader.readLine());
        reader.close();
        int ost;
        if (fist < second) {
            int temp = fist;
            fist = second;
            second = temp;
        }
        while (true) {
            ost = fist % second;
            if (ost == 0) {break;}
            fist=second;
            second=ost;
        }
        System.out.println(second);
    }
}
