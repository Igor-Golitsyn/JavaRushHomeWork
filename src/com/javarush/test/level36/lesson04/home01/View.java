package com.javarush.test.level36.lesson04.home01;
/**
 * Created by Игорь on 09.06.2016.
 */
public class View {
    private Controller controller = new Controller();
    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
