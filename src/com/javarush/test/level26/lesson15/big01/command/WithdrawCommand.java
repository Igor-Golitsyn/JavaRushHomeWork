package com.javarush.test.level26.lesson15.big01.command;
import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;
/**
 * Created by Игорь on 16.12.2015.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        Map<Integer, Integer> map;
        int expectedAmount;
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (expectedAmount <= 0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (!manipulator.isAmountAvailable(expectedAmount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
            }
            catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            try {
                map = manipulator.withdrawAmount(expectedAmount);
                for (Integer nominal : map.keySet()) {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", nominal, map.get(nominal)));
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount, code));
                break;
            }
            catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
