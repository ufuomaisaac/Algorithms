package week1;

import java.util.HashMap;
import java.util.Map;

public class PrefixSum {

    // Leetcode 560
    // Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        // HashMap stores: (prefix sum -> number of times it occurred)
        HashMap<Integer, Integer> subNum = new HashMap<>();

        // Important: prefix sum = 0 has occurred once by default
        // (this allows subarrays starting from index 0 to be counted)
        subNum.put(0, 1);

        int total = 0; // running prefix sum
        int count = 0; // answer (number of subarrays found)

        for (int n : nums) {
            total += n;  // update prefix sum up to current element

            // Check if we have seen (total - k) before.
            // If yes, then subarray from that point to here sums to k.
            if (subNum.containsKey(total - k)) {
                count += subNum.get(total - k);
            }

            // Record/update the current prefix sum
            subNum.put(total, subNum.getOrDefault(total, 0) + 1);
        }

        return count;
    }


    //If two prefix sums have the same remainder when divided by k,
    //then the subarray between them is divisible by k.
    // Leetcode 974.
    // Subarray Sums Divisible by K
    public int subarraysDivByK(int[] nums, int k) {

        HashMap<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);

        int count = 0;
        int prefixSum = 0;

        for (int n : nums) {
            prefixSum += n;

            // Adjust negative remainders to be positive
            int remainder = ((prefixSum % k) + k) % k;


           /* if (remainder < 0) {
                remainder += k;
            }*/
            count += remainderCount.getOrDefault(remainder, 0);

            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        return count;

    }


    // Leetcode 523.
    // Continuous Subarray Sum
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> remainderIndex = new HashMap<>();
        remainderIndex.put(0, -1); // Base case: prefix sum divisible by k before start

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;

            // Handle negative remainders in Java
            if (remainder < 0) {
                remainder += k;
            }

            if (remainderIndex.containsKey(remainder)) {
                if (i - remainderIndex.get(remainder) >= 2) {
                    return true;
                }
            } else {
                remainderIndex.put(remainder, i);
            }
        }
        return false;
    }


    // Leetcode 525.
    // Contiguous Array
    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        int prefixSum = 0;  // Keeps track of balance between 1s and 0s
        Map<Integer, Integer> prefixIndex = new HashMap<>();

        // Initialize with prefixSum = 0 at index -1
        prefixIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            // Treat 0 as -1, and 1 as +1
            prefixSum += (nums[i] == 1) ? 1 : -1;

            if (prefixIndex.containsKey(prefixSum)) {
                // Found a subarray with equal number of 0s and 1s
                maxLength = Math.max(maxLength, i - prefixIndex.get(prefixSum));
            } else {
                // Store the first index where this prefixSum occurs
                prefixIndex.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}
