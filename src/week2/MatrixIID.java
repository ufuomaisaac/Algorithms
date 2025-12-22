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



    /**
     * O(M*N) Time, O(1) Space: Set Matrix Zeroes (LC 73).
     * * Strategy: Use the first row and column as "markers" to avoid O(M+N) extra space.
     * * 1. Store original zero-state of Row 0 and Col 0 in 2 boolean flags.
     * 2. Scan internal matrix (1,1 to M,N); if element is 0, set its Row-start and Col-start to 0.
     * 3. Re-scan internal matrix; if its Row-start or Col-start is 0, set element to 0.
     * 4. Finally, use the 2 boolean flags to zero out Row 0 and Col 0 if they originally had zeros.
     */

    // Leetcode 73
    // Set Matrix Zeroes
    public void setZeroes(int[][] matrix) {
        /**
         * O(M*N) Time, O(1) Space - Set Matrix Zeroes
         */

        //Guide clause
        if(matrix == null || matrix.length == 0) return;

        int rows  = matrix.length;
        int columns = matrix[0].length;

        boolean firstRowHasZero = false;
        boolean firstColumnHasZero = false;


        // PHASE 1: Record original state of the first row and column
        // We do this because we will soon use them as storage makers
        for(int i = 0; i < rows; i++){
            if(matrix[i][0] == 0) {
                firstRowHasZero  = true;
                break;
            }
        }

        for(int j = 0; j < columns; j++) {
            if(matrix[0][j] == 0) {
                firstColumnHasZero = true;
                break;
            }
        }

        //Phase 2: Mark the makers
        //Scan internal matrix; if matrix [i][j] is 0, mark the "head" of that row and column;
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < columns; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Phase 3: Use Markers to fill the inner matrix with zeros
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < columns; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //PHASE 4: finalize the first row and column
        //we do this last so we dont interfere with the internal marking process
        if(firstRowHasZero) {
            for(int i = 0; i < rows; i++){
                matrix[i][0] = 0;
            }
        }

        if(firstColumnHasZero) {
            for(int j = 0; j < columns; j++) {
                matrix[0][j] = 0;
            }
        }
    }


    public boolean isValidSudoku(char[][] board) {
        /**
         * O(1) Time, O(1) Space - Valid Sudoku
         * Note: Time is constant because the board is always 9x9.
         */

        boolean[][] rowSeen= new boolean[9][9];
        boolean[][] columnSeen = new boolean[9][9];
        boolean[][] boxSeen = new boolean[9][9];

        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++) {

                char currentVal = board[r][c];

                if(currentVal == '.') {
                    continue;
                }

                //Convert char '1'-'9' to index 0-8
                int numIndex = currentVal - '1';
                int boxIndex = (r / 3) * 3 + (c / 3);



            }
        }

    }

}
