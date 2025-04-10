package ceiti.md.CustomDeclaredLists;

import ceiti.md.Student;

import java.util.*;

public class StudentArrayList implements List<Student> {
    private Student[] data;
    private int size;

    public StudentArrayList() {
        this.data = new Student[10];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<Student>() {
            private int index = 0;
            private boolean canRemove = false;

            public boolean hasNext() {
                return index < size;
            }

            public Student next() {
                if (!hasNext()) throw new NoSuchElementException();
                canRemove = true;
                return data[index++];
            }

            public void remove() {
                if (!canRemove) throw new IllegalStateException();
                StudentArrayList.this.remove(--index);
                canRemove = false;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        }
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(Student student) {
        ensureCapacity();
        data[size++] = student;
        return true;
    }

    @Override
    public void add(int index, Student student) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = student;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends Student> c) {
        for (Student s : c) add(s);
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends Student> c) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Student[] arr = c.toArray(new Student[0]);
        int len = arr.length;
        ensureCapacity();
        System.arraycopy(data, index, data, index + len, size - index);
        System.arraycopy(arr, 0, data, index, len);
        size += len;
        return len > 0;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i >= 0) {
            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public Student remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Student removed = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) if (!contains(o)) return false;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) changed |= remove(o);
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(data[i])) {
                remove(i);
                i--;
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    @Override
    public Student get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }

    @Override
    public Student set(int index, Student element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Student old = data[index];
        data[index] = element;
        return old;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(data[i], o)) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<Student> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Student> listIterator(int index) {
        return new ListIterator<Student>() {
            private int current = index;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Student next() {
                if (!hasNext()) throw new NoSuchElementException();
                return data[current++];
            }

            @Override
            public boolean hasPrevious() {
                return current > 0;
            }

            @Override
            public Student previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                return data[--current];
            }

            @Override
            public int nextIndex() {
                return current;
            }

            @Override
            public int previousIndex() {
                return current - 1;
            }

            @Override
            public void remove() {
                StudentArrayList.this.remove(--current);
            }

            @Override
            public void set(Student o) {
                data[current - 1] = o;
            }

            @Override
            public void add(Student o) {
                StudentArrayList.this.add(current++, o);
                size++;
            }
        };
    }

    @Override
    public List<Student> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) throw new IndexOutOfBoundsException();
        StudentArrayList sublist = new StudentArrayList();
        sublist.addAll(Arrays.asList(data)
                             .subList(fromIndex, toIndex));
        return sublist;
    }
}
