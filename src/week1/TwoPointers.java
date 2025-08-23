package week1;

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



}



