package week2;

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
}




