import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class OnlineStockSpan {
    // generic pair class {price, span}
    public static class Pair<T, U> {
        T first;
        U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    Deque<Pair<Integer, Integer>> st;

    public OnlineStockSpan() {
        st = new ArrayDeque<>();
    }
    public int next(int price) {
        int span = 1;

        while(!st.isEmpty() && st.peek().first <= price) {
            span += st.peek().second;
            st.pop();
        }
        st.push(new Pair<>(price, span)); // <> tells java which generic types you're using
        return span;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OnlineStockSpan obj = new OnlineStockSpan();
        int param_1 = obj.next(100);
        int param_2 = obj.next(80);
        int param_3 = obj.next(60);
        int param_4 = obj.next(70);
        int param_5 = obj.next(60);
        int param_6 = obj.next(75);
        int param_7 = obj.next(85);

        System.out.println(param_1 + " " + param_2 + " " + param_3 + " " + param_4 + " " + param_5 + " " + param_6 + " " + param_7);
        br.close();
    }
}
