import java.util.Scanner;

public class LineTrip{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while(t-- > 0){
            int n, x;
            n = scanner.nextInt();
            x = scanner.nextInt();

            int[] arr = new int[n+1];

            /**
             * Here instead of storing the inputs in an array we can directly calculate the difference and update maxdiff
             * It will get rid of the extra space
             */

            arr[0] = 0;
            for(int i = 1; i < n+1; i++){
                arr[i] = scanner.nextInt();
            }

            int maxDiff = 1;
            for(int i = 0; i < n; i++){
                int diff = arr[i+1]-arr[i];
                if(diff > maxDiff) maxDiff = diff;
            }

            int lastLap = (x-arr[n])*2;
            if(lastLap > maxDiff) maxDiff = lastLap;

            System.out.println(maxDiff);
        }
        scanner.close();
    }
}