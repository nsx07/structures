package interfaces;

import java.util.function.Function;

public interface ITree<T> {

    void add(T element);
    T remove(T element);
    T get(T element);
    T get(Function<? super T, Boolean> predicate);
    void print();

}