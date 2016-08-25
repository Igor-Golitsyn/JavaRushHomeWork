package com.javarush.test.level32.lesson15.big01.listeners;
import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
/**
 * Created by golit on 12.04.2016.
 */
public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view) {
        this.view = view;
    }
    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu menu = (JMenu) menuEvent.getSource();
        Component[]components=menu.getMenuComponents();
        for (Component component:components){
            component.setEnabled(view.isHtmlTabSelected());
        }
    }
    @Override
    public void menuDeselected(MenuEvent e) {
    }
    @Override
    public void menuCanceled(MenuEvent e) {
    }
}
