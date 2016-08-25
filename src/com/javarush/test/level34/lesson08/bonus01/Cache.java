package com.javarush.test.level34.lesson08.bonus01;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;
public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here
    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (!cache.containsKey(key)) {
            Class<K>[] param = new Class[]{key.getClass()};
            Constructor<V> constructor = clazz.getConstructor(param);
            V temp = constructor.newInstance(key);
            cache.put(key,temp);
        }
        return cache.get(key);
    }
    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public int size() {
        return cache.size();
    }
}
