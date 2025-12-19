package week2;

import java.util.ArrayList;
import java.util.List;

public class MatrixIID {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        // 1. Define the four boundaries (walls)
        int top = 0;
        int bottom  = matrix.length - 1;
        int leftColumn = 0;
        int rightColumn= matrix[0].length - 1;


    }



}
