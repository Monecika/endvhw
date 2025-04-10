import ceiti.md.models.MyNode;

import java.util.*;

public class StudentLinkedList<T> implements List<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public StudentLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean contains(Object o) {
        MyNode<T> current = head;
        while (current != null) {
            if (current.getData()
                       .equals(o)) return true;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) throw new NoSuchElementException("No such element");
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        T[] arr = (T[]) new Object[size];
        int i = 0;
        MyNode<T> current = head;
        while (current != null) {
            arr[i++] = current.getData();
            current.getNext();
        }
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) return (T1[]) new Object[size];

        int i = 0;
        MyNode<T> current = head;
        while (current != null) {
            a[i++] = (T1) current.getData();
            current = current.getNext();
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        MyNode<T> newNode = new MyNode<>(t);
        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        MyNode<T> current = head;
        while (current != null) {
            if (current.getData()
                       .equals(o)) {
                if (current == head && tail == current) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = current.getNext();
                    head.setPrev(null);
                } else if (current == tail) {
                    tail = current.getPrev();
                    tail.setNext(null);
                } else {
                    MyNode<T> next = current.getNext();
                    MyNode<T> prev = current.getPrev();
                    prev.setNext(next);
                    next.setPrev(prev);
                }
                size--;
                return true;
            }
            current.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) if (!contains(item)) return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T item : c) add(item);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) remove(item);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object item : c) if (!contains(item)) remove(item);

        return true;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }

}
