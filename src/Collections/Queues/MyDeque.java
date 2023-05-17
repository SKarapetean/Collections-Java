package Collections.Queues;

import java.util.Iterator;

public interface MyDeque<E> extends MyQueue<E>{

    void addFirst(E var1);

    void addLast(E var1);

    boolean offerFirst(E var1);

    boolean offerLast(E var1);

    E removeFirst();

    E removeLast();

    E pollFirst();

    E pollLast();

    E getFirst();

    E getLast();

    E peekFirst();

    E peekLast();

    boolean removeFirstOccurrence(Object var1);

    boolean removeLastOccurrence(Object var1);
    void push(E var1);

    E pop();
    Iterator<E> descendingIterator();
}
