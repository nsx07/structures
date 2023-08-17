import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IQueue<T> {
    void enqueue(T element);
    T dequeue();
    int size();
    T first();
    T last();
    void forEach(Consumer<T> fn);
    void forEach(BiConsumer<T, Integer> fn);
}
