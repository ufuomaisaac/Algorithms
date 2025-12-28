package week2;

/**
 * THE NAVIGATION RULE:
 * 1. Indices are just numbers; Nodes are objects in memory.
 * 2. To turn an index into a Node reference, you MUST traverse.
 * 3. Array = Direct Access (O(1)).
 * 4. Linked List = Sequential Access (O(N)).
 */


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
 * objfor (int i = 0; i < size - k - 1; i++) {
        newTail = newTail.next;
    }.addAtHead(val);
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

    /**
     * DETAILED ALGORITHM LOGIC: THE TWO-POINTER SEPARATION STRATEGY
     * * 1. DUMMY INITIALIZATION:
     * - We create two sentinel (dummy) nodes: 'lessHead' and 'greaterHead'.
     * - These serve as fixed anchors so we never lose the starting point of our
     * two sub-lists, especially if the original 'head' changes.
     * * 2. TAIL TRACKING:
     * - 'less' and 'greater' act as "active tails" or "write-heads."
     * - They always point to the last confirmed node in their respective chains.
     * * 3. SINGLE-PASS RE-LINKING (O(N) Time):
     * - We traverse the original list node-by-node.
     * - IF (val < x): We attach the current node to 'less.next' and move 'less' forward.
     * - ELSE: We attach the current node to 'greater.next' and move 'greater' forward.
     * - Crucially, we are RE-LINKING existing memory addresses, not creating new objects.
     * * 4. PREVENTING CYCLES (The "Stray Pointer" Fix):
     * - Because we are re-using nodes, the last node of the 'greater' list might
     * still point to a node that was moved into the 'less' list.
     * - We MUST set 'greater.next = null' to terminate the list and avoid an infinite loop.
     * * 5. CONCATENATION:
     * - We bridge the two lists by setting 'less.next = greaterHead.next'.
     * - This effectively "stiches" the end of the small values to the start of the large values.
     * * 6. RESULT:
     * - We return 'lessHead.next', which skips our temporary dummy node and
     * provides the head of the newly partitioned, stable list.
     * * COMPLEXITY:
     * - Time: O(N) -> Each node is visited once.
     * - Space: O(1) -> Only 4 pointer references are used regardless of list size.
     */
    // LeetCode 86
    // Partition List
    public ListNode partition(ListNode head, int x) {
        // 1. Create two 'Dummy' nodes to act as anchors for our two lists.
        ListNode lessHead = new ListNode();
        ListNode greaterHead = new ListNode();

        // Pointers to the end of the two lists// Pointers to the end of the two lists
        ListNode less = lessHead;
        ListNode greater = greaterHead;

        while(head != null) {
            if(head.val < x) {
                less.next = head;   // REUSE the actual node, don't create a new one
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }

        // IMPORTANT: Prevent a cycle in the list
        greater.next = null;

        // Stitch the two lists together
        less.next = greaterHead.next;

        return lessHead.next;
    }



    /**
     * ROTATE RIGHT SUMMARY:
     * 1. Transform the linear list into a circular list by connecting tail to head.
     * 2. Calculate the split position using (size - k % size - 1).
     * 3. Traverse to this position to find the 'newTail'.
     * 4. The 'newHead' is the node after 'newTail'.
     * 5. Sever the connection at 'newTail.next' to restore a linear structure.
     */
    // Leetcode 61
    // Rotate List
    public ListNode rotateRight(ListNode head, int k) {
        // 1. Edge cases
        if(head == null || head.next == null || k == 0) return head;

        // 2. Measure the list and identify the last node simultaneously
        ListNode lastNode = head;
        int size = 1;
        while(lastNode.next != null) {
            lastNode = lastNode.next;
            size++;
        }

        // 3. Handle rotation math
        k = k % size;
        if(k == 0) return head;

        // 4. THE WELD: Connect tail to head to form a ring
        lastNode.next = head;

        // 5. THE NAVIGATION: Use the index math to find the new split point
        // We need to move (size - k - 1) times from the head
        ListNode newTail = head;
        for(int i = 0; i < size - k - 1; i++){
            newTail = newTail.next;
        }

        // 6. THE BREAK: Define new head and sever the ring
        ListNode newHead =newTail.next;
        newTail.next = null;

        return newHead;
    }


    /**
     * ADD TWO NUMBERS SUMMARY:
     * 1. REVERSE ORDER ADVANTAGE: Because lists are reversed, the heads are the
     * least significant digits. We can add naturally from left to right.
     * 2. DUMMY NODE: Use a dummy head to simplify building the result list.
     * 3. CARRY LOGIC:
     * - New Digit = (Sum of nodes + carry) % 10.
     * - New Carry = (Sum of nodes + carry) / 10.
     * 4. PADDING: If one list ends before the other, treat its value as 0.
     * 5. FINAL CARRY: The while loop condition 'carry != 0' ensures that an
     * extra node is created if the last addition results in a carry (e.g., 9 + 1 = 10).
     * 6. COMPLEXITY: Time O(max(N, M)), Space O(max(N, M)) to store the new list.
     */
    // Leetcode 2
    // Add two numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. DUMMY ANCHOR: Use a sentinel node to easily return the result head.
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // 2. UNIFIED LOOP: Handles both lists and the final carry in one go.
        while (l1 != null || l2 != null || carry != 0) {
            // Extract values: Use 0 if the list has already reached the end.
            int val1 = (l1 != null)? l1.val : 0;
            int val2 = (l2 != null)? l2.val : 0;

            // Compute current sum and new carry.
            int sum  = val1 + val2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            // 3. ATTACH: Create a new node for the single digit.
            current.next = new ListNode(digit);
            current = current.next;

            // 4. ADVANCE: Move to the next nodes if they exist.
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }
        // 5. RETURN: Skip the dummy head to get the actual result.
        return dummyHead.next;
    }



    /**
     * FLATTEN MULTILEVEL LIST SUMMARY:
     * 1. ITERATIVE SCAN: Traverse the list using a single pointer 'p'.
     * 2. CHILD DETECTION: When 'p.child' is found, pause the main traversal.
     * 3. TAIL SEARCH: Find the last node of the child branch.
     * 4. RE-WIRING:
     * - Connect Child-Tail to p.next (Bridge the gap forward).
     * - Connect p.next to Child-Tail (Bridge the gap backward).
     * - Connect p to p.child (Bring the branch up to the main level).
     * - Set p.child to null (Crucial: convert to standard doubly list).
     * 5. RESUME: Continue moving 'p' forward. It will eventually process the
     * nodes that were just brought up from the child level.
     */
    // LeetCode 430
    // Flatten a multilevel Doubly Linkedlist
    public Node flatten(Node head) {

    }
}

