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
        return 0;

    }

}
