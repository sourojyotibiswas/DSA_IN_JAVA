import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 1. Check for all substring if it's a palindrome?
 * 2. Expand around the center (1 for odd + 1 for even)
 */
public class LongestPalindromicSubstring {
    private static int checkPalindrome (String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--; j++;
        }
        return j - i - 1;
    }

    public static String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1) return s;
        
        int start = 0, end = 0; // to keep track of window
        for(int i = 0; i < n; i++) {
            int oddLength = checkPalindrome(s, i , i);
            int evenLength = checkPalindrome(s, i, i+1);

            int len = Math.max(oddLength, evenLength);
            if(len > end - start + 1) {
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }

        return s.substring(start, end + 1);
    }

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inp = br.readLine();
        System.out.println(longestPalindrome(inp));
        br.close();
    }
}