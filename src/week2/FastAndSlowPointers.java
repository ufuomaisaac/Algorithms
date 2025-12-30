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


    // LeetCode 142
    // LinkedList Cycle 11
    public ListNode detectCycle(ListNode head) {

    }

}
