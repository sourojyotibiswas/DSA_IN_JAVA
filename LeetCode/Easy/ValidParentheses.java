import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ValidParentheses {
    public static boolean solution(String s) {
        int n = s.length();
        // Stack as ArrayDeque
        Deque<Character> st = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if(!st.isEmpty()) {
                if(st.peek() == '(' && ch == ')') st.pop();
                else if(st.peek() == '[' && ch == ']') st.pop();
                else if(st.peek() == '{' && ch == '}') st.pop();
                else return false;
            }
            else st.push(ch);
        }

        return st.isEmpty();
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        boolean ans = solution(s);
        System.out.println(ans);
        br.close();
    }
}
