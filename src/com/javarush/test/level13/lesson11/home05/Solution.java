package com.javarush.test.level13.lesson11.home05;
public class Solution {
    public static void main(String[] args) throws Exception {
        System.out.println(Matrix.NEO);
    }
    static class Matrix {
        public static DBObject NEO = new User().initializeIdAndName(1, "Neo");
    }
    interface DBObject {
        DBObject initializeIdAndName(long id, String name);
    }
    static class User implements DBObject{
        long id;
        String name;
        @Override
        public String toString() {
            return String.format("User has name %s, id = %d", name, id);
        }
        @Override
        public DBObject initializeIdAndName(long id, String name) {
            this.name=name;
            this.id=id;
            return this;
        }
    }
}
