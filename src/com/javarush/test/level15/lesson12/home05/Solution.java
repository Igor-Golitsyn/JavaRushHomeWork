package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    Solution(){}
    Solution(Solution s){}
    Solution(Solution s,Solution ss){}

    public Solution(int i){}
    public Solution(int i,int ii){}
    public Solution(int i,int ii,int iii){}

    private Solution(String str){}
    private Solution(String str,String strr){}
    private Solution(String str,String strr,String strrr){}

    protected Solution(char ch){}
    protected Solution(char ch,char chh){}
    protected Solution(char ch, char chh, char chhh){}
}

