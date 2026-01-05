import java.io.*;
import java.util.*;

public class Problem_5 {
    /**
     * Approach 1:- Brute force
     * if number of -ve nos. is even then you can eliminate all -ve nos.
     * else if number of -ve nos. is odd then 1 -ve number will remain at the end 
     * so will try to make the samllest element -ve so to get max. result
     */
    public static long maxMatrixSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int min = Integer.MAX_VALUE;
        long sum = 0;
        int negNos = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                sum += Math.abs(matrix[i][j]);
                if(matrix[i][j] < 0) negNos++;
                min = Math.min(Math.abs(matrix[i][j]), min);
            }
        }

        if(negNos % 2 == 0) return sum;
        else return sum - 2*min;
    }
    public static void main(String[] args) throws Exception {
        int[][] matrix5 = {
            {-5, 2},
            {3, 4}
        };
        long result5 = maxMatrixSum(matrix5);
        System.out.println(result5);
    }
}