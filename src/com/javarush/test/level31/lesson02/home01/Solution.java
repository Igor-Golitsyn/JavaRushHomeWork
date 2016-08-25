package com.javarush.test.level31.lesson02.home01;
import java.io.*;
import java.util.*;
/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        //Создали файлы (Пункт 1)
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/"+"allFilesContent.txt");
        //Проверяем директорию на наличее такого файла
        if (allFilesContent.exists())
        {
            allFilesContent.delete();
        }
        //Список фаулов и дальнейшая обработка
        ArrayList<File> list = new ArrayList<>();
        //Удаляем файлы более 50кб (Пункты 2.1 и 2.2)
        seekMinFiles(path, list,resultFileAbsolutePath);
        //Сортируем компаратором (Пункт 2.2.1)
        Collections.sort(list, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });
        //Пишем данные в файло (Пункт 2.2.3)
        FileOutputStream fileOutputStream = new FileOutputStream(resultFileAbsolutePath);
        for (int i =0; i<list.size();i++) {
            FileInputStream fileInputStream = new FileInputStream(list.get(i));
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            if (i == list.size() - 1) {
                continue;
            } else {
                fileOutputStream.write("\r".getBytes());
                fileOutputStream.write("\n".getBytes());
            }
            fileInputStream.close();
        }
        fileOutputStream.close();
        //ReNameFile (Пункт 2.2.2)
        resultFileAbsolutePath.renameTo(allFilesContent);
        //Удаляем пустые директории (Пункт 2.3)
        delDirect(path);
    }
    // Методы поиска минимального файла
    public static void seekMinFiles (File file , ArrayList<File> list,File resultFileAbsolutePath){
        if(file.equals(resultFileAbsolutePath))return;
        if(file.isDirectory()){
            for(File directory : file.listFiles()){
                seekMinFiles(directory,list,resultFileAbsolutePath);
            }
        }else if (file.isFile()){
            if(file.length()>50)file.delete();
            else list.add(file);
        }
    }
    //Удаление пустых папок
    public static void delDirect(File file){
        if(file.isDirectory()){
            for(File direct: file.listFiles()) delDirect(direct);
            file.delete();
        }
    }
}
//**************************************** а вот мой код*************************************************
/*package com.javarush.test.level31.lesson02.home01;
        import java.io.*;
        import java.nio.file.Files;
        import java.util.*;
*//* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*//*
public class Solution {
    private File path;
    private File resultFileAbsolutePath;
    private List<File> filesInPath = new ArrayList<>();
    private void scanDir(File pathForScan) {
        for (File file : pathForScan.listFiles()) {
            if (file.isDirectory()) {
                scanDir(file.getAbsoluteFile());
            } else {filesInPath.add(file);}
        }
    }
    public void filterAndSort(int size) {
        Iterator<File> iterator = filesInPath.iterator();
        while (iterator.hasNext()) {
            File nextFile = iterator.next();
            if (nextFile.length() > size) {
                nextFile.delete();
                iterator.remove();
            }
        }
        Collections.sort(filesInPath, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }
    public void cleanDirectory() {
        cleanDir(path);
    }
    private void cleanDir(File pathForClean) {
        if (pathForClean.listFiles().length > 0) {
            for (File file : pathForClean.listFiles()) {
                if (file.isDirectory()) {
                    cleanDir(file);
                }
            }
        }
        if (pathForClean.listFiles().length == 0) {
            pathForClean.delete();
        }
    }
    public void writeRezult() {
        File fileNewName = new File(resultFileAbsolutePath.getParent() + "\\allFilesContent.txt");
        resultFileAbsolutePath.renameTo(fileNewName);
        resultFileAbsolutePath = fileNewName;
        for (int i = 0; i < filesInPath.size(); i++) {
            try {
                byte[] bytesOfFile = Files.readAllBytes(filesInPath.get(i).toPath());
                FileOutputStream fos = new FileOutputStream(resultFileAbsolutePath, true);
                fos.write(bytesOfFile);
                if (i != filesInPath.size() - 1) {
                    fos.write("\n".getBytes());
                }
                fos.flush();
                fos.close();
            }
            catch (IOException e) {
            }
        }
    }
    public void setPath(File path) {
        this.path = path;
    }
    public void setResultFileAbsolutePath(File resultFileAbsolutePath) {
        this.resultFileAbsolutePath = resultFileAbsolutePath;
    }
    public File getPath() {
        return path;
    }
    public static void main(String[] args) {
        Solution working = new Solution();
        working.setPath(new File(args[0]));
        working.setResultFileAbsolutePath(new File(args[1]));
        working.scanDir(working.getPath());
        working.filterAndSort(50);
        working.cleanDirectory();
        working.writeRezult();
    }
}*/
//*****************************************************************************************************************
//******************************* это для тестов******************************************************************
/*package com.javarush.test.level31.lesson02.home01;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.Random;
        import java.util.concurrent.ThreadLocalRandom;
*//**
 * Created by golit on 25.03.2016.
 *//*
public class Creation {
    private int elements;
    private int numFile = 0;
    private File path;
    public Creation(int elements, File path) {
        this.elements = elements;
        this.path = path;
    }
    public void makeFiles() {
        genFiles(path);
    }
    private void genFiles(File path) {
        for (int i = 0; i < elements / 5; i++) {
            char ch1 = (char) ThreadLocalRandom.current().nextInt(65, 91);
            char ch2 = (char) ThreadLocalRandom.current().nextInt(65, 91);
            char ch3 = (char) ThreadLocalRandom.current().nextInt(65, 91);
            char ch4 = (char) ThreadLocalRandom.current().nextInt(65, 91);
            char ch5 = (char) ThreadLocalRandom.current().nextInt(65, 91);
            if (new Random().nextBoolean()) {
                File dir = new File(path.getAbsolutePath() + "\\" + ch1 + ch2 + ch3 + ch4 + ch5);
                dir.mkdirs();
            } else {
                File file = new File(path.getAbsolutePath() + "\\" + ch1 + ch2 + ch3 + ch4 + ch5 + ".txt");
                try {
                    numFile++;
                    if (numFile > elements) return;
                    file.createNewFile();
                    writeDataToFile(file);
                }
                catch (IOException e) {
                    System.out.println("ERROR");
                    e.printStackTrace();
                }
            }
        }
        for (File file:path.listFiles()){
            if (file.isDirectory()){
                genFiles(file.getAbsoluteFile());
            }
        }
    }
    private void writeDataToFile(File file) throws IOException {
        FileOutputStream fos=new FileOutputStream(file);
        int sizeFile=ThreadLocalRandom.current().nextInt(100);
        for (int i = 0; i < sizeFile; i++) {
            fos.write(ThreadLocalRandom.current().nextInt(65,91));
        }
        fos.flush();
        fos.close();
    }
    public static void main(String[] args) throws IOException {
        Creation creation = new Creation(100, new File(args[0]));
        creation.makeFiles();
        String resultFileAbsolutePath=args[1];
        File file=new File(resultFileAbsolutePath);
        file.createNewFile();
    }
}*/
//***************************************************************************************************************