package week2;

public class KadanesAlgorithm {

    // Leetcode 1014
    // Best Sightseeing Pair
    public int maxScoreSlighseeingPair(int []values){
        int result = Integer.MIN_VALUE;


    }


    /**
     * O(N) Kadane's Algorithm: Maximum Subarray Sum (LC 53).
     * * Strategy: Dynamic Programming / Greedy. Find the largest sum subarray by ensuring currentSum is always beneficial.
     * * 1. Initialize: currentSum=0, maxSum=Integer.MIN_VALUE (or nums[0] if handling initialization differently).
     * 2. Iterate: Loop through the array (num).
     * 3. Update Current Sum: currentSum += num.
     * 4. Update Max Sum: maxSum = max(maxSum, currentSum).
     * 5. Reset Check (The Core): If currentSum < 0, reset currentSum = 0 (or simply use currentSum = max(num, currentSum + num) for a different but equivalent formulation).
     * 6. Return: maxSum.
     * The key step --> The essential step is checking if currentSum is negative. If it is, that prefix of the subarray will only drag down the sum of any future extension,
     * so it's always better to reset and start a new subarray from the next element.
     */

    // LeetCode 53
    // Maximum Subarray Sum problem
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for(int i = 0; i < nums.length; i++){
            currentSum += nums[i];

            if(currentSum > maxSum){
                maxSum = currentSum;
            }

            if(currentSum < 0){
                currentSum = 0;
            }
        }
        return maxSum;
    }


    /**
     * O(N) Kadane's Variation: Maximum Sum Circular Subarray (LC 918).
     * * Strategy: The max sum is either the standard max subarray OR (Total Sum - min subarray).
     * * 1. Initialize: total=0, currmax=0, currmin=0, max=nums[0], min=nums[0].
     * 2. Loop (num):
     * - Update total: total += num.
     * - Standard Kadane: currmax = max(num, currmax + num), update global max.
     * - Inverse Kadane: currmin = min(num, currmin + num), update global min.
     * 3. Edge Case: If all numbers are negative (max < 0), return max.
     * 4. Return: max(max, total - min).
     */
    // Leetcode 918
    // Maximum Sum Circular Subarray
    public int maxSubarraySumCircular(int[] nums) {
        int currentMax = 0, maxSubarray = 0;
        int currentMin = 0, minSubarray = 0;
        int totalSum = 0;

        for(int i = 0; i < nums.length; i++){
            totalSum += nums[i];

            // 1. Standard Kadane's for Maximum Subarray
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSubarray = Math.max(maxSubarray, currentMax);

            // 2. Standard Kadane's for Minimum Subarray
            currentMin = Math.min(nums[i], currentMin + nums[i]);
            minSubarray = Math.min(minSubarray, currentMin);
        }

        // 3. Special Case: If all numbers are negative
        // totalSum == minSubarray, so totalSum - minSubarray = 0.
        // We must return the maxSubarray (the largest single negative number).
        if(maxSubarray < 0) return maxSubarray;

        // 4. Return the better of the two: Linear Max vs Circular Max
        return Math.max(maxSubarray, totalSum - minSubarray);
    }


    /**
     * O(N) Prefix & Suffix Sweep: Maximum Product Subarray (LC 152).
     * * Strategy: The max product must be a prefix or a suffix (due to odd/even negatives).
     * * 1. Initialize: maxProd=Integer.MIN_VALUE, pref=1, suff=1.
     * 2. Loop (i=0 to N):
     * - Calculate pref: Cumulative product from index 0.
     * - Calculate suff: Cumulative product from index n-1-i.
     * - Update maxProd: max(maxProd, pref, suff).
     * - Reset: If pref or suff becomes 0, reset to 1 (treats 0 as a subarray boundary).
     * 3. Return: maxProd.
     */
    // Leetcode 152
    // Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        int prefixSum = 1, suffixSum = 1;

        for(int i = 0; i < nums.length; i++) {
            prefixSum *= nums[i];
            suffixSum  *= nums[n - i - 1];

            maxProduct = Math.max(maxProduct, Math.max(prefixSum, suffixSum));

            if(nums[i] == 0) prefixSum = 1;
            if(nums[n - i - 1] == 0) suffixSum = 1;

        }
        return maxProduct;
    }

}
