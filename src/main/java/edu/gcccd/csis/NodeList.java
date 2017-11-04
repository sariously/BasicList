package edu.gcccd.csis;

import java.util.Iterator;

public final class NodeList<E> implements Iterable<E> {

    private Node<E> head;

    int getLength() {
        int result = 0;
        Node node = head;
        while (node != null) {
            node = node.next;
            result++;
        }
        return result;
    }

    public void append(final E element) {
        if (head == null) {
            head = new Node<>(element);
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(element);
        }
    }

    public void remove(final E element) {
        if (head!=null) {
            if (head.element.equals(element)) {
                head = head.next;
            } else {
                Node<E> node = head;
                while (node.next!=null) {
                    if (node.next.element.equals(element)) {
                        node.next= node.next.next;
                        break;
                    }
                    node= node.next;
                }
            }
        }
    }

    public boolean contains(final E element) {
        for (E e : this) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    // implement Iterable<E> interface
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node!=null;
            }

            @Override
            public E next() {
                E obj = node.element;
                node = node.next;
                return obj;
            }
        };
    }

    static final class Node<E> {
        private E element;
        private Node<E> next;

        private Node(final E element) {
            this.element = element;
        }
    }
}