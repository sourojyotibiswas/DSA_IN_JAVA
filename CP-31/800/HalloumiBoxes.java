import java.util.Scanner;
import java.util.Arrays;

public class HalloumiBoxes{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t;
        t = scanner.nextInt();
        while(t-- > 0){
            int n, k;
            n = scanner.nextInt();
            k = scanner.nextInt();

            long[] arr = new long[n];
            for(int i = 0; i < n; i++){
                arr[i] = scanner.nextLong();
            }

            long[] copy_a = arr.clone();
            Arrays.sort(copy_a);

            if(Arrays.equals(arr, copy_a) || k > 1) System.out.println("YES");
            else System.out.println("NO");
        }
        scanner.close();
    }
}