package com.javarush.test.level36.lesson04.big01.model;
/**
 * Created by Игорь on 10.06.2016.
 */
public interface Model {
    ModelData getModelData();
    void loadUsers();
    void loadDeletedUsers();
    void loadUserById(long userId);
    void deleteUserById(long id);
    void changeUserData(String name, long id, int level);
}
