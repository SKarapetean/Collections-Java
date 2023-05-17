package Collections.Lists;
import Collections.MyCollection;
import Collections.Queues.MyDeque;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyDeque<E>, MyList<E>{

    private int size;
    private Node<E> first;
    private Node<E> last;

    MyLinkedList() {}

    MyLinkedList(MyCollection<? extends E> col) {
        this();
        this.addAll(col);
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkFirst(E el) {
        Node<E> tmp = this.first;
        Node<E> newNode = new Node<>(el, this.first, null);
        this.first = newNode;

        if (tmp == null) {
            this.last = newNode;
        } else {
            tmp.prev = newNode;
        }

        ++this.size;
    }

    private void linkLast(E el) {
        Node<E> tmp = this.last;
        Node<E> newNode = new Node<>(el, null, this.last);
        this.last = newNode;

        if (tmp == null) {
            this.first = newNode;
        } else {
            tmp.next = newNode;
        }

        ++this.size;
    }

    private E unlink(Node<E> node) {
        E data = node.item;
        Node<E> next = node.next;
        Node<E> prev = node.prev;
        if (prev == null) {
            this.first = next;
        } else if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            prev.next = next;
        }

        --this.size;
        return data;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object var1) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
    }

    @Override
    public boolean remove(Object var1) {
        return false;
    }

    @Override
    public boolean addAll(MyCollection<? extends E> vars1) {
        return false;
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends E> vars2) {
        return false;
    }

    @Override
    public boolean removeAll(MyCollection<?> vars1) {
        return false;
    }

    @Override
    public boolean containsAll(MyCollection<?> vars1) {
        return false;
    }

    @Override
    public boolean retainAll(MyCollection<?> vars1) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E var1) {
        return null;
    }

    @Override
    public void add(int index, E var1) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object var1) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object var1) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public MyList<E> subList(int index1, int index2) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public void addFirst(E var1) {
        this.linkFirst(var1);
    }

    @Override
    public void addLast(E var1) {
        this.linkLast(var1);
    }

    @Override
    public boolean offerFirst(E var1) {
        this.linkFirst(var1);
        return true;
    }

    @Override
    public boolean offerLast(E var1) {
        this.linkLast(var1);
        return true;
    }

    @Override
    public E removeFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
        return this.unlink(this.first);
    }

    @Override
    public E removeLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        return this.unlink(this.last);
    }

    @Override
    public E pollFirst() {
        return this.first == null ? null : this.unlink(first) ;
    }

    @Override
    public E pollLast() {
        return this.last == null ? null : this.unlink(last);
    }

    @Override
    public E getFirst() {
        if(this.first == null) {
            throw new NoSuchElementException();
        }
        return this.first.item;
    }

    @Override
    public E getLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        return this.last.item;
    }

    @Override
    public E peekFirst() {
        return this.first == null ? null : this.first.item;
    }

    @Override
    public E peekLast() {
        return this.last == null ? null : this.last.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object var1) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object var1) {
        return false;
    }

    @Override
    public void push(E var1) {
        this.addFirst(var1);
    }

    @Override
    public E pop() {
        return this.removeFirst();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean add(E var1) {
        this.linkLast(var1);
        return true;
    }

    @Override
    public boolean offer(E var1) {
        this.linkLast(var1);
        return true;
    }

    @Override
    public E remove() {
        return this.removeFirst();
    }

    @Override
    public E poll() {
        return this.first == null ? null : this.unlink(first);
    }

    @Override
    public E element() {
        return this.getFirst();
    }

    @Override
    public E peek() {
        return this.first == null ? null : this.first.item;
    }

}
