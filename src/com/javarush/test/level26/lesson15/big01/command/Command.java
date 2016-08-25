package com.javarush.test.level26.lesson15.big01.command;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;
/**
 * Created by Игорь on 16.12.2015.
 */
interface Command {
    public void execute() throws InterruptOperationException, NotEnoughMoneyException;
}
