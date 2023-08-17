import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class Queue<T> implements IQueue<T> {

    public Object[] queue;
    private int top = -1;
    private int base = 0;

    public Queue(int size) {
        this.queue = new Object[size];
    }

    private int move(int pos) {
        return pos + 1 == queue.length ? 0 : pos + 1;
    }

    //#region members 'API'

    public int size() {
        int counter = 0;
        for (Object object : queue) {
            counter += !Objects.isNull(object) ? 1 : 0;
        }
        return counter;
    }

    public boolean isFull() {
        return !isEmpty() && top + 1 == base;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * @return First element in queue.
     */
    public T first() {
        return (T) queue[base];
    }

    /**
     * @return Last element in queue.
     */
    public T last() {
        return !isEmpty() ? (T) queue[top] : null;
    }

    /**
     * @param element An element to insert in queue.
     */
    public void enqueue(T element) {
        if (!isFull()) {
            top = move(top);
            queue[top] = element;
        }
    }

    /**
     * @return Element The element removed from queue.
     */
    public T dequeue() {
        T element = null;
        if (top == base) {
            element = (T) queue[top];
            clear();
        }

        if (!isEmpty()) {
            element = (T) queue[base];
            queue[base] = null;
            base = move(base);
        }

        return element;
    }

    /**
     * @implNote Clear queue.
     */
    public void clear() {
        for (int i = 0; i < queue.length; i++) {
            queue[i] = null;
        }

        top = -1;
        base = 0;
    }

    public void forEach(BiConsumer<T, Integer> fn) {
        int zeroBasedIndex = base;
        for (int i = 0; i < queue.length; i++) {
            fn.accept( (T) queue[zeroBasedIndex], i);
            zeroBasedIndex = move(zeroBasedIndex);
        }
    }

    public void forEach(Consumer<T> fn) {
        int zeroBasedIndex = base;
        for (int i = 0; i < queue.length; i++) {
            fn.accept( (T) queue[zeroBasedIndex]);
            zeroBasedIndex = move(zeroBasedIndex);
        }
    }


    //#endregion
}