package com.javarush.test.level20.lesson10.home05;
import java.io.*;
import java.util.logging.Logger;
/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution {
    public static class Person implements Externalizable {
        private static final long serialVersionUID = 1L;
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;
        public Person() {
            this.greetingString = "Hello, ";
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeObject(country);
            out.writeObject(sex);
        }
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName=(String)in.readObject();
            lastName=(String)in.readObject();
            country=(String)in.readObject();
            sex=(Sex)in.readObject();
            fullName = String.format("%s, %s", lastName, firstName);
        }
    }
    enum Sex {
        MALE,
        FEMALE
    }

}
