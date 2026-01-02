public class Stack {
    private int n;
    private int arr[];
    private int top;

    Stack(int n) {
        this.n = n;
        this.arr = new int[n];
        this.top = -1;
    }

    public boolean isEmpty() { return (top == -1); }

    public boolean isFull() { return (top == n-1); }

    public void push(int e) {
        if(isFull()) throw new RuntimeException("Stack Overflow");
        arr[++top] = e;
    }

    public int pop() {
        if(isEmpty()) throw new RuntimeException("Stack Underflow");
        return arr[top--];
    }

    public int peek() {
        if(isEmpty()) return -1;
        return arr[top];
    }

    public void printStack() {
        if(isEmpty()) throw new RuntimeException("Stack is Empty");
        for(int it : arr) { System.out.print(it + " "); }
        System.out.println();
    }

    public static void main (String[] args) {
        System.out.println("hello vim...");

        Stack st = new Stack(5);
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);

        st.printStack();

        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.pop());

        st.printStack();       

    }
}
