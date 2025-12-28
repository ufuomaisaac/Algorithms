package week2;

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

public class LinkedListInPlaceReversal {


    /**
     * THE "SHUTTLE" INSERTION LOGIC:
     * 1. DON'T REVERSE ARROWS: Instead, move nodes.
     * 2. THE CUT: curr.next = forw.next (Remove the shuttle node from the chain).
     * 3. THE LINK: forw.next = prev.next (Point shuttle to the current reversed head).
     * 4. THE CONNECT: prev.next = forw (Connect anchor to the new shuttle).
     * 5. RESULT: Each loop iteration "shuttles" one node from the right of 'curr'
     * to the right of 'prev'.
     */
    // Leetcode 92
    // Reversed Linked List 11
    public ListNode reverseBetween(ListNode head, int left, int right) {

    }
}
