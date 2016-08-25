package com.javarush.test.level26.lesson15.big01.command;
import com.javarush.test.level26.lesson15.big01.Operation;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Игорь on 16.12.2015.
 */
public class CommandExecutor {
    private static Map<Operation, Command> commandMap = new HashMap<>();
    static {
        commandMap.put(Operation.LOGIN,new LoginCommand());
        commandMap.put(Operation.INFO,new InfoCommand());
        commandMap.put(Operation.DEPOSIT,new DepositCommand());
        commandMap.put(Operation.WITHDRAW,new WithdrawCommand());
        commandMap.put(Operation.EXIT,new ExitCommand());
    }
    private CommandExecutor() {
    }
    public static final void execute(Operation operation) throws InterruptOperationException, NotEnoughMoneyException {
        commandMap.get(operation).execute();
    }
}
