package uvg.edu.gt;

import java.util.ArrayList;

public class App {

    /**
     * Método principal que inicia el programa.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        Reader fileScanner = new Reader();
        String file = "src\\main\\java\\uvg\\edu\\gt\\Lisp.txt";
        Interprete interprete = new Interprete();
        ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
        String var;

        System.out.println("-------------------------");
        System.out.println("Interprete de LISP");
        System.out.println("-------------------------");

        // Lee el archivo de LISP y obtiene las expresiones
        ArrayList<String> expresions = fileScanner.Parse(file);

        // Tokeniza cada expresión y realiza la interpretación
        for (String s : expresions) {
            tokens.add(tokenizer.Tokens(s));
        }
        for (ArrayList<String> juan : tokens) {
            if (juan != null) {
                String expresion = "";
                for (String s : juan) expresion += s + " ";
                System.out.println(expresion);

                // Realiza la interpretación de la expresión y muestra el resultado
                var = interprete.operate(juan, tokenizer.getCommandType(juan));
                System.out.println(var.toString());
                System.out.println("\n");
            }
        }
    }
}