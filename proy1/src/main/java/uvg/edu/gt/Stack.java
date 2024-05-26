package uvg.edu.gt;
import java.util.ArrayList;

public class Stack<T> implements UVGStack<T> {

    private ArrayList<T> arrayList; // ArrayList utilizado para almacenar los elementos de la stack

    /**
     * Constructor de la clase Stack.
     * Inicializa el ArrayList.
     */
    public Stack() {
        arrayList = new ArrayList<T>();
    }

    /**
     * Agrega un elemento a la parte superior de la stack.
     *
     * @param value Valor a agregar a la stack.
     */
    @Override
    public void push(T value) {
        arrayList.add(0, value); // Agrega el elemento al principio de la lista
    }

    /**
     * Remueve y devuelve el elemento en la parte superior de la stack.
     *
     * @return Elemento en la parte superior de la stack.
     */
    @Override
    public T pop() {
        return arrayList.remove(0); // Remueve y devuelve el primer elemento de la lista
    }

    /**
     * Devuelve el elemento en la parte superior de la stack sin removerlo.
     *
     * @return Elemento en la parte superior de la stack.
     */
    @Override
    public T top() {
        return arrayList.get(0); // Devuelve el primer elemento de la lista sin removerlo
    }

    /**
     * Verifica si la stack está vacía.
     *
     * @return true si la stack está vacía, false de lo contrario.
     */
    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty(); // Verifica si la lista está vacía
    }

    /**
     * Devuelve el número de elementos en la stack.
     *
     * @return Número de elementos en la stack.
     */
    @Override
    public int count() {
        return arrayList.size(); // Devuelve el tamaño de la lista
    }
}