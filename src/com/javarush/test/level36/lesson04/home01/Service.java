package com.javarush.test.level36.lesson04.home01;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Игорь on 09.06.2016.
 */
public class Service {
    public List<String> getData() {
        List<String> data = new ArrayList<String>() {{
            add("First string");
            add("Second string");
            add("Third string");
        }};
        return data;
    }
}
