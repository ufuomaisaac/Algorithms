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

}
