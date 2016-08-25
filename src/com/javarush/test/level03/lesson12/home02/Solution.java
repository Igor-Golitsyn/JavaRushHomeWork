/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javarush.test.level03.lesson12.home02;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author polus
 */
public class Solution {
    static int massiv=0;
    static int[] d;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static void min(int t) {
        for (int i = 0; i < t; i++){
            for(int j=0;j<t-i-1;j++){
                if(d[j]<d[j+1]){
                    int s=d[j+1];
                    d[j+1]=d[j];
                    d[j]=s;
                }
            }
        }
    }
    static void input(int t) throws IOException {
        for (int i = 0; i < t; i++) {
            d[i] = Integer.parseInt(JOptionPane.showInputDialog(null,"Введите число "+(i+1)));

        }
    }
    public static void main(String[] args) throws IOException {
        massiv=Integer.parseInt(JOptionPane.showInputDialog(null, "Введите размер массива:"));
        d = new int[massiv];
        for(int i=0;i<1;i++){
            input(massiv);
            min(massiv);
        }
        String text1=("Минимальное число: "+d[massiv-1]);
        JOptionPane.showMessageDialog(null,text1);
    }

}
