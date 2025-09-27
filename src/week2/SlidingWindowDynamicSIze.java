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
}




