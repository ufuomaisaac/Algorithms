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


    /**
     * O(1) Single-Pass: Valid Sudoku (LC 36).
     * * Strategy: Use three boolean arrays to track digits 1-9 in Rows, Cols, and 3x3 Boxes.
     * * 1. numIndex = board[r][c] - '1' (Maps 1-9 to 0-8).
     * 2. boxIndex = (r/3)*3 + (c/3) (Maps grid position to sub-box 0-8).
     * 3. Check if 'numIndex' exists in rowSeen[r], colSeen[c], or boxSeen[boxIndex].
     * 4. If exists, return false. Else, mark as true and continue.
     * 5. Space/Time: O(1) because board size is fixed at 81 cells.
     */
    // Leetcode 36
    // Valid Sudoko
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
                if (currentVal == '.') {
                    continue;
                }

                //Convert char '1'-'9' to index 0-8
                int numIndex = currentVal - '1';
                int boxIndex = (r / 3) * 3 + (c / 3);

                // If number was already seen in row, column, or sub-box, it's invalid
                if (rowSeen[r][numIndex] || columnSeen[c][numIndex] || boxSeen[boxIndex][numIndex]) {
                    return false;
                }

                // Mark the number as seen in all three tracking matrices
                rowSeen[r][numIndex] = true;
                columnSeen[c][numIndex] = true;
                boxSeen[boxIndex][numIndex] = true;
            }
        }
        return true;
    }



    /**
     * O(M*N) Time, O(1) Space: Game of Life (LC 289).
     * * Strategy: In-place update using temporary states to track (Old, New).
     * * 1. States: 0(D->D), 1(L->D), 2(D->L), 3(L->L).
     * 2. Neighbor Count: Use (val == 1 || val == 3) to identify cells originally alive.
     * 3. First Pass: Apply Conway's rules to set states 0-3.
     * 4. Second Pass: Convert 2 and 3 to 1; convert 0 and 1 to 0.
     * * This allows simultaneous update simulation without extra memory.
     */
    // Leetcode 289
    // Game of life
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // 1. First pass: Determine future states and encode them
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = countLiveNeighbors(board, r, c);

                // Rule 1 & 3: Live cell dies
                // Rule 2: Live cell stays alive (State 3)
                if (board[r][c] == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        board[r][c] = 3; // 1 -> 1
                    }
                    // Else stays 1, which means 1 -> 0
                }
                // Rule 4: Dead cell becomes live (State 2)
                else {
                    if (liveNeighbors == 3) {
                        board[r][c] = 2; // 0 -> 1
                    }
                }
            }
        }

        // 2. Second pass: Normalize states to 0s and 1s
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If state is 2 or 3, it's alive in the future.
                if (board[r][c] == 2 || board[r][c] == 3) {
                    board[r][c] = 1;
                } else {
                    board[r][c] = 0;
                }
            }
        }
    }

    private int countLiveNeighbors(int[][] board, int r, int c) {
        int count = 0;
        // Scan the 3x3 area around the cell
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                // Skip the cell itself
                if (i == r && j == c) continue;

                // Check boundaries and original state
                // Original live states were 1 or 3
                if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                    if (board[i][j] == 1 || board[i][j] == 3) {
                        count++;
                    }
                }
            }
        }
        return count;
    }






    /**
     * O(1) Space In-Place Encoding Strategy:
     * * 1. ENCODING PHASE:
     * - We store two values in one integer: (Original_State * 10) + Neighbor_Count.
     * - Example: 13 means (Originally Alive, 3 Neighbors). 02 means (Originally Dead, 2 Neighbors).
     * - Critical: When counting neighbors, if a neighbor was already encoded (r < i or same row/prev col),
     * we must use 'val / 10' to get its ORIGINAL state.
     * * 2. DECODING/UPDATE PHASE:
     * - Extraction: neighCount = val % 10; isAlive = val / 10 == 1.
     * - Logic:
     * - Rule 1 & 3: If alive and neighbors < 2 or > 3 -> 0 (Die).
     * - Rule 2: If alive and neighbors == 2 or 3 -> 1 (Survive).
     * - Rule 4: If dead and neighbors == 3 -> 1 (Reproduce).
     * * Result: 0ms Runtime (Beats 100%) by avoiding extra matrix allocation.
     */
    // Leetcode 289
    // Game of life
    //Come back to this later
    public void gameOfLifee(int[][] board) {
        // Pass 1: Encode the board with neighbor counts and original states
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                encode(board, i, j);
            }
        }

        // Pass 2: Finalize the board state based on Conway's Rules
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                update(board, i, j);
            }
        }
    }

    /**
     * Helper to store (OriginalState * 10) + NeighborCount in each cell
     */
    void encode(int[][] board, int i, int j) {
        int aliveCount = 0;

        // Check all 8 neighbors in a 3x3 grid around cell (i, j)
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                int neighR = i + r;
                int neighC = j + c;

                // Ensure neighbor is within board boundaries
                if (neighR >= 0 && neighR < board.length
                        && neighC >= 0 && neighC < board[0].length) {

                    int neighVal = board[neighR][neighC];

                    // If neighbor was already encoded, extract original state via division
                    if (r == -1 || (r == 0 && c == -1)) {
                        neighVal = neighVal / 10;
                    }

                    // Count as alive if original value was 1 (and not looking at itself)
                    if (neighVal == 1 && !(r == 0 && c == 0)) {
                        aliveCount++;
                    }
                }
            }
        }
        // Encode: Tens place = original state; Ones place = live neighbor count
        board[i][j] = board[i][j] * 10 + aliveCount;
    }

    /**
     * Helper to decode the cell and apply the Game of Life rules
     */
    void update(int[][] board, int i, int j) {
        int neighCount = board[i][j] % 10; // Extract ones digit
        boolean isAlive = board[i][j] / 10 == 1; // Extract tens digit

        if (isAlive && (neighCount < 2 || neighCount > 3)) {
            board[i][j] = 0; // Rule 1 & 3: Under/Overpopulation
        } else if (isAlive && (neighCount == 2 || neighCount == 3)) {
            board[i][j] = 1; // Rule 2: Survival
        } else if (!isAlive && neighCount == 3) {
            board[i][j] = 1; // Rule 4: Reproduction
        } else {
            board[i][j] = isAlive ? 1 : 0; // Maintains current state if no rules apply
        }
    }

}
