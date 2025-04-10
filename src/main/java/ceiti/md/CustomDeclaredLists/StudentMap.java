package ceiti.md.CustomDeclaredLists;

import ceiti.md.MyEntry;

import java.util.*;

public class StudentMap<T, K> implements Map<T, K> {
    private final List<MyEntry<T, K>> entries;

    public StudentMap() {
        entries = new ArrayList<>();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (MyEntry<T, K> entry : entries) {
            if (entry.getKey()
                     .equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (MyEntry<T, K> entry : entries) {
            if (entry.getValue()
                     .equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public K get(Object key) {
        for (MyEntry<T, K> entry : entries) {
            if (entry.getKey()
                     .equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public K put(T key, K value) {
        for (MyEntry<T, K> entry : entries) {
            if (entry.getKey()
                     .equals(key)) {
                K oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        entries.add(new MyEntry<>(key, value));
        return null;
    }

    @Override
    public K remove(Object key) {
        Iterator<MyEntry<T, K>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            MyEntry<T, K> entry = iterator.next();
            if (entry.getKey()
                     .equals(key)) {
                iterator.remove();
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends T, ? extends K> m) {
        for (Entry<? extends T, ? extends K> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public Set<T> keySet() {
        Set<T> keys = new HashSet<>();
        for (MyEntry<T, K> entry : entries) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Collection<K> values() {
        List<K> values = new ArrayList<>();
        for (MyEntry<T, K> entry : entries) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<T, K>> entrySet() {
        Set<Map.Entry<T, K>> entrySet = new HashSet<>();
        for (MyEntry<T, K> entry : entries) {
            entrySet.add(entry);
        }
        return entrySet;
    }
}
