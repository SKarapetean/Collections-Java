package Collections.Lists;

import Collections.MyCollection;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

//import java.util.function.Consumer;
public class MyArrayList<E> implements MyList<E> {
    //private final int defaultCapacity = 10;
    private int size;
    private Object[] elements;
    public MyArrayList() {
        this.elements = new Object[0];
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elements = new Object[capacity];
        } else {
            if (capacity == 0) {
                this.elements = new Object[0];
            }
            throw new IllegalArgumentException();
        }
    }

    public MyArrayList(MyCollection<? extends E> col) {
        Object[] tmp = col.toArray();

        if (tmp.length != 0) {
            this.size = tmp.length;
            if (col.getClass() == MyArrayList.class) {
                this.elements = tmp;
            } else {
                this.elements = Arrays.copyOf(tmp, this.size, Object[].class);
            }
        } else {
            this.elements = new Object[0];
        }
    }

    private void indexChecker(int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException();
        }
    }
    private Object[] grow() {
        return this.elements = Arrays.copyOf(this.elements, this.elements.length + 2);
    }

    private Object[] grow(int capacity) {
        return this.elements = Arrays.copyOf(this.elements, this.elements.length + capacity);
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object obj) {
        return this.indexOf(obj) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyItr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }
    @Override
    public boolean add(E data) {
        this.add(this.size, data);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        int i = this.indexOf(obj);

        if (i != -1) {
            this.remove(i);
            --this.size;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(MyCollection<?> col) {
        Iterator<?> itr = col.iterator();

        do{
            if (!itr.hasNext()) {
                return true;
            }
        } while (this.contains(itr.next()));

        return false;
    }

    @Override
    public boolean addAll(MyCollection<? extends E> col) {
        if (col == null) {
            return false;
        }

        Object[] arr = col.toArray();
        int sizeDef = arr.length - (this.elements.length - this.size);

         if (sizeDef == 0) {
             this.elements = this.grow();

             for (int i = 0; i < arr.length; ++i) {
                 this.add((E) arr[i]);
             }

             return true;
         } else if (sizeDef > 0) {
             this.elements = this.grow(sizeDef + 2);

             for (int i = 0; i < arr.length; ++i) {
                 this.add((E) arr[i]);
             }

             return true;
         } else {

             for (int i = 0; i < arr.length; ++i) {
                 this.add((E) arr[i]);
             }

             return true;
         }
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends E> col) {
        this.indexChecker(index);
        if (col == null) {
            return false;
        }

        Object[] arr = col.toArray();
        int sizeDef = arr.length - (this.elements.length - this.size);

        if (sizeDef == 0) {
            this.elements = this.grow();

            for (int i = 0; i < arr.length; ++i) {
                this.add(index + i, (E) arr[i]);
            }

            return true;
        } else if (sizeDef > 0) {
            this.elements = this.grow(sizeDef + 2);

            for (int i = 0; i < arr.length; ++i) {
                this.add(index + i, (E) arr[i]);
            }

            return true;
        } else {

            for (int i = 0; i < arr.length; ++i) {
                this.add(index + i, (E) arr[i]);
            }

            return true;
        }
    }

    @Override
    public boolean removeAll(MyCollection<?> col) {
        if (col == null) {
            throw new NullPointerException();
        }

        boolean b = false;
        Iterator<?> itr = col.iterator();

        while (itr.hasNext()) {
            Object el = itr.next();
            for (int i = 0; i < this.size; ++i) {
                if (el.equals(this.elements[i])) {
                    this.remove(i);
                    b = true;
                }
            }
        }

        return b;
    }

    @Override
    public boolean retainAll(MyCollection<?> col) {
        if (col == null) {
            throw new NullPointerException();
        }

        boolean changed = false;
        Iterator<E> thisItr = this.iterator();
        while (thisItr.hasNext()) {
            boolean b = false;
            Object el = thisItr.next();
            for (Object o : col) {
                if (o.equals(el)) {
                    b = true;
                }
            }
            if (!b) {
                changed = true;
                thisItr.remove();
            }
        }

        return changed;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; ++i) {
            this.elements[i] = null;
        }
    }
    @Override
    public void add(int index, E data) {
        this.indexChecker(index);

        if (this.size + 1 >= this.elements.length) {
            this.elements = this.grow();
        }
        for (int i = this.size; i > index; --i) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[index] = data;
        ++this.size;
    }

    @Override
    public E remove(int index) {
        this.indexChecker(index);
        E oldValue = (E)this.elements[index];

        for (int i = index; i < this.size - 1; ++i) {
            this.elements[i] = this.elements[i + 1];
        }

        this.elements[--this.size] = null;

        return oldValue;
    }

    @Override
    public int indexOf(Object obj) {
        if (obj == null) {
            for (int i = 0; i < this.size; ++i) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.size; ++i) {
                if (obj.equals(this.elements[i])) {
                    return i;
                }
            }
        }

        return  -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            for (int i = this.size - 1; i >= 0; --i) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = this.size - 1; i >= 0; --i) {
                if (obj.equals(this.elements[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MyListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListItr(index);
    }

    @Override
    public E get(int index) {
        this.indexChecker(index);

        if (index == this.size) {
            throw new IllegalArgumentException();
        }

        return (E)this.elements[index];
    }

    @Override
    public E set(int index, E data) {
        this.indexChecker(index);
        E oldValue = (E)this.elements[index];
        this.elements[index] = data;
        return oldValue;
    }

    private class MyItr implements Iterator<E> {
        int cursor;
        int lastRet = -1;

        MyItr() { }

        @Override
        public boolean hasNext() {
            return this.cursor != MyArrayList.this.size;
        }

        @Override
        public E next() {
            int index = this.cursor;

            if (this.cursor >= MyArrayList.this.size) {
                throw new NoSuchElementException();
            } else {
                if (index >= MyArrayList.this.elements.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = index + 1;
                    lastRet = index;
                    return (E) MyArrayList.this.elements[lastRet];
                }
            }
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            } else {

                try {
                    MyArrayList.this.remove(lastRet);
                    this.cursor = this.lastRet;
                    this.lastRet = -1;
                } catch (Exception e) {
                    throw new ConcurrentModificationException();
                }

            }
        }
    }

    private class MyListItr extends MyArrayList<E>.MyItr implements ListIterator<E> {

        MyListItr(int index) {
            super();
            this.cursor = index;
        }
        public boolean hasPrevious(){
         return this.cursor != 0;
        }

        @Override
        public int nextIndex(){
            return this.cursor;
        }

        @Override
        public int previousIndex(){
            return this.cursor - 1;
        }

        public E previous(){
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                if (i >= MyArrayList.this.elements.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = this.lastRet = i;
                    return (E)MyArrayList.this.elements[lastRet];
                }
            }
        }

        @Override
        public void set(E var1) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }

            try{
                MyArrayList.this.set(lastRet, var1);
            } catch (IndexOutOfBoundsException var2) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E var1) {
            try {
                int i = this.cursor;
                MyArrayList.this.add(i, var1);
                this.cursor = i + 1;
                this.lastRet = -1;
            } catch (IndexOutOfBoundsException var3) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
