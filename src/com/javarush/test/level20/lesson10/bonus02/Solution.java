package com.javarush.test.level20.lesson10.bonus02;
/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0, 0, 0, 1, 0},
                {1, 1, 0, 0, 0, 0, 1, 0},
                {1, 1, 0, 0, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0}};
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }
    public static int getRectangleCount(byte[][] a) {
        byte[][] temp=a.clone();
        int rectangles = 0;
        int lenght = temp.length;
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                if (a[i][j] == 1) {
                    rectangles++;
                    destroy(i, j, temp);
                }
            }
        }
        return rectangles;
    }
    public static void destroy(int i, int j, byte[][] temp) {
        //System.out.println();
        int lenght = temp.length;
        int gorizEnd = 0;
        boolean stop = false;
        for (int k = j; k < lenght; k++) {
            if (temp[i][k] == 1) {
                gorizEnd = k;
            } else break;
        }
        for (int p = i; p < lenght; p++) {
            for (int u = j; u <= gorizEnd; u++) {
                if (temp[p][u] != 0) {
                    temp[p][u] = 0;
                } else {
                    stop = true;
                    break;
                }
            }
            if (stop) break;
        }
        /*Рисуем результат*/
        /*for (int k = 0; k < lenght; k++) {
            for (int l = 0; l < lenght; l++) {
                System.out.print(temp[k][l] + " ");
            }
            System.out.println();
        }*/
    }
}
