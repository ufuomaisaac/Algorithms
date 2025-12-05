package week2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
The Sliding Window technique is used on arrays or strings to maintain a continuous sub-array or sub-string (the "window")
whose size can either be fixed or dynamic,based on a specific condition.

    1. Fixed-Size Sliding Window 📏
    Used when the problem requires finding the maximum/minimum/average of all subarrays of a specific, constant length K.

    Logic:
    Initialize the window of size K. Calculate its initial result.
    In a single loop, slide the window one element at a time.
    In each step, add the new element entering the window.
    Subtract the element leaving the window.
    Update the result (max/min).

    Time Complexity: O(N) (where N is the length of the array), as you only iterate through the array once.

    Goldman Sachs Practice Problems:
        #35 Sliding Window Maximum (Hard): While fixed size, this needs a Deque (Double-ended queue) to maintain the maximum in O(1) time, keeping the overall complexity at O(N).
        Find the Maximum sum of a subarray of size K. (A generic template problem).


    2. Dynamic-Size Sliding Window 📈
    Used when the problem requires finding the longest or shortest subarray that satisfies a certain condition
    (e.g., sum is ≥ target, or contains no more than K distinct characters).
    The window expands on the right and contracts on the left only when the condition is violated.

    Logic:
    Initialize left = 0. Expand the window by incrementing right.
    While the condition is met, update the result (usually storing the window length, right−left+1).
    If the condition is violated, shrink the window by incrementing left until the condition is met again.

    Time Complexity: O(N). Although there is a nested while loop, the left pointer never moves backward,
    so each element is processed a maximum of twice (once by right, once by left).

    Goldman Sachs Practice Problems:
        #15 Longest Substring Without Repeating Characters (Medium).
        #52 Minimum Size Subarray Sum (Medium).
        #83 Longest Substring with At Most K Distinct Characters (Medium).
        #84 Permutation in String (Medium): This uses a refined dynamic window where the window size is the length of the shorter string
*/

public class SlidingWindowDynamicSIze {



    // LeetCode 3
    // Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen =  new HashMap<>();
        int maxLen = 0;
        int start = 0;

        for(int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            // If we’ve seen this char before and it’s inside the window
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= start) {
                start = lastSeen.get(c) + 1; // move left pointer
            }

            lastSeen.put(c, end); // update last seen index of char
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }



    // Leetcode 424.
    // Longest Repeating Character Replacement
    public int characterReplacement(String s, int k){
        int left = 0;
        int maxCount = 0;
        int result = 0;

        int[] freq = new int[26];

        for(int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'A']++;
            maxCount = Math.max(result, freq[c - 'A']);

            while((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Update result with the valid window size
            result = Math.max(result, right - left + 1);
        }
        return result;
    }



    //Leetcode 239
    //Sliding Window Maximum
    public  static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resultIndex = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        /**
         The condition means: if the index of the current maximum element (deque.peekFirst()) is less than
         the starting index of the current window (i - k + 1) then the maximum at this instance is stale because
         sliding window length is lesser than the expected value k.
         */
        for (int i = 0; i < n; i++) {

            // Step 1: Remove indices from the front that are outside the current window (i - k)
            // The window starts at index (i - k + 1). Anything before that is stale.
            if(!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.removeFirst();

            }

            // Step 2: Maintain the monotonically Decreasing property
            // Remove indices from the back whose corresponding values are less than the current element
            // ...(nums[i]).
            // These elements are no longer relevant because the current element is larger
            // and is also closer to the right end of the window.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            // Step 3: Add the current index to the back of the deque
            deque.addLast(i);

            // Step 4: Record the result once the first window is formed
            // The first window is complete when i >= k - 1
            if (i >= k - 1) {
                // The element at the front of the deque is the index of the maximum element
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }
        return result;

    }

    // Not done
    // Assignement
    // Do the Sliding window minimum
    public  static int[] minSlidingWindow(int[] nums, int k) {




        //Initialize parameter
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resultIndex = 0;


        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < n ; i++) {

            if(!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.removeFirst();
            }

            while(!deque.isEmpty() && deque.peekLast() <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            if(i >= k - 1){
                result[resultIndex++] = nums[deque.peekFirst()];
            }

        }

        return result;


        }

}




