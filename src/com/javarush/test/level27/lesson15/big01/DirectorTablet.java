package com.javarush.test.level27.lesson15.big01;
import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by golit on 19.01.2016.
 */
public class DirectorTablet {
    private StatisticEventManager manager = StatisticEventManager.getInstance();
    private StatisticAdvertisementManager advertisementManager=StatisticAdvertisementManager.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        Map<Long, Long> map = manager.getAmountStatistick();
        long itog = 0;
        for (Long dayNumber : map.keySet()) {
            Date date = new Date(dayNumber * 86400000);
            String line=String.format("%s - %.2f", sdf.format(date), (double) map.get(dayNumber) / 100);
            ConsoleHelper.writeMessage(line.replace(',','.'));
            itog += map.get(dayNumber);
        }
        String line=String.format("Total - %.2f", (double) itog / 100);
        ConsoleHelper.writeMessage(line.replace(',','.'));
    }
    public void printCookWorkloading() {
        Map<Long, Map<String, Integer>> cookStat = manager.getCookStatistic();
        for (Long day : cookStat.keySet()) {
            Date date = new Date(day * 86400000);

            Map<String, Integer> cooksInDay = cookStat.get(day);
            ConsoleHelper.writeMessage(sdf.format(date));
            for (String cookName : cooksInDay.keySet()) {
                int workTime = (cooksInDay.get(cookName)+59)/60;
                ConsoleHelper.writeMessage(String.format("%s - %d min", cookName, workTime));
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet() {
        List<Advertisement> activeAdvertisment=advertisementManager.getActiveAdvertisment();
        Collections.sort(activeAdvertisment, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement:activeAdvertisment){
            ConsoleHelper.writeMessage(String.format("%s - %d",advertisement.getName(),advertisement.getHits()));
        }
    }
    public void printArchivedVideoSet() {
        List<Advertisement> noActiveAdvertisment=advertisementManager.getArchivedAdvertisment();
        Collections.sort(noActiveAdvertisment, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement:noActiveAdvertisment){
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
    public void printNoVideAvailible(){
        List<EventDataRow> eventDataRowList=StatisticEventManager.getInstance().getNoVideoAvailabl();
        for (EventDataRow eventDataRow:eventDataRowList){
            ConsoleHelper.writeMessage(String.format("%s no video %dsec",sdf.format(eventDataRow.getDate()),eventDataRow.getTime()));
        }
    }
}
