package uvg.edu.gt;

import java.util.Arrays;

public class PrefixCalc {

    private static String[] operators = {"+", "-", "*", "/"}; // Lista de operadores permitidos
    private Stack<Integer> stack; // Pila utilizada para realizar los cálculos

    /**
     * Calcula el resultado de una expresión en notación prefija.
     *
     * @param expresion Expresión en notación prefija.
     * @return Resultado de la expresión.
     */
    public Integer calcularPrefijo(String expresion) {
        String[] val = expresion.split(" "); // Divide la expresión en tokens

        stack = new Stack<Integer>(); // Inicializa la pila

        boolean flag = true; // Bandera para controlar errores
        int num1;
        int num2;
        int result = 0;

        // Recorre la expresión de derecha a izquierda
        for (int i = val.length - 1; i >= 0; i--) {
            if (Arrays.asList(operators).contains(val[i])) { // Si el token es un operador
                if (stack.count() >= 2) { // Verifica si hay suficientes operandos en la pila
                    // Obtiene los dos últimos números de la pila
                    num1 = stack.pop();
                    num2 = stack.pop();
                    switch (val[i]) {
                        case "+": // Suma
                            stack.push(num1 + num2);
                            break;
                        case "-": // Resta
                            stack.push(num1 - num2);
                            break;
                        case "*": // Multiplicación
                            stack.push(num1 * num2);
                            break;
                        case "/": // División
                            if (num1 != 0) // Evita la división por cero
                                stack.push(num1 / num2);
                            else { // Maneja el error de división por cero
                                System.out.println("Error: División entre cero");
                                return null;
                            }
                            break;
                    }
                } else { // Maneja el error de falta de operandos
                    System.out.println("Error: Faltan operandos");
                    flag = false;
                }
            } else { // Si el token es un número, lo apila
                stack.push(Integer.parseInt(val[i]));
            }
        }
        // Si queda un único elemento en la pila, es el resultado
        if (stack.count() == 1 && flag)
            result = stack.pop();
        else if (!flag) // Si hubo errores de operandos faltantes
            return null;
        else { // Si quedaron operadores sin usar
            System.out.println("Error: Faltan operadores");
            flag = false;
        }
        // Si no hubo errores, devuelve el resultado; de lo contrario, devuelve nulo
        if (flag)
            return result;
        else
            return null;
    }
}