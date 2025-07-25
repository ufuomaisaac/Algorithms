public class Algorithms {



    //LEETCODE
    //1962. Remove Stones to Minimize the Total
    //My approach is to apply the rule to the biggest integer in the array removing more value from the largest integer
    public static int minStoneSum(int[] piles, int k) {

        int totalStone = 0;
        while(k > 0) {
            for (int i = 0; i < piles.length; i++) {

                for (int j = 0; j < piles.length; j++) {

                    if (piles[i] > piles[j] & j == piles.length - 1) {
                        totalStone += piles[i] / 2;
                        k--;
                        piles[i] = 0;
                    }
                }
                totalStone += piles[i];
            }
        }
        return totalStone;
    }



    //LEETCODE
    //189. Rotate Array
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }



    // Leetcode 238
    // Product of Array except self
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int product = 1;

        for (int i = 0; i < nums.length; i++) {
            res[i] = product;
            product *= nums[i];
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= product;
            product *= nums[i];
        }
        return res;
    }



    // Leetcode 122.  Max Profit
    // Best Time to Buy and Sell Stock II
    public static int maxProfit(int[] prices) {

        int maxProfit = 0;
        int currentProfit = 0;
        for(int i = 1; i < prices.length; i++){

            if (prices[i] > prices[i - 1]) {
                currentProfit = prices[i] - prices[i - 1];
                maxProfit += currentProfit;
            }
        }
        return maxProfit;
    }



    // Leetcode 41.
    // First Missing Positive
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (val <= n) {
                nums[val - 1] = -Math.abs(nums[val - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }



    // LeetCode 2348.
    // Number of Zero-Filled Subarrays
    public static long zeroFilledSubarray(int[] nums) {

        long result = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                result += 1;

                for(int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 0) {
                        result += 1;
                    } else
                        break;
                }
            }
        }
        return result;
    }



    // Leetcode 334.
    // Increasing Triplet Subsequence
    public static boolean increasingTriplett(int[] nums) {

        int max1 = Integer.MAX_VALUE;
        int max2 = Integer.MAX_VALUE;

        for(int n : nums) {
            if(n <= max1) max1 = n;
            else if(n <= max2) max2 = n;
            else return true;
        }

        return false;
    }
}
