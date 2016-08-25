package com.javarush.test.level26.lesson15.big01.command;
import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;
/**
 * Created by Игорь on 16.12.2015.
 */
class DepositCommand implements Command {
    private ResourceBundle res=ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] twoDigit = ConsoleHelper.getValidTwoDigits(code);
        int twoDigit0=Integer.parseInt(twoDigit[0]);
        int twoDigit1=Integer.parseInt(twoDigit[1]);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code).
                addAmount(twoDigit0, twoDigit1);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),twoDigit0*twoDigit1,code));
    }
}
