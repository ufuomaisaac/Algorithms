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

}
