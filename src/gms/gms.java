package gms;

import java.util.*;

public class gms {

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



