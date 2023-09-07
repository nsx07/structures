package interfaces;

public interface ITree<T> {

    void add(T element);
    T find(T element);
    T remove();

}