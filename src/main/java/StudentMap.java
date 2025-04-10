import ceiti.md.models.MyEntry;

import java.util.*;

public class StudentMap<T, K> implements Map<T, K> {
    private final List<MyEntry<T, K>> entryList;

    public StudentMap() {
        this.entryList = new ArrayList<>();
    }

    @Override
    public int size() {
        return entryList.size();
    }

    @Override
    public boolean isEmpty() {
        return entryList.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for (MyEntry<T, K> entry : entryList) {
            if (entry.getKey()
                     .equals(key)) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (MyEntry<T, K> entry : entryList) {
            if (entry.getValue()
                     .equals(value)) return true;
        }
        return false;
    }

    @Override
    public K get(Object key) {
        for (MyEntry<T, K> entry : entryList) {
            if (entry.getKey()
                     .equals(key)) return entry.getValue();
        }
        return null;
    }

    @Override
    public K put(T key, K value) {
        for (MyEntry<T, K> entry : entryList) {
            if (entry.getKey()
                     .equals(key)) {
                K oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        entryList.add(new MyEntry<>(key, value));
        return null;
    }

    @Override
    public K remove(Object key) {
        Iterator<MyEntry<T, K>> iterator = entryList.iterator();
        while (iterator.hasNext()) {
            MyEntry<T, K> entry = iterator.next();
            if (entry.getKey()
                     .equals(key)) {
                K value = entry.getValue();
                iterator.remove();
                return value;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends T, ? extends K> m) {
        for (Entry<? extends T, ? extends K> item : m.entrySet()) {
            put(item.getKey(), item.getValue());
        }
    }

    @Override
    public void clear() {
        entryList.clear();
    }

    @Override
    public Set<T> keySet() {
        Set<T> keys = new HashSet<>();
        for (MyEntry<T, K> entry : entryList) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Collection<K> values() {
        List<K> values = new ArrayList<>();
        for (MyEntry<T, K> entry : entryList) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public Set<Entry<T, K>> entrySet() {
        return new HashSet<>(entryList);
    }
}
