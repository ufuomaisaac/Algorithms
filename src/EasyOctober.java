import java.util.Arrays;

public class EasyOctober {



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

    //238
    //Product of Array except self
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

    // Lettcode 122.  Max Profit
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

    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i < nums.length){
            if(nums[i] > 0 && nums[i] < nums.length){
                int index = nums[i] - 1;
                if(nums[i] != nums[index]){
                    int temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
                else{
                    i++;
                }
            }else{
                i++;
            }
        }
        for(int j = 1; j <= nums.length; j++){
            if(nums[j - 1] != j){
                return j;
            }
        }
        return nums[nums.length - 1] + 1;
    }

    class Solution {
        public int firstMissingPositive(int[] nums) {
            int[] filteredNums = Arrays.stream(nums).filter(n -> n > 0).toArray();

            Arrays.sort(filteredNums);

            int target = 1;
            for (int n : filteredNums) {
                if (n == target) {
                    target++;
                } else if (n > target) {
                    return target;
                }
            }

            return target;
        }
    }
}
