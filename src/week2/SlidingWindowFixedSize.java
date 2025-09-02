package week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Leetcode 438
    // Find All Anagrams in a String
    public List<Integer> findAnagramss(String s, String p) {
        int sLength = s.length(), pLength = p.length();
        List<Integer> result = new ArrayList<>();
        if (sLength < pLength) return result;

        Map<Character, Integer> pFreq = new HashMap<>();
        Map<Character, Integer> windowFreq = new HashMap<>();

        // Build frequency map for string p
        for (char c : p.toCharArray()) {
            pFreq.put(c, pFreq.getOrDefault(c, 0) + 1);
        }

        // Sliding window
        for (int i = 0; i < sLength; i++) {
            // Add current character to window
            char addChar = s.charAt(i);
            windowFreq.put(addChar, windowFreq.getOrDefault(addChar, 0) + 1);

            // Keep window size equal to pLength
            if (i >= pLength) {
                char removeChar = s.charAt(i - pLength);
                if (windowFreq.get(removeChar) == 1) {
                    windowFreq.remove(removeChar);
                } else {
                    windowFreq.put(removeChar, windowFreq.get(removeChar) - 1);
                }
            }

            // Compare window with p
            if (i >= pLength - 1 && windowFreq.equals(pFreq)) {
                result.add(i - pLength + 1);
            }
        }

        return result;
    }



}
