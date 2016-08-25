package com.javarush.test.level33.lesson10.home01;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Игорь on 22.04.2016.
 */
@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    public int count;
    public double profit;
    @XmlElementWrapper(name = "goods")
    public List <String> names=new ArrayList<>();
    public List<String> secretData=new ArrayList<>();
    public Shop() {
    }
    @Override
    public String toString() {
        return "Shop{" +
                "names=" + names +
                ", count=" + count +
                ", profit=" + profit +
                ", secretData=" + secretData +
                '}';
    }
}
