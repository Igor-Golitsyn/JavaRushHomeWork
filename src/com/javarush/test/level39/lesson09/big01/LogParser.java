package com.javarush.test.level39.lesson09.big01;
import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.javarush.test.level39.lesson09.big01.Event.*;
public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    //************************************* IP Query *******************************************************************
    private Path logDir;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public LogParser(Path logDir) {
        this.logDir = logDir;
    }
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }
    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getUniqElements(after, before, Target.IPS);
    }
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getUniqElements(after, before, Target.IPS_USER, user);
    }
    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getUniqElements(after, before, Target.IPS_USER_EVENT, event);
    }
    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getUniqElements(after, before, Target.IPS_STATUS, status);
    }
    private Set<String> getUniqElements(Date after, Date before, Target target, Object... objects) {
        Set<String> uniqElement = new HashSet<>();
        if (after == null) after = new Date(0);
        if (before == null) before = new Date(Long.MAX_VALUE);
        File[] listFiles = logDir.toFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });
        for (File file : listFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                List<String> lines = new ArrayList<>();
                while (reader.ready()) {
                    lines.add(reader.readLine());
                }
                for (String line : lines) {
                    String[] element = line.split("\t");
                    Date date = sdf.parse(element[2]);
                    if ((date.after(after) || date.equals(after)) && (date.before(before) || date.equals(before))) {
                        switch (target) {
                            case IPS:
                                uniqElement.add(element[0]);
                                break;
                            case IPS_USER:
                                String user = (String) objects[0];
                                if (user.equalsIgnoreCase(element[1])) uniqElement.add(element[0]);
                                break;
                            case IPS_USER_EVENT:
                                Event event = (Event) objects[0];
                                if (element[3].startsWith(event.name())) uniqElement.add(element[0]);
                                break;
                            case IPS_STATUS:
                                Status status = (Status) objects[0];
                                if (element[4].startsWith(status.name())) uniqElement.add(element[0]);
                                break;
                            case USERS:
                                uniqElement.add(element[1]);
                                break;
                            case USERS_FROM_IP:
                                String ip = (String) objects[0];
                                if (element[0].equals(ip)) {
                                    uniqElement.add(element[1]);
                                }
                                break;
                            case USER_EVENT:
                                event = (Event) objects[0];
                                if (objects.length == 2) {
                                    int task = (int) objects[1];
                                    if (element[3].startsWith(event.name()) && element[3].endsWith(task + "")) {
                                        uniqElement.add(element[1]);
                                    }
                                } else {
                                    if (element[3].startsWith(event.name())) uniqElement.add(element[1]);
                                }
                                break;
                            case ANY_USER_ANY_EVENT:
                                uniqElement.add(element[3].replaceAll("[0-9]", "").trim());
                                break;
                            case EVENTS_FOR_IP:
                                ip = (String) objects[0];
                                if (ip.equals(element[0])) {
                                    uniqElement.add(element[3].replaceAll("[0-9]", "").trim());
                                }
                                break;
                            case EVENTS_FOR_STATUS:
                                status = (Status) objects[0];
                                if (element[4].equals(status.name())) {
                                    uniqElement.add(element[3].replaceAll("[0-9]", "").trim());
                                }
                                break;
                            case EVENTS_SOLVE_DONE:
                                String number = element[3].replaceAll("[^0-9]", "").trim();
                                if (!number.isEmpty()) {
                                    uniqElement.add(number);
                                }
                                break;
                            case DATES:
                                uniqElement.add(element[2]);
                                break;
                            case STATUS:
                                uniqElement.add(element[4]);
                        }
                    }
                }
            }
            catch (Exception e) {
            }
        }
        return uniqElement;
    }
    //*********************************** IP Query end *****************************************************************
    //************************************ User Query ******************************************************************
    @Override
    public Set<String> getAllUsers() {
        return getUniqElements(null, null, Target.USERS);
    }
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getUniqElements(after, before, Target.USERS).size();
    }
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getDuplicateElements(after, before, Target.USER_ANY_EVENT, user).size();
    }
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getUniqElements(after, before, Target.USERS_FROM_IP, ip);
    }
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUniqElements(after, before, Target.USER_EVENT, Event.LOGIN);
    }
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUniqElements(after, before, Target.USER_EVENT, DOWNLOAD_PLUGIN);
    }
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUniqElements(after, before, Target.USER_EVENT, WRITE_MESSAGE);
    }
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUniqElements(after, before, Target.USER_EVENT, SOLVE_TASK);
    }
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUniqElements(after, before, Target.USER_EVENT, SOLVE_TASK, task);
    }
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUniqElements(after, before, Target.USER_EVENT, DONE_TASK);
    }
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUniqElements(after, before, Target.USER_EVENT, DONE_TASK, task);
    }
    private List<String> getDuplicateElements(Date after, Date before, Target target, Object... objects) {
        List<String> elements = new ArrayList<>();
        if (after == null) after = new Date(0);
        if (before == null) before = new Date(Long.MAX_VALUE);
        File[] listFiles = logDir.toFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });
        for (File file : listFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                List<String> lines = new ArrayList<>();
                while (reader.ready()) {
                    lines.add(reader.readLine());
                }
                for (String line : lines) {
                    String[] element = line.split("\t");
                    Date date = sdf.parse(element[2]);
                    if ((date.after(after) || date.equals(after)) && (date.before(before) || date.equals(before))) {
                        switch (target) {
                            case ANY_USER_ANY_EVENT:
                                elements.add(element[3]);
                                break;
                            case USER_ANY_EVENT:
                                String userForEvent = (String) objects[0];
                                if (userForEvent.equalsIgnoreCase(element[1])) elements.add(element[3]);
                                break;
                            case USER_EVENT:
                                userForEvent = (String) objects[0];
                                Event event = (Event) objects[1];
                                if (objects.length == 3) {
                                    int task = (int) objects[2];
                                    if (element[3].startsWith(event.name()) && element[3].endsWith(task + "") && element[1].equalsIgnoreCase(userForEvent)) {
                                        elements.add(element[2]);
                                    }
                                } else {
                                    if (element[3].startsWith(event.name()) && element[1].equalsIgnoreCase(userForEvent))
                                        elements.add(element[2]);
                                }
                                break;
                            case STATUS:
                                Status status = (Status) objects[0];
                                if (element[4].equals(status.name())) {
                                    elements.add(element[2]);
                                }
                                break;
                            case EVENTS_SOLVE_TASK_NUMBER:
                                int number = (int) objects[0];
                                if (element[3].startsWith(Event.SOLVE_TASK.name()) && element[3].endsWith(String.valueOf(number))) {
                                    elements.add(element[3]);
                                }
                                break;
                            case EVENTS_DONE_TASK_NUMBER:
                                number = (int) objects[0];
                                if (element[3].startsWith(Event.DONE_TASK.name()) && element[3].endsWith(String.valueOf(number))) {
                                    elements.add(element[3]);
                                }
                                break;
                        }
                    }
                }
            }
            catch (Exception e) {
            }
        }
        return elements;
    }
    //************************************ User Query end **************************************************************
    //*************************************** Date Query ***************************************************************
    private Date getFirstDate(Set<Date> dateSet) {
        if (dateSet.size() == 0) return null;
        Date firstDate = new Date(Long.MAX_VALUE);
        for (Date date : dateSet) {
            if (date.before(firstDate)) firstDate = date;
        }
        return firstDate;
    }
    private Set<Date> convertStringToDate(List<String> listString) {
        Set<Date> dateSet = new HashSet<>();
        for (String str : listString) {
            try {
                dateSet.add(sdf.parse(str));
            }
            catch (ParseException e) {
            }
        }
        return dateSet;
    }
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return convertStringToDate(getDuplicateElements(after, before, Target.USER_EVENT, user, event));
    }
    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return convertStringToDate(getDuplicateElements(after, before, Target.STATUS, Status.FAILED));
    }
    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return convertStringToDate(getDuplicateElements(after, before, Target.STATUS, Status.ERROR));
    }
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> dateSet = convertStringToDate(getDuplicateElements(after, before, Target.USER_EVENT, user, Event.LOGIN));
        return getFirstDate(dateSet);
    }
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> dateSet = convertStringToDate(getDuplicateElements(after, before, Target.USER_EVENT, user, Event.SOLVE_TASK, task));
        return getFirstDate(dateSet);
    }
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> dateSet = convertStringToDate(getDuplicateElements(after, before, Target.USER_EVENT, user, Event.DONE_TASK, task));
        return getFirstDate(dateSet);
    }
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return convertStringToDate(getDuplicateElements(after, before, Target.USER_EVENT, user, Event.WRITE_MESSAGE));
    }
    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return convertStringToDate(getDuplicateElements(after, before, Target.USER_EVENT, user, Event.DOWNLOAD_PLUGIN));
    }
    //*************************************** Date Query end ***********************************************************
    //*************************************** Event Query **************************************************************
    private Set<Event> getEventsFromStings(Set<String> events) {
        Set<Event> eventSet = new HashSet<>();
        for (String str : events) {
            eventSet.add(Event.valueOf(str));
        }
        return eventSet;
    }
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }
    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<String> events = getUniqElements(after, before, Target.ANY_USER_ANY_EVENT);
        return getEventsFromStings(events);
    }
    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<String> events = getUniqElements(after, before, Target.EVENTS_FOR_IP, ip);
        return getEventsFromStings(events);
    }
    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        List<String> stringsEvents = getDuplicateElements(after, before, Target.USER_ANY_EVENT, user);
        Set<Event> eventSet = new HashSet<>();
        for (String str : stringsEvents) {
            eventSet.add(Event.valueOf(str.replaceAll("[0-9]", "").trim()));
        }
        return eventSet;
    }
    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<String> events = getUniqElements(after, before, Target.EVENTS_FOR_STATUS, Status.FAILED);
        return getEventsFromStings(events);
    }
    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<String> events = getUniqElements(after, before, Target.EVENTS_FOR_STATUS, Status.ERROR);
        return getEventsFromStings(events);
    }
    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return getDuplicateElements(after, before, Target.EVENTS_SOLVE_TASK_NUMBER, task).size();
    }
    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return getDuplicateElements(after, before, Target.EVENTS_DONE_TASK_NUMBER, task).size();
    }
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Set<String> setSolveDone = getUniqElements(after, before, Target.EVENTS_SOLVE_DONE);
        Map<Integer, Integer> tasks = new HashMap<>();
        for (String str : setSolveDone) {
            int number = Integer.parseInt(str);
            int solvedList = getDuplicateElements(after, before, Target.EVENTS_SOLVE_TASK_NUMBER, number).size();
            if (solvedList != 0) tasks.put(number, solvedList);
        }
        return tasks;
    }
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Set<String> setSolveDone = getUniqElements(after, before, Target.EVENTS_SOLVE_DONE);
        Map<Integer, Integer> tasks = new HashMap<>();
        for (String str : setSolveDone) {
            int number = Integer.parseInt(str);
            int solvedList = getDuplicateElements(after, before, Target.EVENTS_DONE_TASK_NUMBER, number).size();
            if (solvedList != 0) tasks.put(number, solvedList);
        }
        return tasks;
    }
    //*************************************** Event Query end **********************************************************
    //****************************************** QL Query **************************************************************
    @Override
    public Set<Object> execute(String query) {
        Date after = new Date(0);
        Date before = new Date(Long.MAX_VALUE);
        if (query.contains("and date between")) {
            int numLast = query.lastIndexOf(34);
            int numPreLast = query.lastIndexOf(34, numLast - 1) + 1;
            String dateBefore = query.substring(numPreLast, numLast);
            query = query.replace("\"" + dateBefore + "\"", "");
            numLast = query.lastIndexOf(34);
            numPreLast = query.lastIndexOf(34, numLast - 1) + 1;
            String dateAfter = query.substring(numPreLast, numLast);
            query = query.replace("\"" + dateAfter + "\"", "");
            query = query.replace("and date between", "");
            query = query.trim();
            try {
                after = sdf.parse(dateAfter);
                before = sdf.parse(dateBefore);
            }
            catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        String[] querys = query.split(" ");
        if (querys.length == 2) {
            switch (query) {
                case "get ip":
                    return (Set) getUniqueIPs(null, null);
                case "get user":
                    return (Set) getAllUsers();
                case "get date":
                    Set<String> datesSetStrings = getUniqElements(null, null, Target.DATES);
                    Set<Date> datesSet = new HashSet<>();
                    for (String str : datesSetStrings) {
                        try {
                            datesSet.add(sdf.parse(str));
                        }
                        catch (ParseException e) {
                        }
                    }
                    return (Set) datesSet;
                case "get event":
                    return (Set) getAllEvents(null, null);
                case "get status":
                    Set<String> statusSetStrings = getUniqElements(null, null, Target.STATUS);
                    Set<Status> statusSet = new HashSet<>();
                    for (String str : statusSetStrings) {
                        statusSet.add(Status.valueOf(str));
                    }
                    return (Set) statusSet;
            }
        } else {
            String field1 = querys[1];
            int numberReturnField = getNumberOfField(field1);
            String field2 = querys[3];
            int numberQueryField = getNumberOfField(field2);
            String value = query.substring(query.indexOf(34) + 1, query.lastIndexOf(34));
            Set<Object> rezult = new HashSet<>();
            File[] listFiles = logDir.toFile().listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".log");
                }
            });
            for (File file : listFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while (reader.ready()) {
                        String line = reader.readLine();
                        String[] element = line.split("\t");
                        Date date = sdf.parse(element[2]);
                        if ((date.after(after) || date.equals(after)) && (date.before(before) || date.equals(before))) {
                            if (element[numberQueryField].contains(value)) {
                                switch (numberReturnField) {
                                    case 0:
                                    case 1:
                                        rezult.add(element[numberReturnField]);
                                        break;
                                    case 2:
                                        rezult.add(sdf.parse(element[numberReturnField]));
                                        break;
                                    case 3:
                                        rezult.add(Event.valueOf(element[numberReturnField].replaceAll("[0-9]", "").trim()));
                                        break;
                                    case 4:
                                        rezult.add(Status.valueOf(element[numberReturnField]));
                                        break;
                                }
                            }
                        }
                    }
                }
                catch (Exception e) {
                }
            }
            return rezult;
        }
        return null;
    }
    private int getNumberOfField(String str) {
        int numberField = -1;
        switch (str) {
            case "ip":
                numberField = 0;
                break;
            case "user":
                numberField = 1;
                break;
            case "date":
                numberField = 2;
                break;
            case "event":
                numberField = 3;
                break;
            case "status":
                numberField = 4;
                break;
        }
        return numberField;
    }
    private enum Target {
        IPS, IPS_USER, IPS_USER_EVENT, IPS_STATUS, USERS, USERS_FROM_IP, USER_EVENT, USER_ANY_EVENT, ANY_USER_ANY_EVENT, STATUS, EVENTS_FOR_IP, EVENTS_FOR_STATUS, EVENTS_SOLVE_TASK_NUMBER, EVENTS_DONE_TASK_NUMBER, EVENTS_SOLVE_DONE, DATES
    }
}
