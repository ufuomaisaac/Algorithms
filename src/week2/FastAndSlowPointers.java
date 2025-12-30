package week2;

public class FastAndSlowPointers {

    /**
     * THE DRY RUN RECAP:
     * 1. THE GAP: The 'Fast' pointer always closes the gap between it
     * and the 'Slow' pointer by 1 node every turn. Inside a circle,
     * this ensures they MUST meet.
     * 2. THE OVERLAP: Once they meet, the distance they haven't
     * covered in the circle is exactly equal to the distance from
     * the start of the list.
     * 3. THE HANDSHAKE: By moving 'head' and 'slow' together, you
     * are walking those two equal distances. Where they "handshake"
     * is your cycle start.
     */

    /**
     * THE SOLUTION IN 3 STEPS:
     * 1. RACE: Move slow and fast pointers. If they never meet, return null.
     * 2. SYNC: Once they meet, move one pointer back to the very start (head).
     * 3. COLLIDE: Move both 1 step at a time. Their meeting point is the
     * "joint" where the linear list becomes a circle.
     */

    /**
     * TO THE LETTER SUMMARY:
     * 1. PHASE 1: Find ANY node inside the loop by using different speeds.
     * 2. PHASE 2: Find the START of the loop by using identical speeds.
     * 3. THE KEY: One pointer starts at the Beginning; the other starts
     * at the Meeting Point.
     * 4. RESULT: The first node they share is the entrance.
     * 5. SPACE: O(1) - we didn't store any nodes in a Map or Set.
     */


    // LeetCode 142
    // LinkedList Cycle 11
    public ListNode detectCycle(ListNode head) {
        // --- PHASE 1: DETECTION (The Race) ---
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;           // moves one step
            fast = fast.next.next;      // moves two steps

            // If they meet, a cycle is confirmed
            if (slow == fast) {
                return findEntrance(head, slow);
            }
        }

        // If fast hits null, there is no cycle
        return null;
    }

    private ListNode findEntrance(ListNode head, ListNode meetingPoint){
        // --- PHASE 2: LOCATION (The Synchronized Walk) ---
        ListNode pointer1 = head;               //Start from the beginning
        ListNode pointer2 = meetingPoint;       //Start from where they met

        // Move both at the same speed (1 step)
        while(pointer1 != pointer2){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        // The node where they collide is the cycle entrance
        return pointer1;
    }



}
