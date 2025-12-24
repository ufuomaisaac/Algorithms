package week2;

public class LinkedList {

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


    public  MyLinkedList() {
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




    }

    public void deleteAtIndex(int index) {

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
