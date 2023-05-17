package Collections;

import java.util.Iterator;

public interface MyCollection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object var1);
    Iterator<E> iterator();
    boolean add(E var1);
    boolean remove(Object var1);
    boolean addAll(MyCollection<? extends E> vars1);
    boolean removeAll(MyCollection<?> vars1);
    boolean containsAll(MyCollection<?> vars1);
    boolean retainAll(MyCollection<?> vars1);
    void clear();
    boolean equals(Object var1);
    int hashCode();
    Object[] toArray();
}
