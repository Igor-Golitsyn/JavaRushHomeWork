package com.javarush.test.level26.lesson15.big01.command;
import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * Created by Игорь on 18.12.2015.
 */
class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String lineCard = ConsoleHelper.readString();
            String linePin = ConsoleHelper.readString();
            if (!(lineCard.matches("\\d{12}") && linePin.matches("\\d{4}"))) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            try {
                if (linePin.equals(validCreditCards.getString(lineCard))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), lineCard));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), lineCard));
                }
            }
            catch (MissingResourceException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
