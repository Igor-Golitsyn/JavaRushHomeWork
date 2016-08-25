package com.javarush.test.level20.lesson10.bonus03;
import java.util.ArrayList;
import java.util.List;
/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}};
        List<Word> words= detectAllWords(crossword, "emoh", "same", "home","ver","rev","rov");
        for (Word ww:words){
            System.out.println(ww);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> list=new ArrayList<>();
        int stroka = crossword.length;
        int stolb = crossword[0].length;
        for (int i = 0; i < words.length; i++) {
            Word slovo = new Word(words[i]);
            char[] chars = words[i].toCharArray();
            for (int j = 0; j < stroka; j++) {

                for (int k = 0; k < stolb; k++) {
                    if (chars[0] == (char) crossword[j][k]) {

                        int dlinSlova = chars.length - 1;
                        try {
                            if (chars[dlinSlova] == (char) crossword[j - dlinSlova][k]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k, j - dlinSlova);
                                if (proverka(slovo,crossword)){
                                list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j + dlinSlova][k]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k, j + dlinSlova);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j + dlinSlova][k + dlinSlova]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k + dlinSlova, j + dlinSlova);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j - dlinSlova][k - dlinSlova]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k - dlinSlova, j - dlinSlova);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j + dlinSlova][k - dlinSlova]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k - dlinSlova, j + dlinSlova);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j - dlinSlova][k + dlinSlova]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k + dlinSlova, j - dlinSlova);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j][k + dlinSlova]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k + dlinSlova, j);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                        try {
                            if (chars[dlinSlova] == (char) crossword[j][k - dlinSlova]) {
                                slovo.setStartPoint(k, j);
                                slovo.setEndPoint(k - dlinSlova, j);
                                if (proverka(slovo,crossword)){
                                    list.add(slovo);}
                            }
                        }
                        catch (Exception e) {
                        }
                    }
                }
            }

        }
        return list;
    }
    private static boolean proverka(Word word,int[][]crossword){
        String line=new String();
        int x=word.startX,y=word.startY;
        for (int i = 0; i < word.text.length(); i++) {
            if (word.startX<word.endX)x=word.startX+i;
            if (word.startX>word.endX)x=word.startX-i;
            if (word.startY<word.endY)y=word.startY+i;
            if (word.startY>word.endY)y=word.startY-i;
            line+=(char)crossword[y][x];
        }
        return line.equals(word.text);
    }
    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;
        public Word(String text) {
            this.text = text;
        }
        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }
        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }
        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
