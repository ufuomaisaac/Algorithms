package week1;

import java.util.HashMap;

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


    public int subarraysDivByK(int[] nums, int k) {

    }



}
