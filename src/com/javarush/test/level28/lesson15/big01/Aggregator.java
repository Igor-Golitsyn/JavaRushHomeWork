package com.javarush.test.level28.lesson15.big01;
import com.javarush.test.level28.lesson15.big01.model.*;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;
import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;
/**
 * Created by golit on 08.02.2016.
 */
public class Aggregator {
    public static void main(String[] args) {
        View view=new HtmlView();
        Model model = new Model(view,new Provider[]{new Provider(new HHStrategy()),new Provider(new MoikrugStrategy())});
        Controller controller=new Controller(model);
        view.setController(controller);
        ((HtmlView)view).userCitySelectEmulationMethod();
    }
}
