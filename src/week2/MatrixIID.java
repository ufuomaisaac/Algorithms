package week2;

import java.util.ArrayList;
import java.util.List;

public class MatrixIID {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        // 1. Define the four boundaries (walls)
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        // 2. Process layers until boundaries cross each other
        while (top <= bottom && left <= right) {

            // Direction: Right (Traverse top row)
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++; // Top row is done, move the boundary down

            // Direction: Down (Traverse right column)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Right column is done, move the boundary left

            // Direction: Left (Traverse bottom row)
            // Safety Check: Ensure we still have a row to process after incrementing 'top'
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--; // Bottom row is done, move the boundary up
            }

            // Direction: Up (Traverse left column)
            // Safety Check: Ensure we still have a column after decrementing 'right'
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Left column is done, move the boundary right
            }
        }

        return result;
    }



    /**
     * O(N^2) Two-Step Rotation: Rotate Image (LC 48).
     * * Strategy: Clockwise 90 deg = Transpose + Horizontal Reverse.
     * * 1. Transpose: Swap elements across the main diagonal (row i, col j <-> row j, col i).
     * - Important: Inner loop starts at j = i + 1 to avoid swapping back.
     * 2. Reverse: For each row, swap elements from ends to middle using two pointers.
     * 3. Space Complexity: O(1) because all swaps are done in-place.
     */
    // Leetcode  48
    // Rotate Image
    // preferred solution
    public void rotate(int[][] matrix) {
        int n = matrix.length;


        // Step 1: Transpose (Swap matrix[i][j] with matrix[j][i])
        // We only iterate over the upper triangle (j = i + 1) to avoid double-swapping.
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
            // Step 2: Reverse each row
            // Using a simple two-pointer approach for each row.

        }
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }


    }

    public void reverseRow(int[] row) {
        int left = 0;
        int right = row.length - 1;

        while(left < right){
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }


    // Another solution
    public void rotatee(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) return;

        transpose(matrix);
        reverseRows(matrix);
    }

    private void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            // j starts at i + 1 to only swap the upper triangle with the lower triangle
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void reverseRows(int[][] matrix) {
        for (int[] row : matrix) {
            int left = 0;
            int right = row.length - 1;
            while (left < right) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
                left++;
                right--;
            }
        }
    }


}
