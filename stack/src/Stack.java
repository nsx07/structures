@SuppressWarnings("unchecked")
public class Stack<T> {
    private final int size;
    private int top = -1;
    public final Object[] stack;

    public Stack(int size) {
        this.size = size;
        this.stack = (T[]) new Object[size];
    }

    private boolean isEmpty() {
        return top == -1;
    }

    private boolean isFull() {
        return top + 1 == size;
    }

    public void add(T element) {
        if (!isFull()) {
            top++;

            stack[top] = element;
        }
    }

    public T remove() {
        if (!isEmpty()) {

            T deletedElement = (T) stack[top];
            stack[top] = null;

            top--;
            return deletedElement;
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < stack.length; i++) {
            stack[i] = null;
        }
        top = -1;
    }

}