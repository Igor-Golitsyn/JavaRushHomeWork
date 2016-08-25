package com.javarush.test.level26.lesson15.big01;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
/**
 * Created by Игорь on 15.12.2015.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private boolean hasMoney;
    Map<Integer, Integer> denominations;
    public String getCurrencyCode() {
        return currencyCode;
    }
    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare((int) o2, (int) o1);
            }
        });
    }
    public void addAmount(int denomination, int count) {
        hasMoney = true;
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else denominations.put(denomination, count);
    }
    public int getTotalAmount() {
        int summa = 0;
        for (int denom : denominations.keySet()) {
            summa += denominations.get(denom) * denom;
        }
        return summa;
    }
    public boolean hasMoney() {
        return hasMoney;
    }
    public boolean isAmountAvailable(int expectedAmount) {
        if (getTotalAmount() >= expectedAmount && expectedAmount > 0) return true;
        else return false;
    }
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        stop = false;
        proSumma = 0;
        proMap = new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare((int) o2, (int) o1);
            }
        });
        jadina(denominations, expectedAmount);
        int moneyProMap = 0;
        for (Integer nominal : proMap.keySet()) {
            moneyProMap += nominal * proMap.get(nominal);
        }
        if (moneyProMap != expectedAmount) throw new NotEnoughMoneyException();
        for (Integer nominal : proMap.keySet()) {
            denominations.put(nominal, denominations.get(nominal) - proMap.get(nominal));
        }
        return proMap;
    }
    private boolean stop;
    private int proSumma;
    private Map<Integer, Integer> proMap;
    private Map<Integer, Integer> jadina(Map<Integer, Integer> denom, int expectedAmount) {
        for (Integer nominal : denom.keySet()) {
            for (int i = denom.get(nominal); i >= 0; i--) {
                if (proSumma + nominal * i > expectedAmount) {
                    continue;
                } else if (proSumma + nominal * i == expectedAmount) {
                    if (stop) continue;
                    proMap.put(nominal, i);
                    stop = true;
                    return proMap;
                } else {
                    if (!stop) {
                        proSumma += nominal * i;
                        if (i != 0) proMap.put(nominal, i);
                        TreeMap<Integer, Integer> tempMap = new TreeMap<>(new Comparator() {
                            @Override
                            public int compare(Object o1, Object o2) {
                                return Integer.compare((int) o2, (int) o1);
                            }
                        });
                        tempMap.putAll(denom);
                        tempMap.remove(nominal);
                        jadina(tempMap, expectedAmount);
                    }
                }
            }
        }
        return new TreeMap<>();
    }
}
