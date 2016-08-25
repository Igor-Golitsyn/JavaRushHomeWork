package com.javarush.test.level27.lesson15.big01.ad;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by golit on 25.01.2016.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private final AdvertisementStorage advertisementStorage=AdvertisementStorage.getInstance();
    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }
    private StatisticAdvertisementManager() {
    }
    public List<Advertisement> getActiveAdvertisment() {
        List<Advertisement> advertisements = new ArrayList<>(advertisementStorage.list());
        Iterator<Advertisement> iterator = advertisements.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getHits() == 0) iterator.remove();
        }
        return advertisements;
    }
    public List<Advertisement> getArchivedAdvertisment() {
        List<Advertisement> advertisements = new ArrayList<>(advertisementStorage.list());
        Iterator<Advertisement> iterator = advertisements.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getHits() > 0) iterator.remove();
        }
        return advertisements;
    }
}
