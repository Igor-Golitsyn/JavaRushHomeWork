package com.javarush.test.level36.lesson04.big01.model;
import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.List;
/**
 * Created by Игорь on 10.06.2016.
 */
public class ModelData {
    private List<User> users;
    private User activeUser;
    private boolean displayDeletedUserList;
    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }
    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public User getActiveUser() {
        return activeUser;
    }
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}
