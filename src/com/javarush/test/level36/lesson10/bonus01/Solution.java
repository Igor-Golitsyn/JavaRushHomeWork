package com.javarush.test.level36.lesson10.bonus01;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }
    public static void main(String[] args) throws ClassNotFoundException {
        //Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        //Solution solution = new Solution("c:\\Users\\Игорь\\YandexDisk\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second\\");
        Solution solution = new Solution(args[0]);
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }
    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName).listFiles();
        for (File ff : files) {
            hiddenClasses.add(new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    try {
                        byte[] bytes = Files.readAllBytes(Paths.get(name));
                        return defineClass(null, bytes, 0, bytes.length);
                    }
                    catch (IOException e) {
                        throw new ClassNotFoundException();
                    }
                }
            }.loadClass(ff.getAbsolutePath()));
        }
    }
    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor con : constructors) {
                        con.setAccessible(true);
                        try {
                            return (HiddenClass) con.newInstance();
                        }
                        catch (Exception e) {
                        }
                    }
                }
                catch (Exception e) {
                }
            }
        }
        return null;
    }
}
