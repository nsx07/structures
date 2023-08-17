public class Queue<T> {

    public Object[] queue;
    private int size;
    private int top = -1;
    private int base = 0;

    public Queue(int size) {
        this.queue = new Object[size];
        this.size = size;
    }

    private int move(int pos) {
        return pos + 1 == queue.length ? 0 : pos + 1;
    }
    public boolean isFull() {
        return !isEmpty() && top + 1 == base;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void enqueue(T element) {
        if (top == base) {
            clear();
            return;
        }
        if (isFull()) {
            top = move(top);
            queue[top] = element;
        }
    }

    public void dequeue() {
        if (!isEmpty()) {
            base = move(base);
            queue[base] = null;
        }
    }

    public void clear() {
        for (int i = 0; i < queue.length; i++) {
            queue[i] = null;
        }
        top = -1;
    }

}