import java.io.*;
import java.util.*;

public class Problem_3 {
    public static int numOfWays(int n) {
        int MOD = 1_000_000_007;

        // Base case: first row
        long twos = 6;    // patterns with 2 colors
        long threes = 6;  // patterns with 3 colors

        for (int i = 2; i <= n; i++) {
            long prevTwos = twos;
            long prevThrees = threes;

            twos = (2 * prevTwos + 2 * prevThrees) % MOD;
            threes = (2 * prevTwos + 3 * prevThrees) % MOD;
        }

        return (int)((twos + threes) % MOD);
    }
    public static void main(String[] args) throws Exception {
        System.out.print(numOfWays(5000));
    }
}