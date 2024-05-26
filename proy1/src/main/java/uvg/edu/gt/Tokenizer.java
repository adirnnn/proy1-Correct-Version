package uvg.edu.gt;

import java.util.ArrayList;
public class Tokenizer {

    /**
     * Convierte un comando dado en una cadena de caracteres a un ArrayList de caracteres.
     *
     * @param command Comando en forma de cadena de caracteres.
     * @return ArrayList de caracteres que representa el comando.
     */
    public ArrayList<Character> stringToChar(String command) {
        ArrayList<Character> characters = new ArrayList<Character>();
        for (int i = 0; i < command.length(); i++) {
            characters.add(command.charAt(i));
        }
        return characters;
    }

    /**
     * Crea tokens a partir de un comando dado.
     *
     * @param command Comando en forma de cadena de caracteres.
     * @return ArrayList de tokens que representa el comando.
     */
    public ArrayList<String> Tokens(String command) {
        String temp = "";
        ArrayList<String> commands = new ArrayList<String>();
        ArrayList<Character> characters = new ArrayList<Character>();
        characters.addAll(stringToChar(command));
        for (Character current_char : characters) {
            if (current_char == '(') ;
            else {
                if (current_char == ')') {
                    if (!temp.equals("")) {
                        commands.add(temp);
                        temp = "";
                    }
                } else {
                    if (current_char != ' ')
                        temp += current_char + "";
                    else {
                        if (!temp.equals("")) {
                            commands.add(temp);
                            temp = "";
                        }
                    }
                }
            }
        }
        return commands;
    }

    /**
     * Devuelve un número según el tipo de comando que se debe ejecutar.
     *
     * @param command Comando en forma de lista de tokens.
     * @return Entero que representa el tipo de comando.
     *         - 1 para 'setq'
     *         - 2 para operaciones aritméticas ('+', '-', '*', '/')
     *         - 3 para comandos de impresión ('print', "'", 'quote')
     *         - 4 para la operación '>'
     *         - 5 para la operación '<'
     *         - 6 para la operación 'equals' o '='
     *         - 7 para el comando 'Atom'
     *         - 8 para el comando 'List'
     *         - 9 para el comando 'Cond'
     *         - 10 para el comando 'defun'
     *         - -1 si no se encuentra la expresión
     */
    public int getCommandType(ArrayList<String> command) {
        if (command.get(0).contains("setq"))
            return 1;
        else if (command.get(0).contains("+") || command.get(0).contains("-") || command.get(0).contains("*") || command.get(0).contains("/"))
            return 2;
        else if (command.get(0).contains("'") || command.get(0).contains("quote") || command.get(0).contains("print"))
            return 3;
        else if (command.get(0).contains(">"))
            return 4;
        else if (command.get(0).contains("<"))
            return 5;
        else if (command.get(0).contains("equals") || command.get(0).contains("="))
            return 6;
        else if (command.get(0).contains("Atom"))
            return 7;
        else if (command.get(0).contains("List"))
            return 8;
        else if (command.get(0).contains("Cond"))
            return 9;
        else if (command.get(0).contains("defun"))
            return 10;
        else
            return -1; // Si no encuentra la expresión
    }
}