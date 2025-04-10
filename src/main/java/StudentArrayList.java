import java.util.*;

public class StudentArrayList<T> implements List<T> {
    private T[] arr;
    private int size;

    public StudentArrayList() {
        this.arr = (T[]) new Object[10];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T item : arr) {
            if (item.equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) return (T1[]) new Object[size];

        for (int i = 0; i < size; i++)
            a[i] = (T1) arr[i];
        return a;
    }

    @Override
    public boolean add(T t) {
        arr[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = indexOf(o); i < size - 1; i++) {
            arr[i] = arr[i++];
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) if (indexOf(c) == -1) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T item : c) {
            checkAvailability(size);
            arr[size++] = item;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        while (size + c.size() > arr.length) checkAvailability(size + c.size());
        for (T item : c) {
            arr[index + c.size()] = arr[index];
            arr[index++] = item;
        }
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) remove(c);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object item : c) if (!contains(c)) remove(item);
        return true;
    }

    @Override
    public void clear() {
        arr = (T[]) new Object[10];
    }

    @Override
    public T get(int index) {
        return arr[index];
    }

    @Override
    public T set(int index, T element) {
        T oldElement = arr[index];
        arr[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        if (arr[index] != null) {
            size++;
            for (int i = size - 1; i >= index; i--) {
                arr[i] = arr[i - 1];
            }
        }
        arr[index] = element;
    }

    @Override
    public T remove(int index) {
        T value = arr[index];
        remove(arr[index]);

        return value;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) if (arr[i].equals(0)) return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int current = index;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("No such element");
                return arr[current++];
            }

            @Override
            public boolean hasPrevious() {
                return current > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) throw new NoSuchElementException("No such element");
                return arr[current--];
            }

            @Override
            public int nextIndex() {
                return current++;
            }

            @Override
            public int previousIndex() {
                return current--;
            }

            @Override
            public void remove() {
                StudentArrayList.this.remove(current--);
            }

            @Override
            public void set(T t) {
                arr[current] = t;
            }

            @Override
            public void add(T t) {
                StudentArrayList.this.add(current, t);
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        StudentArrayList<T> list = new StudentArrayList<>();
        list.addAll(Arrays.asList(arr)
                          .subList(fromIndex, toIndex));
        return list;
    }

    private void checkAvailability(int size) {
        if (size >= arr.length) Arrays.copyOf(arr, size * 2);
    }
}
