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

    /**
     * THE PROBLEM IN A NUTSHELL:
     * 1. Find the 'sub-chain' starting at 'left' and ending at 'right'.
     * 2. Reverse that sub-chain in-place.
     * 3. Keep the nodes at the very beginning and very end of the total list
     * in their original order.
     * 4. Complexity requirement: Do it in a single pass (O(N)) and
     * without extra memory (O(1)).
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        // 1. THE GUARD: Dummy node handles cases where 'left' is the head (index 1)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 2. THE ANCHOR: Move 'prev' to the node exactly BEFORE the reversal zone
        ListNode prev = dummy;
        for(int i = 0; i < left - 1; i++){
            prev = prev.next;
        }

        // 3. THE TAIL: 'curr' starts as the head of the segment
        // It will eventually be pushed to the end of the segment
        ListNode curr = prev.next;

        // 4. THE SHUTTLE LOOP: Perform (right - left) swaps
        for(int i = 0; i < right - left; i++){
            // Step A: Identify the 'shuttle' node (the one to move)
            ListNode forw = curr.next;

            // Step B: THE CUT - Bridge curr to the node after forw
            curr.next = forw.next;

            // Step C: THE LINK - Point forw to the current head of the sub-segment
            forw.next = prev.next;

            // Step D: THE PASTE - Connect the anchor to forw
            prev.next = forw;
        }
        return dummy.next;

    }

    public ListNode reverseKGroup(ListNode head, int k) {

    }
}
}
