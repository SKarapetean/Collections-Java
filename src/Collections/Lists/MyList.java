package Collections.Lists;

import Collections.MyCollection;

import java.util.Iterator;
import java.util.ListIterator;
public interface MyList<E> extends MyCollection<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object var1);
    Iterator<E> iterator();
    Object[] toArray();
    boolean add(E var1);
    boolean remove(Object var1);
    boolean containsAll(MyCollection<?> vars1);
    boolean addAll(MyCollection<? extends E> vars1);
    boolean addAll(int index, MyCollection<? extends E> vars2);
    boolean removeAll(MyCollection<?> vars1);
    boolean retainAll(MyCollection<?> vars1);
    void clear();
    boolean equals(Object var1);
    int hashCode();
    E get(int index);
    E set(int index, E var1);
    void add(int index, E var1);
    E remove(int index);
    int indexOf(Object var1);
    int lastIndexOf(Object var1);
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);
    MyList<E> subList(int index1, int index2);

}
