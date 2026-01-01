import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned)
 * Check overflow - if it will overflow when we multiply out current result by 10 in next step
 */

public class ReverseInteger {
    public static int reverse(int x) {
        int res = 0;

        while(x != 0) {
            // check if we multiply res by 10 will it overflow in next step
            if(res > Integer.MAX_VALUE/10 || res < Integer.MIN_VALUE/10) return 0;
            int lastDigit = x % 10;
            res = res * 10 + lastDigit;
            x /= 10;
        }
        return res;
    }

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inp = Integer.parseInt(br.readLine());
        System.out.println(reverse(inp));
        br.close();
    }
}