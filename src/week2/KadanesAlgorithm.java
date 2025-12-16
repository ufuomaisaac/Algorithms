package week2;

public class KadanesAlgorithm {


    // Leetcode 1014
    // Best Sightseeing Pair
    public int maxScoreSlighseeingPair(int []values){
        int result = Integer.MIN_VALUE;

    }


    // LeetCode
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

}
