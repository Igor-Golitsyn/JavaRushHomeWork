package com.javarush.test.level20.lesson10.home03;
import java.io.*;
/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable{
    private static final long serialVersionUID = 1L;
    public static class A {
        protected String name = "A";
        public A() {}
        public A(String name) {
            this.name += name;
        }
    }
    public class B extends A implements Serializable {
        private static final long serialVersionUID = 3L;
        public B(String name) {
            super(name);
            this.name += name;
        }
        private void readObject(ObjectInputStream inputStream ) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
            name=(String)inputStream.readObject();
        }
        private void writeObject(ObjectOutputStream outputStream) throws IOException {
            outputStream.defaultWriteObject();
            outputStream.writeObject(name);
        }
    }
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        Solution.B bbb = solution.new B("VASY");
        System.out.println(bbb.name);
        File file = File.createTempFile("TEMP", null);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(bbb);
        outputStream.close();
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        B load = (B) inputStream.readObject();
        System.out.println(load.name);
        inputStream.close();
        file.delete();
    }
}
