import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CoverInWater{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /**
         * -> #...#..#.#
         * -> ..#.#..
         */
        int t;
        t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();

            boolean continuous_three_empty_cells = false;
            int total_count_of_empty_cells = 0;

            for(int i=0; i < n; i++){
                if(s.charAt(i) == '.' && i+1 < n && s.charAt(i+1) == '.' && i+2 < n && s.charAt(i+2) == '.'){
                    continuous_three_empty_cells = true;
                    break;
                }
                if(s.charAt(i) == '.') total_count_of_empty_cells++;
            }
            if(continuous_three_empty_cells) System.out.println(2);
            else System.out.println(total_count_of_empty_cells);
        }
        br.close();
    }
}