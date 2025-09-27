package week2;

import java.util.HashMap;
import java.util.Map;

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




