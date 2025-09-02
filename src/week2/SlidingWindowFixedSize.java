package week2;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowFixedSize {

    // Leetcode 438
    // Find All Anagrams in a String
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> result = new ArrayList<>();
        if (n < m) return result;

        int[] target = new int[26]; // frequency of chars in p
        int[] window = new int[26]; // frequency of chars in current window

        // Count frequency of characters in p
        for (char c : p.toCharArray()) {
            target[c - 'a']++;
        }

        int diff = 0;
        for (int count : target) {
            if (count > 0) diff++;
        }

        for (int i = 0; i < n; i++) {
            int add = s.charAt(i) - 'a';

            // Add new char into the window
            if (window[add]++ == target[add]) diff++;
            else if (window[add] == target[add]) diff--;

            // Remove old char if window is too big
            if (i >= m) {
                int rem = s.charAt(i - m) - 'a';
                if (window[rem]-- == target[rem]) diff++;
                else if (window[rem] == target[rem]) diff--;
            }

            // If diff == 0, we found an anagram
            if (diff == 0) {
                result.add(i - m + 1);
            }
        }

        return result;
    }
}
