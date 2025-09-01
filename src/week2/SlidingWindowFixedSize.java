package week2;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowFixedSize {

    public List<Integer> findAnagrams(String s, String p) {
        int sLength = s.length(), pLength = p.length();
        List<Integer> anagramIndices = new ArrayList<>();
        if (sLength < pLength) return anagramIndices;

        int[] pFreq = new int[26];      // frequency of chars in p
        int[] windowFreq = new int[26]; // frequency of chars in current window

        // Count frequency of characters in p
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }

        int unmatchedCharTypes = 0; // number of characters whose frequencies don't match
        for (int count : pFreq) {
            if (count > 0) unmatchedCharTypes++;
        }

        for (int i = 0; i < sLength; i++) {
            int addedChar = s.charAt(i) - 'a';

            // Add new character into the window
            if (windowFreq[addedChar]++ == pFreq[addedChar]) unmatchedCharTypes++;
            else if (windowFreq[addedChar] == pFreq[addedChar]) unmatchedCharTypes--;

            // Remove old character when window size exceeds pLength
            if (i >= pLength) {
                int removedChar = s.charAt(i - pLength) - 'a';
                if (windowFreq[removedChar]-- == pFreq[removedChar]) unmatchedCharTypes++;
                else if (windowFreq[removedChar] == pFreq[removedChar]) unmatchedCharTypes--;
            }

            // If all frequencies match, it's an anagram
            if (unmatchedCharTypes == 0) {
                anagramIndices.add(i - pLength + 1);
            }
        }

        return anagramIndices;
    }
}
