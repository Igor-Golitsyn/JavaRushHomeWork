package com.javarush.test.level15.lesson12.home05;
/**
 * Created by polus on 30.06.2015.
 */
public class SubSolution extends Solution {
    SubSolution() {
    }
    SubSolution(Solution s) {
        super(s);
    }
    SubSolution(Solution s, Solution ss) {
        super(s, ss);
    }
    public SubSolution(int i) {
        super(i);
    }
    public SubSolution(int i, int ii) {
        super(i, ii);
    }
    public SubSolution(int i, int ii, int iii) {
        super(i, ii, iii);
    }
    private SubSolution(String str){    }
    private SubSolution(String str,String strr){}
    private SubSolution(String str,String strr,String strrr){}
    protected SubSolution(char ch) {
        super(ch);
    }
    protected SubSolution(char ch, char chh) {
        super(ch, chh);
    }
    protected SubSolution(char ch, char chh, char chhh) {
        super(ch, chh, chhh);
    }
}
