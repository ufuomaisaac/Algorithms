package week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {

    // Two Sum II - Input Array Is Sorted
    // Leetcode 167
    public int[] twoSum(int[] numbers, int target) {

        int leftIndex = 0;
        int rightIndex = numbers.length - 1;

        while(leftIndex < rightIndex) {
            int total = numbers[leftIndex] + numbers[rightIndex];

            if (total == target){
                return new int[]{leftIndex + 1, rightIndex + 1};
            } else if (total > target) {
                rightIndex -= 1;
            } else {
                leftIndex += 1;
            }
        }

        return new int[]{};
    }


    // Container with the most water
    // Leetcode 11
    public int maxArea(int[] height) {

        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int area = 0;

        while(leftIndex < rightIndex) {

            int minHeight = Math.min(height[leftIndex], height[rightIndex]);

            area = Math.max(minHeight * (rightIndex - leftIndex), area);

            if(height[leftIndex] < height[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }

        }
        return area;
    }


    // 3Sum
    // Leetcode 15
    //56--++==
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Sort the array

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total < 0) {
                    j++;  // Need a bigger number
                } else if (total > 0) {
                    k--;  // Need a smaller number
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    // Skip duplicates for j
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    // Skip duplicates for k
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return result;
    }



}



