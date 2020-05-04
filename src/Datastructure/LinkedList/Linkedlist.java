package Datastructure.LinkedList;

import java.util.Arrays;
import java.util.Stack;

class Node {


    private int data;
    private Node next;

    Node() {

    }

    Node(int val, Node next) {
        data = val;
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}

public class Linkedlist {
    Node head;
    Node tail;
    int size;

    Linkedlist() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void printReversedLL(Linkedlist l) {
        System.out.println("Use stack LIFO:Only print reversed ll, not change inner structure");
        //User Stack LIFO
        String output = "";
        Stack<Node> stk = new Stack<Node>();
        Node cur = l.head;
        while (cur != null) {
            stk.push(cur);
            cur = cur.getNext();
        }
        while (!stk.empty()) {
            Node out = stk.pop();
            output += out.getData() + ", ";

        }
        System.out.println(output);
    }

    public static void printReversedLL_Recursive(Linkedlist l, Node cur) {

        String out = "";
        if (cur != l.tail) {

            printReversedLL_Recursive(l, cur.getNext());
        }
        out += cur.getData() + ",";

        System.out.print(out + '\n');
    }

    public static void main(String[] argv) {
        Linkedlist ll = new Linkedlist();
    /*
    ll.addfromEnd(1);
    ll.addfromEnd(12);
    ll.addfromEnd(33);
    */
        ll.addFromBegin(23);
        ll.addFromBegin(34);
        ll.addFromBegin(5);
        ll.addFromBegin(7);
        ll.printLL();

        ll.reverseLL();
        System.out.println("Reversed the structure");
        ll.printLL();
        printReversedLL(ll);
        System.out.println("Use recursion:Only print reversed ll, not change inner structure");
        printReversedLL_Recursive(ll, ll.head);
        System.out.println("\n deleted by index");
        ll.delete_index(4);
        ll.deleteFromBegin();
        ll.printLL();

    }

    void addfromEnd(int val) {
        Node newN = new Node(val, null);
        if (size == 0) {
            head = newN;

        } else {
            tail.setNext(newN);
        }
        tail = newN;
        size++;
    }

    void addFromBegin(int val) {
        Node newN = new Node(val, null);

        if (size == 0) {
            tail = newN;
        } else {

            newN.setNext(head);
        }
        head = newN;

        size++;

    }

    boolean isEmpty() {
        boolean out = false;
        if (size == 0 || (head == null && tail == null)) {
            out = true;
        }
        return out;
    }

    Node deleteFromBegin() {
        Node deleted = null;
        if (isEmpty()) {
            System.out.println("cannot delete empty list");
        } else {
            deleted = head;
            head = head.getNext();
        }
        return deleted;
    }

    Node delete_index(int index) {
        Node deleted = null;
        Node cur = head;
        if (index < size && index >= 0) {
            int i = 0;
            if (index == 0) {
                deleteFromBegin();
            } else {
                while (i < size - 1) {
                    if (i == index - 1) {
                        deleted = cur.getNext();
                        Node nn = deleted.getNext();
                        cur.setNext(nn);
                    }
                    cur = cur.getNext();
                    i++;
                }
            }

        } else {
            System.out.println("invalid index");
        }
        return deleted;
    }

    void reverseLL() {
        Node cur = head;
        Node prev = null;
        Node next = null;
        while (cur != null) {
            next = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }
        tail = head;
        head = prev;


    }

    void printLL() {
        String out = "";
        Node cur = head;
        while (cur != null && cur != tail.getNext()) {
            out += cur.getData() + ",";
            cur = cur.getNext();
        }

        System.out.println(out);
    }
}


