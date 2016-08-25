package com.javarush.test.level20.lesson10.bonus01;
import java.math.BigDecimal;
import java.util.*;
/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[] result = null;
        TreeMap<Long, ?> map = new TreeMap<>();
        int razr = (N + "").length();
        long[][] tablica = new long[10][razr];
        for (int i = 0; i < 10; i++) {
            long temp = i;
            for (int j = 0; j < razr; j++) {
                tablica[i][j] = temp;
                temp = temp * i;
            }
        }
        int[] chislMas = new int[razr];
        for (int i = 0; i < razr; i++) {
            chislMas[i] = 0;
        }
        for (int tt = 1; tt < 10; tt++) {
            chislMas[razr - 1] = tt;
            String line = new String();
            for (int aa = 0; aa < razr; aa++) {
                line += chislMas[aa] + "";
            }
            long chislo = Long.parseLong(line);
            String lineline = chislo + "";
            int dlinnaChisla = lineline.length();
            if (chislo > N) { break; }
            char[] chisloChar = (chislo + "").toCharArray();
            char[] vesChar;
            long ves;
            for (int i = dlinnaChisla; i <= razr; i++) {
                ves = 0;
                for (int dd = 0; dd < razr; dd++) {
                    ves += tablica[chislMas[dd]][i - 1];
                }
                vesChar = (ves + "").toCharArray();
                boolean treh = cisloANDves(i, chisloChar, vesChar);
                if (treh) {
                    if (ves > N) continue;
                    map.put(ves, null);
                }
            }
            if (chislMas[razr - 1] == 9) {
                for (int zz = razr - 2; zz >= 0; zz--) {
                    if (chislMas[zz] != 9) {
                        chislMas[zz]++;
                        for (int xx = zz; xx < razr; xx++) {
                            chislMas[xx] = chislMas[zz];
                            tt = chislMas[xx] - 1;
                        }
                        break;
                    } else {
                        chislMas[zz] = 0;
                    }
                }
            }
        }
        result = new int[map.size()];
        int fint = 0;
        for (long temp : map.keySet()) {
            result[fint] = (int) temp;
            fint++;
        }
        return result;
    }
    public static boolean cisloANDves(int dlinna, char[] chisloChar, char[] vesChar) {
        ArrayList<String> arrayChislo = new ArrayList<>();
        for (int y = 0; y < dlinna; y++) {
            arrayChislo.add(Integer.toString(0));
        }
        int h = dlinna - chisloChar.length;
        for (int g = 0; g < chisloChar.length; g++) {
            arrayChislo.set(h + g, chisloChar[g] + "");
        }
        ArrayList<String> arrayVes = new ArrayList<>();
        for (char temp : vesChar) {
            arrayVes.add(temp + "");
        }
        if (arrayChislo.size() != arrayVes.size()) return false;
        for (String temp : arrayChislo) {
            arrayVes.remove(temp);
        }
        if (arrayVes.size() > 0) return false;
        return true;
    }
    public static void main(String[] args) {
        Date starrt = new Date();
        Solution solution = new Solution();
        int[] trewr = solution.getNumbers(Integer.MAX_VALUE);
        Date finish = new Date();
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb" + " TIME: " + (finish.getTime() - starrt.getTime()));
        for (int temp:trewr){
            System.out.println(temp);
        }
    }
}
