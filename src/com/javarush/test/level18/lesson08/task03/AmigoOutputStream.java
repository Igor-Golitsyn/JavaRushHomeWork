package com.javarush.test.level18.lesson08.task03;
import java.io.*;
import java.nio.channels.FileChannel;
/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/
public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";
    public AmigoOutputStream(FileOutputStream original) throws IOException {
        super(fileName);
    }
    public AmigoOutputStream(String s) throws FileNotFoundException {
        super(s);
    }
    public AmigoOutputStream(String s, boolean b) throws FileNotFoundException {
        super(s, b);
    }
    public AmigoOutputStream(File file) throws FileNotFoundException {
        super(file);
    }
    public AmigoOutputStream(File file, boolean b) throws FileNotFoundException {
        super(file, b);
    }
    public AmigoOutputStream(FileDescriptor fileDescriptor) {
        super(fileDescriptor);
    }
    @Override
    public void write(int i) throws IOException {
        super.write(i);
    }
    @Override
    public void write(byte[] bytes) throws IOException {
        super.write(bytes);
    }
    @Override
    public void write(byte[] bytes, int i, int i1) throws IOException {
        super.write(bytes, i, i1);
    }
    @Override
    public void close() throws IOException {
        super.flush();
        String fin = "JavaRush © 2012-2013 All rights reserved.";
        byte[] bb = fin.getBytes();
        super.write(bb);
        super.close();
    }
    @Override
    public FileChannel getChannel() {
        return super.getChannel();
    }
    @Override
    protected void finalize() throws IOException {
        super.finalize();
    }
    public static void main(String[] args) throws FileNotFoundException,IOException {

            new AmigoOutputStream(new FileOutputStream(fileName));

    }
}

