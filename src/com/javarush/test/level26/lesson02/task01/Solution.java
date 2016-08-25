package com.javarush.test.level26.lesson02.task01;
import java.util.Arrays;
import java.util.Comparator;
/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        final double median;
        Arrays.sort(array);
        int middle = array.length / 2;
        if (array.length % 2 == 1) median = array[middle];
        else median = (array[middle - 1] + array[middle]) / 2.0;
        Comparator<Integer> comparatorByMedian = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result1 = Math.abs((int) ((double) o1 - median));
                int result2 = Math.abs((int) ((double) o2 - median));
                int addendum = (result1 == result2) ? ((o1 > o2) ? 1 : -1) : 0;
                int result = (result1 - result2) * 10 + addendum;
                return result;
            }
        };
        Arrays.sort(array, comparatorByMedian);
        return array;
    }

}
/*
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        double median = 0;
        int n = array.length;
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add((double) array[i]);
        }
        list.sort((o1, o2) -> {return Double.compare(o1, o2);});
        if (n % 2 > 0) {
            median = list.get((n + 1) / 2 - 1);
        } else median = (list.get(n / 2 - 1) + list.get(n / 2)) / 2;
        final double finalMedian = median;
        list.sort((o1, o2) -> {
            int result = (int) Math.round(Math.abs(o1 - finalMedian) - Math.abs(o2 - finalMedian));
            return result == 0 ? Double.compare(o1, o2) : result;
        });
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }
    public static void main(String[] args) {
        Integer[] integers = Solution.sort(new Integer[]{5, 8,  15, 17});
        for (int i = 0; i < integers.length; i++) {
            System.out.println(integers[i]);
        }
    }
}
*/

