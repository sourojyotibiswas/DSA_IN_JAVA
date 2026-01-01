import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.HashSet;

// HashSet + 2 cases (what if set not contains else contains?)
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n == 0) return 0;
        // store unique characters in a range
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, res = 0;
        while(j < n) {
            char chj = s.charAt(j);
            char chi = s.charAt(i);
            if(!set.contains(chj)) {
                set.add(chj);
                res = Math.max(res, j - i + 1);
                j++;
            } else {
                set.remove(chi);
                i++;
            }
        }
        return res;
    }

    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inp = br.readLine();
        System.out.println(lengthOfLongestSubstring(inp));
        br.close();
    }
}