package ceiti.md.models;

import java.util.Map;

public class MyEntry<T, K> implements Map.Entry<T, K> {
    private final T key;
    private K value;

    public MyEntry(T key, K value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public T getKey() {
        return key;
    }

    @Override
    public K getValue() {
        return value;
    }

    @Override
    public K setValue(K value) {
        this.value = value;
        return value;
    }

}
