package ceiti.md.CustomDeclaredLists;

import ceiti.md.Node;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class DequeList<T> implements Deque<T> {
    private Node<T> front;
    private Node<T> tail;
    private int size;

    @Override
    public void addFirst(T t) {
        Node<T> newNode = new Node<>(t);
        if (front == null) {
            front = tail = newNode;
        } else {
            newNode.setNext(front);
            front.setPrev(newNode);
            front = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T t) {
        Node<T> newNode = new Node<>(t);
        if (tail == null) {
            front = tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    @Override
    public T removeFirst() {
        if (front == null) return null;
        T data = front.getData();
        front = front.getNext();
        if (front != null) front.setPrev(null);
        else tail = null;
        size--;
        return data;
    }

    @Override
    public T removeLast() {
        if (tail == null) return null;
        T data = tail.getData();
        tail = tail.getPrev();
        if (tail != null) tail.setNext(null);
        else front = null;
        size--;
        return data;
    }

    @Override
    public T pollFirst() {
        return removeFirst();
    }

    @Override
    public T pollLast() {
        return removeLast();
    }

    @Override
    public T getFirst() {
        if (front == null) return null;
        return front.getData();
    }

    @Override
    public T getLast() {
        if (tail == null) return null;
        return tail.getData();
    }

    @Override
    public T peekFirst() {
        if (front == null) return null;
        return front.getData();
    }

    @Override
    public T peekLast() {
        if (tail == null) return null;
        return tail.getData();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        for (Node<T> node = front; node != null; node = node.getNext()) {
            if (removeOccurrence(o, (Node<T>) node)) return true;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        for (Node<T> node = tail; node != null; node = node.getPrev()) {
            if (removeOccurrence(o, (Node<T>) node)) return true;
        }
        return false;
    }

    private boolean removeOccurrence(Object o, Node<T> node) {
        if (node.getData().equals(o)) {
            if (node.getPrev() != null) node.getPrev().setNext(node.getNext());
            if (node.getNext() != null) node.getNext().setPrev(node.getPrev());
            if (node == front) front = node.getNext();
            if (node == tail) tail = node.getPrev();
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T t : c) {
            addLast(t);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified |= removeFirstOccurrence(o);
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (Node<T> node = front; node != null; node = node.getNext()) {
            if (!c.contains(node.getData())) {
                removeFirstOccurrence(node.getData());
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        front = tail = null;
        size = 0;
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Node<T> node = front; node != null; node = node.getNext()) {
            if (node.getData().equals(o)) return true;
        }
        return false;
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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node<T> node = front; node != null; node = node.getNext()) {
            array[i++] = node.getData();
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Node<T> node = front; node != null; node = node.getNext()) {
            a[i++] = (T1) node.getData();
        }
        return a;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            private Node<T> current = tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.getData();
                current = current.getPrev();
                return data;
            }
        };
    }
}
