import week2.SlidingWindowDynamicSIze;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       String value =  Algorithms.reverseWords("Hello World  ");
       System.out.println(value);
       int[] nums = new int[] {1, 0, -1, 4, 6,9,-1};

        int[] result = SlidingWindowDynamicSIze.maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(result));

    }
}