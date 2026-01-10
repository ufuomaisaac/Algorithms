package week2;

import java.util.Stack; // 1. TRACK LAST INDEX: Consistent with your first example

public class Stacks {

    public String removeDuplicateLetters(String s) {
        // 1. TRACK LAST INDEX: Consistent with your first example
        int [] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[ s.charAt(i) - 'a'] = i;
        }

        // 2. TOOLS: Consistent with 'seen' and 'st' naming
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char currChar = s.charAt(i);
            int curr = currChar - 'a';

            // 3. SKIP IF SEEN; pick only one instance of the char
            if(seen[curr]) continue;

            // 4. BULLY MECHANIC: Monotonic Stack Logic
            // While the top is "bigger" than the current AND it appears again
            while(!stack.isEmpty() && stack.peek() > currChar && i < lastIndex[stack.peek() - 'a']){
                char removed = stack.pop();
                seen[removed - 'a'] = false; // Mark as unseen so it can be re-added later
            }

            stack.push(currChar);
            seen[curr]= true;
        }

        // 5. BUILD RESULT; Using the StringBuilder for O(N) construction
        StringBuilder sb = new StringBuilder();
        for(char c : stack) {
            sb.append(c);
        }
        return sb.toString();

    }
}

/**
 * DOUBLE-PUSH INTUITION:
 * - We use the Stack itself as a "time machine."
 * - Every time a new minimum is born, the old minimum is buried
 * directly beneath it.
 * - This ensures that 'min' always knows what its predecessor
 * was without needing a second stack.
 */

/**
 * MIN STACK CODE RECAP:
 * 1. INITIAL MIN: Start with Integer.MAX_VALUE.
 * 2. DOUBLE PUSH: 'stack.push(min)' followed by 'min = x' and
 * 'stack.push(x)' saves the state of the world before the change.
 * 3. DOUBLE POP: 'min = stack.pop()' inside the pop method
 * restores the state of the world as it was before that minimum
 * was added.
 * 4. PEEK: 'top()' and 'getMin()' never change the stack; they
 * only read the current state.
 */
// LeetCode 155 ----> Min Stack

class MinStack {
    // We start with the largest possible value so the first push always triggers a save
    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack =new Stack<>();

    public MinStack() {
    }

    public void push(int val) {
        // 1. THE "BACKUP" LOGIC:
        // If the new value x is smaller than or equal to our current min,
        // we save the OLD min onto the stack before pushing x.
        if(val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);

    }

    public void pop() {
        // 2. THE "RECOVERY" LOGIC:
        // If the value we are popping is the current minimum,
        // the value directly below it in the stack is the PREVIOUS minimum.
        if (stack.pop() == min) {
            min = stack.pop();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * TWO-STACK MINSTACK RECAP:
 * 1. SYNCHRONIZATION: Both stacks always stay the same size.
 * 2. DATA STACK: Holds the literal numbers in order.
 * 3. MIN STACK: At index 'i', it holds the minimum value of
 * all elements from index 0 to 'i' in the data stack.
 * 4. GETMIN: Simply peek at the top of the minStack.
 * 5. POP: Always pop from both stacks to maintain alignment.
 */
class MinStackk {
    // Stores all the actual values pushed
    private Stack<Integer> dataStack = new Stack<>();
    // Stores the minimum value present in dataStack at any given level
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        dataStack.push(x);

        // If minStack is empty, x is the current minimum.
        // Otherwise, x is the minimum only if it's smaller than or equal
        // to the current top of minStack.
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        } else {
            // If x is NOT the new minimum, we push the current
            // minimum again to keep the stacks synchronized in height.
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

