package week2;

import java.util.*;
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



    // Leetcode 567
    // Permutation in String
    public boolean checkInclusion(String s1, String s2) {
        int[] alpha = new int[26];
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        // Step 1: Count frequency of characters in s1
        for (int i = 0; i < c1.length; i++) {
            alpha[c1[i] - 'a'] += 1;
        }

        int count = 0;
        for (int right = 0; right < c2.length; right++) {
            // Step 2: Expand window to include s2[right]
            if (alpha[c2[right] - 'a'] != 0) {
                count++;
                alpha[c2[right] - 'a'] = alpha[c2[right] - 'a'] - 1;
            } else {
                // Step 3: If mismatch, shrink window from the left
                if (count == c1.length)
                    return true;
                else {
                    // Remove leftmost character of the current window
                    alpha[c2[right - count] - 'a'] = alpha[c2[right - count] - 'a'] + 1;
                    count--; //length is decreased coz lefft pointer is moved forward one step
                    right--;  //doing right-- because right++ will be done by for loop I want i to stay where it is
                }
            }
        }

        // Step 4: Check if final window matched s1 length
        return count == c1.length;
    }


    // LeetCode 2461.
    // Maximum Sum of Distinct Subarrays With Length K
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>(); // frequency of elements in current window
        long maxSum = 0, windowSum = 0;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            if (right >= k - 1) {  // window has reached size k
                if (freqMap.size() == k) {
                    maxSum = Math.max(maxSum, windowSum);
                }

                int left = right - k + 1; // left boundary of window
                windowSum -= nums[left];
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
            }
        }
        return maxSum;
    }


    // LeetCode 30
    // Substring with Concatenation of All Words
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (words.length == 0 || s.length() == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int wordCount = words.length;
        int stringLength = s.length();

        // Step 1: Build the frequency map of words
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Step 2: Try all possible starting offsets
        for (int offset = 0; offset < wordLength; offset++) {
            Map<String, Integer> windowFrequency = new HashMap<>();
            int left = offset;
            int matchedWords = 0;

            // Step 3: Slide over the string in word-sized chunks
            for (int right = offset; right + wordLength <= stringLength; right += wordLength) {
                String currentWord = s.substring(right, right + wordLength);

                // Case A: Current word is part of the target words
                if (wordFrequency.containsKey(currentWord)) {
                    windowFrequency.put(currentWord, windowFrequency.getOrDefault(currentWord, 0) + 1);
                    matchedWords++;

                    // Shrink window if we have more of 'currentWord' than allowed
                    while (windowFrequency.get(currentWord) > wordFrequency.get(currentWord)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowFrequency.put(leftWord, windowFrequency.get(leftWord) - 1);
                        left += wordLength;
                        matchedWords--;
                    }

                    // If we matched all words, record starting index
                    if (matchedWords == wordCount) {
                        result.add(left);
                    }

                } else {
                    // Case B: Current word not in words → reset window
                    windowFrequency.clear();
                    matchedWords = 0;
                    left = right + wordLength;
                }
            }
        }

        return result;
    }
}



