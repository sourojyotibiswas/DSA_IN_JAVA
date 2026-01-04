import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameWithIntegers{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());

            if(n % 3 == 0) System.out.println("Second");
            else System.out.println("First");
        }
    }
}