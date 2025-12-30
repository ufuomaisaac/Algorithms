package week2;

import java.util.Stack;

public class Stacks {
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

