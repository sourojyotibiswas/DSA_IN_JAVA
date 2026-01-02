// LC-66

import java.io.*;
import java.util.*;

public class Problem_2 {
    /**
     * Approach 1:-
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        
        if(digits[n-1] < 9) {
            digits[n-1] = digits[n-1] + 1;
            return digits;
        }
        int carry = 0;
        for(int i = n-1; i >= 0; i--) {
            if(digits[i] == 9 && i == n-1) {
                digits[i] = 0;
                carry = 1;
            }
            else if(carry == 1 && digits[i] == 9) {
                digits[i] = 0;
            }
            else if(carry == 1) {
                digits[i] = digits[i] + 1;
                carry = 0;
            }
        }
        if(carry == 1) {
            int[] res = new int[n+1];
            res[0] = 1;
            for(int i = 1; i < n+1; i++) {
                res[i] = 0;
            }
            return res;
        }
        return digits;
    }

    /**
     * Approach 2:-
     */
    // public static int[] plusOne(int[] digits) {
    //     int n=digits.length;
    //     for(int i=n-1; i>=0; i--){
    //         digits[i]++;
    //         if (digits[i]<10) return digits;
    //         digits[i]=0;
    //     }
    //     digits=new int[n+1];
    //     digits[0]=1;
    //     return digits;
    // }
    public static void main(String[] args) throws Exception {
         int[] digits = {9, 9, 9};
        int[] result = plusOne(digits);

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}