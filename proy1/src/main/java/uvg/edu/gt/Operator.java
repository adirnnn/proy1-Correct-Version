package uvg.edu.gt;

public class Operator {

    /**
     * Compara dos valores para verificar si son iguales.
     * @param input Expresión que contiene los valores a comparar.
     * @return "True" si los valores son iguales, "False" de lo contrario.
     */
    public String equals(String input) {
        String value = "";
        String value2 = "";
        String output = "";
        String[] analyse = input.split(" ");
        int i = 0;

        if (analyse[i].equals("=") || analyse[i].equals("equals")) {
            i++;
        }
        value = analyse[i];
        value2 = analyse[i + 1];
        if (value.equals(value2)) {
            output = "True";
        } else {
            output = "False";
        }
        return output;

    }

    /**
     * Comprueba si el primer valor es menor que el segundo.
     * @param lisp Expresión que contiene los valores a comparar.
     * @return "True" si el primer valor es menor que el segundo, "False" de lo contrario.
     */
    public String isSmaller(String lisp) {
        int value = 0;
        int value2 = 0;
        String output = "";
        String[] analyse = lisp.split(" ");
        int i = 0;
        if (analyse[i].equals("<")) {
            i++;
        }

        value = Integer.parseInt(analyse[i]);
        value2 = Integer.parseInt(analyse[i + 1]);

        if (value < value2) {
            output = "True";
        } else {
            output = "False";
        }
        return output;

    }

    /**
     * Comprueba si el primer valor es mayor que el segundo.
     * @param lisp Expresión que contiene los valores a comparar.
     * @return "True" si el primer valor es mayor que el segundo, "False" de lo contrario.
     */
    public String isBigger(String lisp) {
        int value = 0;
        int value2 = 0;
        String output = "";
        String[] analyse = lisp.split(" ");
        int i = 0;
        if (analyse[i].equals(">")) {
            i++;
        }

        value = Integer.parseInt(analyse[i]);
        value2 = Integer.parseInt(analyse[i + 1]);

        if (value > value2) {
            output = "True";
        } else {
            output = "False";
        }
        return output;

    }

    /**
     * Verifica si la expresión es un átomo.
     * @param lisp Expresión a verificar.
     * @return "True" si la expresión es un átomo, "NIL" de lo contrario.
     */
    public String isAtom(String lisp) {

        String atom = "NIL";
        String[] analyse = lisp.split(" ");
        int i = 0;
        if (analyse[i].contains("Atom")) {
            i++;
        }
        if ((analyse.length - i) <= 1) {
            atom = "True";
        }
        return atom;
    }

    /**
     * Verifica si la expresión es una lista.
     * @param lisp Expresión a verificar.
     * @return "True" si la expresión es una lista, "NIL" de lo contrario.
     */
    public String isList(String lisp) {
        String list = "NIL";
        String[] analyse = lisp.split(" ");
        int i = 0;
        if (analyse[i].contains("List")) {
            i++;
        }
        if ((analyse.length - i) <= 1) {
            list = "True";
        }
        return list;
    }

}