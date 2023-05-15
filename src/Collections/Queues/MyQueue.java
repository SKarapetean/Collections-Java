package Collections.Queues;

import Collections.MyCollection;

public interface MyQueue<E> extends MyCollection<E> {
    boolean add(E var1);
    boolean offer(E var1);
    E remove();
    E poll();
    E element();
    E peek();
}
