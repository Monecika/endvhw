package ceiti.md.CustomDeclaredLists;

import ceiti.md.Student;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList implements List<Student> {

    private Node<Student> head;
    private Node<Student> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
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
        if (o == null) return false;
        Node<Student> current = head;
        while (current != null) {
            if (current.student.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<Student>() {
            private Node<Student> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Student next() {
                Student student = current.student;
                current = current.next;
                return student;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<Student> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.student;
            current = current.next;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass()
                                                           .getComponentType(), size);
        }
        Node<Student> current = head;
        int i = 0;
        while (current != null) {
            a[i++] = (T) current.student;
            current = current.next;
        }
        return a;
    }

    @Override
    public boolean add(Student student) {
        Node<Student> newNode = new Node<>(student);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) return false;
        Node<Student> current = head;
        while (current != null) {
            if (current.student.equals(o)) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void removeNode(Node<Student> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next; // node is head
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev; // node is tail
        }
        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Student> c) {
        boolean modified = false;
        for (Student student : c) {
            if (add(student)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Student> c) {
        throw new UnsupportedOperationException("addAll not implemented for specific index.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object obj : c) {
            if (remove(obj)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node<Student> current = head;
        while (current != null) {
            if (!c.contains(current.student)) {
                remove(current.student);
                modified = true;
            }
            current = current.next;
        }
        return modified;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public Student get(int index) {
        checkIndex(index);
        Node<Student> current = getNodeAt(index);
        return current.student;
    }

    @Override
    public Student set(int index, Student element) {
        checkIndex(index);
        Node<Student> current = getNodeAt(index);
        Student oldStudent = current.student;
        current.student = element;
        return oldStudent;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private Node<Student> getNodeAt(int index) {
        Node<Student> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public void add(int index, Student element) {
        checkIndex(index);
        if (index == size) {
            add(element); // append to the end
        } else {
            Node<Student> newNode = new Node<>(element);
            Node<Student> current = getNodeAt(index);
            if (current.prev != null) {
                current.prev.next = newNode;
            } else {
                head = newNode; // new node is the head
            }
            newNode.prev = current.prev;
            current.prev = newNode;
            newNode.next = current;
            size++;
        }
    }

    @Override
    public Student remove(int index) {
        checkIndex(index);
        Node<Student> current = getNodeAt(index);
        Student removedStudent = current.student;
        removeNode(current);
        return removedStudent;
    }

    @Override
    public int indexOf(Object o) {
        Node<Student> current = head;
        int index = 0;
        while (current != null) {
            if (current.student.equals(o)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<Student> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.student.equals(o)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<Student> listIterator() {
        return new ListIterator<Student>() {
            private Node<Student> current = head;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Student next() {
                Student student = current.student;
                current = current.next;
                currentIndex++;
                return student;
            }

            @Override
            public boolean hasPrevious() {
                return current != null && current.prev != null;
            }

            @Override
            public Student previous() {
                current = current.prev;
                currentIndex--;
                return current.student;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation not supported.");
            }

            @Override
            public void set(Student student) {
                current.student = student;
            }

            @Override
            public void add(Student student) {
                throw new UnsupportedOperationException("Add operation not supported.");
            }
        };
    }

    @Override
    public ListIterator<Student> listIterator(int index) {
        checkIndex(index); // Check if the index is valid

        return new ListIterator<Student>() {
            private Node<Student> current = getNodeAt(index); // Start at the given index
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Student next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Student student = current.student;
                current = current.next;
                currentIndex++;
                return student;
            }

            @Override
            public boolean hasPrevious() {
                return current != null && current.prev != null;
            }

            @Override
            public Student previous() {
                if (!hasPrevious()) {
                    throw new java.util.NoSuchElementException();
                }
                current = current.prev;
                currentIndex--;
                return current.student;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation not supported.");
            }

            @Override
            public void set(Student student) {
                current.student = student;
            }

            @Override
            public void add(Student student) {
                throw new UnsupportedOperationException("Add operation not supported.");
            }
        };
    }

    @Override
    public List<Student> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        List<Student> sublist = new LinkedList(); // New LinkedList to hold the sublist

        Node<Student> current = getNodeAt(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(current.student);
            current = current.next;
        }

        return sublist;
    }

    private class Node<T> {
        T student;
        Node<T> next;
        Node<T> prev;

        Node(T student) {
            this.student = student;
            this.next = null;
            this.prev = null;
        }
    }
}
