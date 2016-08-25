package com.javarush.test.level39.lesson09.big01;
import java.nio.file.Paths;
public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println("Уникальные IP: " + logParser.getNumberOfUniqueIPs(null, null));
        System.out.println("Список всех пользователей: " + logParser.getAllUsers());
        System.out.println("Количество уникальных пользователей: " + logParser.getNumberOfUsers(null, null));
        System.out.println("IP, с которых работал переданный пользователь: " + logParser.getIPsForUser("Vasya Pupkin", null, null));
        System.out.println("IP, с которых было произведено переданное событие: " + logParser.getIPsForEvent(Event.LOGIN, null, null));
        System.out.println("IP, события с которых закончилось переданным статусом: " + logParser.getIPsForStatus(Status.OK, null, null));
        System.out.println("Количество событий от определенного пользователя: " + logParser.getNumberOfUserEvents("Vasya Pupkin", null, null));
        System.out.println("пользователей с определенным IP: " + logParser.getUsersForIP("146.34.15.55", null, null));
        System.out.println("пользователи которые были залогинены: " + logParser.getLoggedUsers(null, null));
        System.out.println("пользователи которые скачали плагин: " + logParser.getDownloadedPluginUsers(null, null));
        System.out.println("пользователи которые отправили сообщение: " + logParser.getWroteMessageUsers(null, null));
        System.out.println("пользователи которые решали любую задачу: " + logParser.getSolvedTaskUsers(null, null));
        System.out.println("пользователи которые решали задачу с номером: " + logParser.getSolvedTaskUsers(null, null, 18));
        System.out.println("пользователи которые решили любую задачу: " + logParser.getDoneTaskUsers(null, null));
        System.out.println("пользователи которые решили любую задачу с номером: " + logParser.getDoneTaskUsers(null, null, 15));
        System.out.println("определенный пользователь произвел определенное событие: " + logParser.getDatesForUserAndEvent("Vasya Pupkin", Event.SOLVE_TASK, null, null));
        System.out.println("определенное событие не выполнилось (статус FAILED): " + logParser.getDatesWhenSomethingFailed(null, null));
        System.out.println("определенное событие не выполнилось (статус ERROR): " + logParser.getDatesWhenErrorHappened(null, null));
        System.out.println("пользователь написал сообщение: " + logParser.getDatesWhenUserWroteMessage("Vasya Pupkin", null, null));
        System.out.println("пользователь скачал плагин: " + logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null));
        System.out.println("пользователь залогинился впервые за указанный период: " + logParser.getDateWhenUserLoggedFirstTime("Vasya Pupkin", null, null));
        System.out.println("пользователь впервые попытался решить определенную задачу: " + logParser.getDateWhenUserSolvedTask("Vasya Pupkin", 1, null, null));
        System.out.println("пользователь решил определенную задачу: " + logParser.getDateWhenUserDoneTask("Vasya Pupkin", 1, null, null));
        System.out.println();
        System.out.println("количество различных типов событий за указанный период: " + logParser.getNumberOfAllEvents(null, null));
        System.out.println("события за указанный период: " + logParser.getAllEvents(null, null));
        System.out.println("события, которые происходили с указанного IP: " + logParser.getEventsForIP("146.34.15.55", null, null));
        System.out.println("события, которые инициировал определенный пользователь: " + logParser.getEventsForUser("Vasya Pupkin", null, null));
        System.out.println("события, которые не выполнились: " + logParser.getFailedEvents(null, null));
        System.out.println("события, которые завершились ошибкой: " + logParser.getErrorEvents(null, null));
        System.out.println("количество попыток решить определенную задачу: " + logParser.getNumberOfAttemptToSolveTask(18, null, null));
        System.out.println("количество успешных решений определенной задачи: " + logParser.getNumberOfSuccessfulAttemptToSolveTask(48, null, null));
        System.out.println("количество попыток решить задачу:" + logParser.getAllSolvedTasksAndTheirNumber(null, null));
        System.out.println("сколько раз ее решили:" + logParser.getAllDoneTasksAndTheirNumber(null, null));
        System.out.println("уникальные IP из лога по запросу get ip:" + logParser.execute("get ip"));
        System.out.println("уникальные Users из лога по запросу get user:" + logParser.execute("get user"));
        System.out.println("уникальные Dates из лога по запросу get date:" + logParser.execute("get date"));
        System.out.println("уникальные Events из лога по запросу get event:" + logParser.execute("get event"));
        System.out.println("уникальные Status из лога по запросу get status:" + logParser.execute("get status"));
        System.out.println("execute по комбинированному запросу: " + logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println("execute по комбинированному запросу: " + logParser.execute("get event for user = \"Vasya Pupkin\""));
        System.out.println("execute по комбинированному запросу с датами: " + logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between\n" + "\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}
