package com.javarush.test.level26.lesson15.big01.command;
import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;
/**
 * Created by Игорь on 16.12.2015.
 */
class InfoCommand implements Command {
    private ResourceBundle res=ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulators.isEmpty()) ConsoleHelper.writeMessage(res.getString("no.money"));
        for (CurrencyManipulator manipulator : manipulators) {
            if (!manipulator.hasMoney()) ConsoleHelper.writeMessage(res.getString("no.money"));
            else
                ConsoleHelper.writeMessage(String.format("%s - %d", manipulator.getCurrencyCode(), manipulator.getTotalAmount()));
        }
    }
}
