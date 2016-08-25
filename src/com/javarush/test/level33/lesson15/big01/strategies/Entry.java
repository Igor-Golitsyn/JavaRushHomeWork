package com.javarush.test.level33.lesson15.big01.strategies;
import java.io.Serializable;
/**
 * Created by Игорь on 04.05.2016.
 */
public class Entry implements Serializable {
    final Long key;
    String value;
    Entry next;
    final int hash;
    public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
    public Long getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    public int hashCode(){
        return hash;
    }
    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", hash=" + hash +
                '}';
    }
}
