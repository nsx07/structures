import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IStack<T> {
    void add(T element);
    T remove();
    int size();
    T first();
    T last();
    void forEach(Consumer<T> fn);
    void forEach(BiConsumer<T, Integer> fn);
}
