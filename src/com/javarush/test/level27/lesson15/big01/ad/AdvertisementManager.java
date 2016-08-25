package com.javarush.test.level27.lesson15.big01.ad;
import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
/**
 * Created by Игорь on 12.01.2016.
 */
public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos() {
        ArrayList<Advertisement> listToPlay = new ArrayList<>();
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0) {
                listToPlay.add(advertisement);
            }
        }
        if (listToPlay.isEmpty()) {
            StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }
        for (int i = 0; i < listToPlay.size(); i++) {
            ArrayList<Advertisement> tt = new ArrayList<>(listToPlay);
            Advertisement ttt = tt.get(i);
            tt.remove(i);
            jmot(tt, ttt);
        }
        listToPlay = listEtalon;
        if (listToPlay.isEmpty()) {
            StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }
        Collections.sort(listToPlay, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int rezult;
                rezult = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (rezult == 0)
                    rezult = Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration(), o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                return rezult;
            }
        });
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(listToPlay, rezultAmount(listToPlay), rezultTimePlay(listToPlay)));
        for (Advertisement advertisement : listToPlay) {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", advertisement.getName(), advertisement.getAmountPerOneDisplaying(), advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration()));
            advertisement.revalidate();
        }
    }
    private ArrayList<Advertisement> listEtalon = new ArrayList<>();
    private void jmot(ArrayList<Advertisement> advertisements, Advertisement previousAdv) {
        ArrayList<Advertisement> arrayListTemp = new ArrayList<>(advertisements);
        ArrayList<Advertisement> arrayListRezult = new ArrayList<>();
        arrayListRezult.add(previousAdv);
        arrayListRezult.addAll(arrayListTemp);
        if (rezultTimePlay(arrayListRezult) <= timeSeconds) {
            if (rezultAmount(arrayListRezult) == rezultAmount(listEtalon)) {
                if (rezultTimePlay(arrayListRezult) == rezultTimePlay(listEtalon)) {
                    if (arrayListRezult.size() < listEtalon.size()) {
                        listEtalon = arrayListRezult;
                    }
                } else {
                    if (rezultTimePlay(arrayListRezult) > rezultTimePlay(listEtalon)) {
                        listEtalon = arrayListRezult;
                    }
                }
            } else {
                if (rezultAmount(arrayListRezult) > rezultAmount(listEtalon)) {
                    listEtalon = arrayListRezult;
                }
            }
        }
        for (int i = 0; i < arrayListTemp.size(); i++) {
            ArrayList<Advertisement> tt = new ArrayList<>(arrayListTemp);
            Advertisement ttt = tt.get(i);
            tt.remove(i);
            jmot(tt, ttt);
        }
    }
    private int rezultTimePlay(ArrayList<Advertisement> arrayList) {
        int rezult = 0;
        for (Advertisement advertisement : arrayList) {
            rezult += advertisement.getDuration();
        }
        return rezult;
    }
    private long rezultAmount(ArrayList<Advertisement> arrayList) {
        long rezult = 0;
        for (Advertisement advertisement : arrayList) {
            rezult += advertisement.getAmountPerOneDisplaying();
        }
        return rezult;
    }
}
