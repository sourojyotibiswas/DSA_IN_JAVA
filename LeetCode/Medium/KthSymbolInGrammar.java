import java.io.*;
import java.util.*;

/**
 * The K-th symbol in row n depends on whether it lies in the left or right half of the previous row.
 * If it lies in the left half, it is the same as the (n−1) row’s value; if in the right half, it is the inverse of that value.
 * Recursively apply this logic until reaching the base case (n = 1, k = 1 → 0).
 */

public class KthSymbolInGrammar {
    // MLE
    // public int kthGrammar(int n, int k) {
    //     int row = n;
    //     String s = "0";
    //     for(int i = 1; i < n; i++) {
    //         int m = s.length();
    //         StringBuilder sb = new StringBuilder();
    //         for(int j = 0; j < m; j++) {
    //             if(s.charAt(j) == '0') sb.append("01");
    //             else sb.append("10");
    //         }
    //         s = sb.toString();
    //     }

    //     if(s.charAt(k-1) == '1') return 1;
    //     else return 0;
    // }

    public static int recursion(int n, int k) {
        if(n == 1 && k == 1) return 0;
        int len = (int)Math.pow(2, n-1);
        int mid = len / 2;

        if(k <= mid) {
            return recursion(n-1, k);
        }
        return 1-recursion(n-1, k-mid);
    }

    public static int kthGrammar(int n, int k) {
        return recursion(n, k);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(kthGrammar(2, 2));
    }
}