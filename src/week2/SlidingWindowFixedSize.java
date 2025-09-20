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
}




class minimumOperation {
    public static int findMinimumOperations(List<List<Integer>> bookshelf, int k) {
        int n = bookshelf.size();
        if (n == 0) return 0;
        int m = bookshelf.get(0).size();

        int[][] authorsInRow = new int[k + 1][n];
        int[][] authorsInCol = new int[k + 1][m];

        int totalBooks = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int author = bookshelf.get(i).get(j);
                if (author > 0) {
                    authorsInRow[author][i]++;
                    authorsInCol[author][j]++;
                    totalBooks++;
                }
            }
        }

        int operations = 0;
        while (totalBooks > 0) {
            operations++;
            int maxRemoved = -1;
            int bestI = -1;
            int bestJ = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int author = bookshelf.get(i).get(j);
                    if (author > 0) {
                        int removedCount = authorsInRow[author][i] + authorsInCol[author][j];
                        if (removedCount > maxRemoved) {
                            maxRemoved = removedCount;
                            bestI = i;
                            bestJ = j;
                        }
                    }
                }
            }

            int authorToRemove = bookshelf.get(bestI).get(bestJ);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (bookshelf.get(i).get(j) == authorToRemove && (i == bestI || j == bestJ)) {
                        authorsInRow[authorToRemove][i]--;
                        authorsInCol[authorToRemove][j]--;
                        bookshelf.get(i).set(j, 0);
                        totalBooks--;
                    }
                }
            }
        }
        return operations;
    }
}


//Check Gemini for the solution
class starvation {
    public static List<Integer> findStarvation(List<Integer> priorities) {
        int n = priorities.size();
        List<Integer> starvation = new ArrayList<>(Collections.nCopies(n, 0));

        // Coordinate Compression
        List<Integer> sortedPriorities = new ArrayList<>(priorities);
        Collections.sort(sortedPriorities);
        HashMap<Integer, Integer> priorityMap = new HashMap<>();
        int rank = 1;
        for (int p : sortedPriorities) {
            if (!priorityMap.containsKey(p)) {
                priorityMap.put(p, rank++);
            }
        }

        // Fenwick Tree (BIT) setup
        int[] bit = new int[priorityMap.size() + 1];

        // Process in reverse order
        for (int i = n - 1; i >= 0; i--) {
            int compressedPriority = priorityMap.get(priorities.get(i));

            // Query Fenwick Tree for count of lower-priority items
            int lowerPriorityCount = 0;
            for (int idx = compressedPriority - 1; idx > 0; idx -= idx & -idx) {
                lowerPriorityCount += bit[idx];
            }
            starvation.set(i, lowerPriorityCount);

            // Update Fenwick Tree for the current priority
            for (int idx = compressedPriority; idx < bit.length; idx += idx & -idx) {
                bit[idx]++;
            }
        }
        return starvation;
    }
}


//This is the class I used for the first question;
class Result {
    public static List<Integer> findStarvation(List<Integer> priorities) {
        int n = priorities.size();
        List<Integer> starvation = new ArrayList<>(Collections.nCopies(n, 0));

        // Step 1: Coordinate Compression
        Set<Integer> uniquePriorities = new HashSet<>(priorities);
        List<Integer> sortedUniquePriorities = new ArrayList<>(uniquePriorities);
        Collections.sort(sortedUniquePriorities);

        HashMap<Integer, Integer> priorityMap = new HashMap<>();
        for (int i = 0; i < sortedUniquePriorities.size(); i++) {
            priorityMap.put(sortedUniquePriorities.get(i), i + 1);
        }

        // Step 2: Fenwick Tree (BIT) setup
        int[] bit = new int[sortedUniquePriorities.size() + 1];

        // Step 3: Process in reverse order
        for (int i = n - 1; i >= 0; i--) {
            int currentPriority = priorities.get(i);
            int compressedPriority = priorityMap.get(currentPriority);

            // Query Fenwick Tree for count of lower-priority items
            int lowerPriorityCount = 0;
            for (int idx = compressedPriority - 1; idx > 0; idx -= idx & -idx) {
                lowerPriorityCount += bit[idx];
            }
            starvation.set(i, lowerPriorityCount);

            // Update Fenwick Tree for the current priority
            for (int idx = compressedPriority; idx < bit.length; idx += idx & -idx) {
                bit[idx]++;
            }
        }
        return starvation;
    }
}



class TableOfContentsGenerator {

    /**
     * Generates a table of contents from a list of strings,
     * identifying chapters and sections based on markup.
     *
     * @param linesOfText An array of strings, where each string is a line of text.
     * @return A list of strings representing the formatted table of contents.
     */
    public List<String> tableOfContents(String[] linesOfText) {
        List<String> tableOfContentsList = new ArrayList<>();
        int chapterNumber = 0;
        int sectionNumber = 0;

        for (String currentLine : linesOfText) {
            if (currentLine.startsWith("# ")) {
                // This line is a chapter title.
                chapterNumber++;
                sectionNumber = 0; // Reset section counter for the new chapter.

                String chapterTitle = currentLine.substring(2);
                tableOfContentsList.add(chapterNumber + ". " + chapterTitle);

            } else if (currentLine.startsWith("## ")) {
                // This line is a section title.
                // It's only a valid section if a chapter has already been started.
                if (chapterNumber > 0) {
                    sectionNumber++;

                    String sectionTitle = currentLine.substring(3);
                    tableOfContentsList.add(chapterNumber + "." + sectionNumber + ". " + sectionTitle);
                }
            }
            // Lines without a '#' or '##' prefix are simply ignored.
        }
        return tableOfContentsList;
    }
}


class Resultt{

    /*
     * Complete the 'tableOfContents' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY text as parameter.
     */

    public static List<String> tableOfContents(List<String> text) {
        List<String> tableOfContentsList = new ArrayList<>();
        int chapterNumber = 0;
        int sectionNumber = 0;

        for (String currentLine : text) {
            if (currentLine.startsWith("# ")) {
                // This line is a chapter title.
                chapterNumber++;
                sectionNumber = 0; // Reset section counter for the new chapter.

                String chapterTitle = currentLine.substring(2);
                tableOfContentsList.add(chapterNumber + ". " + chapterTitle);

            } else if (currentLine.startsWith("## ")) {
                // This line is a section title.
                // It's only a valid section if a chapter has already been started.
                if (chapterNumber > 0) {
                    sectionNumber++;

                    String sectionTitle = currentLine.substring(3);
                    tableOfContentsList.add(chapterNumber + "." + sectionNumber + ". " + sectionTitle);
                }
            }
            // Lines without a '#' or '##' prefix are simply ignored.
        }
        return tableOfContentsList;
    }

}

//This is wrong
class TableOfContents {

    /**
     * Completes the function tableOfContents in the editor with the following parameter:
     * string text[]: the lines in the table of contents
     *
     * Returns:
     * string[]: each string is a line in the table of contents
     */
    public String[] tableOfContents(String[] text) {
        List<String> result = new ArrayList<>();
        int chapterNumber = 0;
        int sectionNumber = 0;

        for (String line : text) {
            if (line.startsWith("# ")) {
                chapterNumber++;
                sectionNumber = 0;
                String title = line.substring(2);
                result.add(chapterNumber + ". " + title);
            } else if (line.startsWith("## ")) {
                sectionNumber++;
                String title = line.substring(3);
                result.add(chapterNumber + "." + sectionNumber + ". " + title);
            }
        }

        return result.toArray(new String[0]);
    }
}


