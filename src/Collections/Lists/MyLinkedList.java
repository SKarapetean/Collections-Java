package Collections.Lists;
import Collections.MyCollection;
import Collections.Queues.MyDeque;
import java.util.LinkedList;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyDeque<E>, MyList<E>{

    private int size;
    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {}

    public MyLinkedList(MyCollection<? extends E> col) {
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

    private void linkBefore(E el, Node<E> b) {
        Node<E> tmp = this.first;

        while ( tmp != this.last) {
            if (tmp == b) {
                tmp.prev = new Node<>(el, tmp, tmp.prev);
                tmp.prev.prev.next = tmp.prev;
                break;
            }
            tmp = tmp.next;
        }

        ++this.size;
    }
    private E unlink(Node<E> node) {
        E data = node.item;
        Node<E> next = node.next;
        Node<E> prev = node.prev;

        if (prev == null) {
            this.first = next;
            this.first.prev = null;
        } else if (next == null) {
            this.last = prev;
            this.last.next = null;
        } else {
            next.prev = prev;
            prev.next = next;
        }

        --this.size;
        return data;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkPositionIndex(int index){
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
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
        return this.indexOf(var1) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
    }

    @Override
    public boolean remove(Object var1) {
        Node<E> tmp;
        if (var1 == null) {
            for (tmp = this.first; tmp != null; tmp = tmp.next) {
                if (tmp.item == null) {
                    this.unlink(tmp);
                    return true;
                }
            }
        } else {
            for (tmp = this.first; tmp != null; tmp = tmp.next) {
                if (tmp.item.equals(var1)) {
                    this.unlink(tmp);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(MyCollection<? extends E> vars1) {
        Iterator<?> itr = vars1.iterator();
        while (itr.hasNext()) {
            this.add((E) itr.next());
        }
        return true;
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends E> vars2) {
        this.checkPositionIndex(index);
        Object[] arr = vars2.toArray();

        for (int i = 0; i < arr.length; ++i) {
            this.add(index + i, (E)arr[i]);
        }

        return arr.length > 0;
    }

    @Override
    public boolean removeAll(MyCollection<?> vars1) {
        Iterator<?> itr = vars1.iterator();
        while (itr.hasNext()) {
            this.remove(itr.next());
        }
        return true;
    }

    @Override
    public boolean containsAll(MyCollection<?> vars1) {
        Iterator<?> itr = vars1.iterator();
        boolean bool = true;

        while (itr.hasNext()) {
            if (!this.contains(itr.next())) {
                bool = false;
                break;
            }
        }

        return bool;
    }

    @Override
    public boolean retainAll(MyCollection<?> col) {
        if (col == null) {
            throw new NullPointerException();
        }

        boolean modified = false;
        Node<E> tmp = this.first;

        while (tmp != null) {
            if (!col.contains(tmp.item)) {
                System.out.println(this.unlink(tmp));
                modified = true;
            }
            tmp = tmp.next;
        }

        return modified;
    }

    @Override
    public void clear() {
        Node<E> tmp;
        Node<E> next;

        for (tmp = this.first; tmp != null; tmp = next) {
            next = tmp.next;
            tmp.item = null;
            tmp.prev = null;
            tmp.next = null;
        }

        this.size = 0;
    }

    @Override
    public E get(int index) {
        this.checkElementIndex(index);
        return this.getNode(index).item;
    }

    @Override
    public E set(int index, E var1) {
        this.checkPositionIndex(index);
        Node<E> tmp = this.getNode(index);
        E el = tmp.item;
        tmp.item = var1;
        return el;
    }

    @Override
    public void add(int index, E var1) {
        this.checkPositionIndex(index);
        this.linkBefore(var1, this.getNode(index));
    }

    private Node<E> getNode(int index) {
        Node<E> tmp;

        if (index > this.size >> 1) {
            tmp = this.last;
            for (int i = this.size - 1; i > index; --i) {
                tmp = tmp.prev;
            }
        } else {
            tmp = this.first;
            for (int i = 0; i < index; ++i) {
                tmp = tmp.next;
            }
        }

        return tmp;
    }
    @Override
    public E remove(int index) {
        this.checkElementIndex(index);
        this.unlink(this.getNode(index));
        return null;
    }

    @Override
    public int indexOf(Object var1) {
        int index = 0;
        Node<E> tmp;

        if (var1 == null) {
            for (tmp = this.first; tmp != null; tmp = tmp.next) {
                if (tmp.item == null) {
                    return index;
                }
                ++index;
            }
        } else {
            for (tmp = this.first; tmp != null; tmp = tmp.next) {
                if (var1.equals(tmp.item)) {
                    return index;
                }
                ++index;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object var1) {
        int index = this.size - 1;
        Node<E> tmp;

        if (var1 == null) {
            for (tmp = this.last; tmp != null; tmp = tmp.prev) {
                if (tmp.item == null) {
                    return index;
                }
                --index;
            }
        } else {
            for (tmp = this.last; tmp != null; tmp = tmp.prev) {
                if (tmp.item.equals(var1)) {
                    return index;
                }
                --index;
            }
        }

        return -1;
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
        Object[] result = new Object[this.size];
        int index = 0;

        for (Node<E> tmp = this.first; tmp != null; tmp = tmp.next) {
            result[index++] = tmp.item;
        }

        return result;
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
