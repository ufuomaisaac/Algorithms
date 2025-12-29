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



    /**
     * REVERSE K-GROUP RECAP (Direct vs. Reversed Logic):
     * 1. SCOUTING: Scan 'k' nodes. If count < k, return 'head' as-is.
     * * 2. POST-ORDER PROCESSING:
     * - We call reverseKGroup(curr, k) first.
     * - This returns the head of the "Reversed" section (the future of the list).
     * * 3. THE REVERSAL SWAP (The "Direct-to-Reversed" Transfer):
     * - 'head' points to the "Direct" part (nodes still in their original order).
     * - 'curr' points to the "Reversed" part (nodes already flipped and connected).
     * * Inside the loop:
     * - tmp = head.next  // Save the next node in the Direct part.
     * - head.next = curr // Takes the first Direct node and pins it to the
     * // front of the Reversed part.
     * - curr = head      // The "Reversed" boundary grows to include this node.
     * - head = tmp       // Move the "Direct" pointer forward to the next node.
     * * 4. ATTACHMENT:
     * Because 'curr' originally held the result of the recursive call, the
     * original first node of your current group automatically points to the
     * head of the next group.
     */

                //OR

    /**
     * REVERSE K-GROUP RECAP:
     * 1. SCOUTING: Always check if k nodes exist. If head is null or count < k,
     * just return head (Base Case).
     * 2. POST-ORDER PROCESSING: By calling the recursion BEFORE the reversal loop,
     * we ensure the "tail" of the list is fixed before we attach the "head."
     * 3. THE REVERSAL SWAP:
     * - tmp = head.next  (Hold the next node)
     * - head.next = curr (Reverse the pointer)
     * - curr = head      (Shift the reversed boundary)
     * - head = tmp       (Move to next node)
     * 4. ATTACHMENT: The first node of your group automatically points to the
     * head of the next group because 'curr' starts as that head.
     */
    // Leetcode 25
    // Reverse Nodes in K-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. THE SCOUT: Find the (k+1)-th node to see if we have enough to reverse
        ListNode curr = head;
        int count = 0;

        while(curr != null && count != k) {
            curr = curr.next;
            count++;
        }

        // 2. THE DECISION: If we found k nodes, reverse them
        if(count == k){
            // LEAP OF FAITH: Recursively solve the rest of the list first.
            // 'curr' will be the head of the finished "future" part.
            curr = reverseKGroup(curr, k);

            // 3. THE REVERSAL: Standard "pre-pending" loop
            // We move k nodes from the 'head' list to the 'curr' list.

            while(count-- > 0) {
                ListNode temp = head.next;  // Save the next node in the direct part
                head.next = curr;           // Link current head back to the reversed part
                curr = head;                // Move the "reversed head" marker to this node
                head = temp;           // Advance the "direct head" to the next node
            }

            // 4. THE UPDATE: 'curr' is now the new head of this reversed block
            curr = head;
        }
        // 5. THE RETURN: Returns the new head (if reversed) or original head (if not)
        return head;
    }

}
