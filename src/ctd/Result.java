package ctd;
import java.util.*;

import java.util.*;
import java.util.stream.Collectors;

public class Result {
    public static void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        fizzBuzz(15);
    }
}




class Solution {

    private static boolean canFormTeam(int K, List<Integer> lower, List<Integer> higher) {
        int n = lower.size();
        List<int[]> devs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int rMin = Math.max(1, K - higher.get(i));
            int rMax = Math.min(K, lower.get(i) + 1);
            if (rMin <= rMax) devs.add(new int[]{i + 1, rMax}); // [skill, maxRank]
        }

        if (devs.size() < K) return false;

        devs.sort(Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // min-heap

        for (int[] d : devs) {
            heap.add(d[0]);
            if (heap.size() > d[1]) heap.poll();
        }
        return heap.size() >= K;
    }

    public static int getOptimalTeamSize(List<Integer> lower, List<Integer> higher) {
        int n = lower.size(), low = 1, high = n, best = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canFormTeam(mid, lower, higher)) {
                best = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(getOptimalTeamSize(
                Arrays.asList(0, 4, 2, 3, 3),
                Arrays.asList(0, 1, 3, 2, 4))); // 3
    }
}



class Solutionn {

    /**
     * Finds the smallest non-negative integer (MEX) not present in the array.
     * This function runs in O(N) time because 'k' will not exceed N+1.
     */
    private static int findMex(int n, int[] counts) {
        int k = 0;
        while (k <= n) {
            if (counts[k] == 0) {
                return k;
            }
            k++;
        }
        // Should not be reached given the problem constraints
        return n + 1;
    }

    public static List<Integer> findValidSizes(List<Integer> memoryBlocks) {
        int n = memoryBlocks.size();

        // Use an array to store the frequency counts for values 0 to n.
        // Size n+1 ensures we can check up to MEX = n.
        int[] counts = new int[n + 1];

        // Step 1: Calculate initial frequencies
        for (int x : memoryBlocks) {
            // Only values up to n matter for finding MEX <= n.
            if (x <= n) {
                counts[x]++;
            }
        }

        Set<Integer> validSizes = new HashSet<>();

        // Step 2: Calculate MEX without any operation (Base Case)
        int mexOriginal = findMex(n, counts);
        validSizes.add(mexOriginal);

        // Step 3: Calculate MEX with exactly one operation.
        // We iterate through every possible value 'v' that could be increased.
        // The operation v -> v+1 is only valid if v < n-1.
        for (int v = 0; v <= n - 2; v++) {

            // The operation is only possible if at least one block has the size 'v'.
            if (counts[v] > 0) {
                // Simulate Operation: Change one 'v' to 'v+1'
                counts[v]--;
                counts[v + 1]++;

                // Find the New MEX
                int mexNew = findMex(n, counts);
                validSizes.add(mexNew);

                // Undo Simulation (Restore counts)
                counts[v]++;
                counts[v + 1]--;
            }
        }

        // Step 4: Final Output
        List<Integer> result = new ArrayList<>(validSizes);
        Collections.sort(result);
        return result;
    }

    // For local testing
    public static void main(String[] args) {
        // Sample 1 (from image)
        System.out.println("Input [0, 3, 4], n=5: " + findValidSizes(Arrays.asList(0, 3, 4))); // Expected: [0, 1]

        // Sample 2 (from image)
        System.out.println("Input [0, 2, 2], n=3: " + findValidSizes(Arrays.asList(0, 2, 2))); // Expected: [0, 1]

        // Sample 3 (from analysis: [0, 0, 0], n=3) - Original MEX 1, New MEX 2 is possible.
        System.out.println("Input [0, 0, 0], n=3: " + findValidSizes(Arrays.asList(0, 0, 0))); // Expected: [1, 2]

        // Sample 4 (from analysis: [2, 2, 2], n=3)
        System.out.println("Input [2, 2, 2], n=3: " + findValidSizes(Arrays.asList(2, 2, 2))); // Expected: [0]
    }
}

