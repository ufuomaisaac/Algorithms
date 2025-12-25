package week2;


/**
 * DOUBLY LINKED LIST RULES:
 * 1. Every 'add' or 'delete' in the middle requires updating TWO neighbors.
 * 2. 'head.prev' must always be null.
 * 3. 'tail.next' must always be null.
 * 4. Special care is needed when the list has only 1 node (head == tail).
 */

// Leetcode 707
// Design Linked List
public class MyLinkedList {

    private int size;
    private Node head;
    private Node tail;

    public class Node{
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public MyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;

    }

    /** Get the value of the index-th node. O(N) time. */
    public int get(int index) {
        if(index < 0 || index >= size) return -1;

        Node current = head;
        for(int i = 0;i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /** Add a node of value val before the first element. O(1) time. */
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        if(head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /** Append a node of value val to the last element. O(1) time. */
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if(head == null){
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /** Add a node before the index-th node. O(N) time. */
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) return ;

        if(index == 0){
            addAtHead(val);
            return;
        }

        if(index == size) {
            addAtTail(val);
            return;
        }

        // Standard middle insertion
        Node newNode = new Node(val);
        Node current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }

        // Stitching the newNode between current.prev and current
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        // Case 1: Deleting the Head
        if(current == head) {
            head = head.next;
            if(head != null) head.prev = null;
            else tail = null; //List become empty
        }

        // Case 2: Deleting the Tail
        else if(current == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null; //List become empty
        }

        // Case 3: Deleting a Middle Node
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {

    }
}
