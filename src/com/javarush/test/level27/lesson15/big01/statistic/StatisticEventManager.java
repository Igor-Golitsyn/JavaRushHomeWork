package com.javarush.test.level27.lesson15.big01.statistic;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
/**
 * Created by golit on 18.01.2016.
 */
public class StatisticEventManager {
    private static StatisticEventManager ourInstance = new StatisticEventManager();
    public StatisticStorage storage = new StatisticStorage();
    public static StatisticEventManager getInstance() {
        return ourInstance;
    }
    private StatisticEventManager() {
    }
    public void register(EventDataRow data) {
        //TODO
        storage.put(data);
    }
    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> map;
        public StatisticStorage() {
            map = new HashMap<>();
            for (EventType e : EventType.values()) {
                map.put(e, new ArrayList<EventDataRow>());
            }
        }
        private void put(EventDataRow data) {
            map.get(data.getType()).add(data);
        }
        private List<EventDataRow> getForEventType(EventType eventType) {
            return map.get(eventType);
        }
    }
    public Map<Long, Long> getAmountStatistick() {
        Map<Long, Long> amountStatistick = new TreeMap<Long, Long>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o2, o1);
            }
        });
        for (EventDataRow eventDataRow : storage.getForEventType(EventType.SELECTED_VIDEOS)) {
            long data = eventDataRow.getDate().getTime() / 86400000;
            VideoSelectedEventDataRow selectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            if (amountStatistick.containsKey(data)) {
                amountStatistick.put(data, amountStatistick.get(data) + selectedEventDataRow.getAmount());
            } else {
                amountStatistick.put(data, selectedEventDataRow.getAmount());
            }
        }
        return amountStatistick;
    }
    public Map<Long, Map<String, Integer>> getCookStatistic() {
        Map<Long, Map<String, Integer>> allDayStatistic = new TreeMap<Long, Map<String, Integer>>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o2, o1);
            }
        });
        for (EventDataRow eventDataRow : storage.getForEventType(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) eventDataRow;
            long day = cookedOrderEventDataRow.getDate().getTime() / 86400000;
            String cookName = cookedOrderEventDataRow.getCookName();
            int cookWorkInEvent = cookedOrderEventDataRow.getTime();
            if (allDayStatistic.containsKey(day)) {
                Map<String, Integer> cookersMap = allDayStatistic.get(day);
                if (cookersMap.containsKey(cookName)) {
                    cookersMap.put(cookName, cookersMap.get(cookName) + cookWorkInEvent);
                } else {
                    cookersMap.put(cookName, cookWorkInEvent);
                }
            } else {
                Map<String, Integer> cookersMap = new TreeMap<>();
                cookersMap.put(cookName, cookWorkInEvent);
                allDayStatistic.put(day, cookersMap);
            }
        }
        return allDayStatistic;
    }
    public List<EventDataRow> getNoVideoAvailabl() {
        return storage.getForEventType(EventType.NO_AVAILABLE_VIDEO);
    }
}