/**
 * NODE CLASS RECAP:
 * 1. Standard Singly: val, next.
 * 2. Standard Doubly: val, next, prev.
 * 3. Multilevel Doubly: val, next, prev, child.
 * * *Note: In flattening, we convert Type 3 into Type 2
 * by reassigning 'child' addresses to 'next' slots.*
 */
class Node {
    public int val;      // The data stored in the node
    public Node prev;    // Pointer to the previous node (Backward)
    public Node next;    // Pointer to the next node (Forward)
    public Node child;   // Pointer to a sub-level list (Downward)

    // Default Constructor
    public Node() {}

    // Constructor to initialize a node with a value
    public Node(int _val) {
        val = _val;
    }

    // Full Constructor for complex initialization
    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};



/**
 * THE LINKED LIST FRAMEWORK:
 * 1. It is a "Singly" Linked List (only goes forward).
 * 2. 'val' stores the integer.
 * 3. 'next' is a recursive reference to the next ListNode object.
 * 4. 'null' is the universal signal for "End of List".
 */
class ListNode {
    // The actual data stored in the node
    public int val;

    // The reference (pointer) to the next node in the chain
    public ListNode next;

    // Constructor 1: Creates an empty node (usually val = 0)
    public ListNode() {}

    // Constructor 2: Creates a node with a specific value
    public ListNode(int val) {
        this.val = val;
    }

    // Constructor 3: Creates a node with a value AND connects it to another node
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
