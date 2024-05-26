package uvg.edu.gt;

public interface UVGStack<T> {

    void push(T value);
    T pop();
    T top();
    boolean isEmpty();
    int count();
}