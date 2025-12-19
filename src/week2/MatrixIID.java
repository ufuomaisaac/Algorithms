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



}
