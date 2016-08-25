package com.javarush.test.level37.lesson10.big01;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 * Created by Игорь on 21.07.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;
    public AmigoSet() {
        map = new HashMap<E, Object>();
    }
    public AmigoSet(Collection<? extends E> collection) {
        int initCapacity = (collection.size() / .75f) > 16 ? (int) (collection.size() / .75f) : 16;
        map = new HashMap<E, Object>(initCapacity);
        this.addAll(collection);
    }
    @Override
    public boolean add(E e) {
        map.put(e, PRESENT);
        return map.containsKey(e);
    }
    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
    @Override
    public int size() {
        return map.size();
    }
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }
    @Override
    public void clear() {
        map.clear();
    }
    @Override
    public AmigoSet<E> clone() {
        try {
            AmigoSet<E> amigoSet = (AmigoSet<E>) super.clone();
            amigoSet.map = (HashMap<E, Object>) map.clone();
            return amigoSet;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeObject(new HashSet<E>(map.keySet()));
        out.defaultWriteObject();
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        int capacity = (int) in.readObject();
        float loadFactor = (float) in.readObject();
        map = new HashMap<E, Object>(capacity, loadFactor);
        HashSet<E> hashSet = (HashSet<E>) in.readObject();
        for (E eee : hashSet) {
            map.put(eee, PRESENT);
        }
        in.defaultReadObject();
    }
}
