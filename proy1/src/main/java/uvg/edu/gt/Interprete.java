package uvg.edu.gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interprete {

    private HashMap<String, String> variables = new HashMap<String, String>();
    private PrefixCalc PrefixCalculator = new PrefixCalc();
    private Operator Operaciones = new Operator();
    private ArrayList<String> instructions = new ArrayList<String>(Arrays.asList("setq", "print", "+", "-", "*", "/",
            "'", "quote", ">", "<", "equals", "=", "Atom", "List", "Cond", "defun"));
    private ArrayList<String> FunctionsNames = new ArrayList<String>();
    private Tokenizer tokenizer = new Tokenizer();
    private HashMap<String, ArrayList<String>> functions = new HashMap<String, ArrayList<String>>();
    private HashMap<String, LinkedHashMap<String, String>> parameters = new HashMap<String, LinkedHashMap<String, String>>();
    private boolean end = false;
    private int created_instructions = 1;

    /**
     * Realiza operaciones basadas en el código de entrada.
     * @param commands Lista de comandos a interpretar.
     * @param option Opción de operación a realizar.
     * @return Resultado de la operación.
     */
    public String operate(ArrayList<String> commands, int option){
        if (!end){
            String expresion = "";
            for (String s: commands)
                expresion += s + " ";
            if(findVariables(expresion) != null){
                expresion = findVariables(expresion);
                if (option == 1)
                    return setQ(commands);
                else if (option == 2)
                    return PrefixCalculator.calcularPrefijo(expresion) + "";
                else if (option == 3)
                    return quote(expresion);
                else if (option == 4)
                    return Operaciones.isBigger(expresion);
                else if (option == 5)
                    return Operaciones.isSmaller(expresion);
                else if (option == 6)
                    return Operaciones.equals(expresion);
                else if (option == 7)
                    return Operaciones.isAtom(expresion);
                else if (option == 8)
                    return Operaciones.isList(expresion);
                else if (option == 9)
                    return cond(commands);
                else if (option == 10){
                    newFunction(convertToArrayList(expresion));
                    return "";
                }
                else{
                    if (isHere(instructions, commands.get(0)))
                        return useFunction(convertToArrayList(expresion));
                    else
                        return "instrucción inválida";
                }
            }
            else
                return "La variable no ha sido creada";
        }
        else{
            return "........................";
        }
    }

    /**
     * Define una nueva función en el intérprete.
     * @param command Lista de comandos que definen la función.
     */
    public void newFunction(ArrayList<String> command){
        String name = command.get(1);
        this.instructions.add(name); 
        FunctionsNames.add(name);
        ArrayList<String> instrucciones = new ArrayList<String>();
        LinkedHashMap<String, String> parametersFunction = new LinkedHashMap<String, String>();
        String[] parametersSplited = command.get(2).trim().split(","); 
        for(String parameter: parametersSplited)
            parametersFunction.put(parameter, "");
        this.parameters.put(name, parametersFunction);

        for (int i = 3; i < command.size(); i++){
            String expresion = "";
            if (isHere(getInstructions(), command.get(i))){
                expresion = command.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < command.size() && flag; j++){
                    if (!isHere(getInstructions(),command.get(j))){
                        expresion += command.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                i += cont;
            }
            instrucciones.add(expresion);
        }
        functions.put(name, instrucciones); 
    }
    
    /**
     * Utiliza una función definida previamente en el intérprete.
     * @param commands Lista de comandos para la función.
     * @return Resultado de la función.
     */
    public String useFunction(ArrayList<String> commands){
        String name = commands.get(0);
        String result = "";
        ArrayList<String> newParameters = new ArrayList<String>();
        String parameters = "";
        for (int i = 1; i <commands.size(); i++){
            if (isHere(getInstructions(), commands.get(i))){
                String expression = commands.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < commands.size() && flag; j++){
                    if (!isHere(getInstructions(),commands.get(j))){
                        expression += commands.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                i += cont;
                parameters += operate(convertToArrayList(expression), tokenizer.getCommandType(convertToArrayList(expression)));
            }
            else
                parameters += commands.get(i) + " ";

            newParameters.add(parameters);
        }
        
        String[] parametersSplited = parameters.split(" ");
        ArrayList<String> instructions = functions.get(name);
        LinkedHashMap<String, String> parametersFunction = this.parameters.get(name);
        String instrucciones = "";

        
        for (int i = 0; i < instructions.size(); i++){
            instrucciones += instructions.get(i).trim() + " ";
        }

        if(parametersSplited.length == parametersFunction.size()){
            int i = 0;
            for(String parameter: parametersFunction.keySet()){
                parametersFunction.put(parameter, parametersSplited[i]);
                instrucciones = instrucciones.replace(parameter, parametersFunction.get(parameter));
                i++;
            }
        }
        
        ArrayList<String> evaExpression = convertToArrayList(instrucciones);
        ArrayList<String> newInstructions = new ArrayList<String>();
        for (int i = 0; i < evaExpression.size(); i++){
            String expresion = "";
            if (isHere(getInstructions(), evaExpression.get(i))){
                if (evaExpression.get(i).equals("Cond")){
                    String condition = "";
                    for (int j = i; j < evaExpression.size(); j++){
                        condition += evaExpression.get(j) + " ";
                    }
                    expresion += operate(convertToArrayList(condition), tokenizer.getCommandType(convertToArrayList(condition)));
                    i = evaExpression.size();
                    end = true;
                } else{
                    expresion = evaExpression.get(i) + " ";
                    boolean flag = true;
                    int cont = 0;
                    for (int j = i+1; j < evaExpression.size() && flag; j++){
                        if (!isHere(getInstructions(),evaExpression.get(j))){
                            expresion += evaExpression.get(j) + " ";
                            cont++;
                        }
                        else
                            flag = false;
                    }
                    i += cont;
                }
            }
            newInstructions.add(expresion);
        }
        for (String ins: newInstructions)
            result += operate(convertToArrayList(ins), tokenizer.getCommandType(convertToArrayList(ins))) + "\n";
        if (created_instructions != 1){
            result = created_instructions + "\n";
        }
        return result;
    }

    /**
     * Define una nueva variable en el intérprete.
     * @param command Lista de comandos para definir la variable.
     * @return Nombre y valor de la variable definida.
     */
    public String newVariable(ArrayList<String> command){
        String name = command.get(1);
        String value = "";
        for (int i = 2; i < command.size(); i++){
            String operation = "";
            if (isHere(instructions, command.get(i))){
                for (int j = i; j < command.size(); j++){
                    operation += command.get(j) + " ";
                }
                value = operate(convertToArrayList(operation), tokenizer.getCommandType(convertToArrayList(operation)));
                i = command.size();
            }
            else
                value = command.get(i);
        }
        variables.put(name, value);
        return name +": " + value;
    }

    /**
     * Retorna una expresión entre comillas de una función.
     * @param func Función a citar.
     * @return Función citada.
     */
    public String quote(String func){
        String value = "";
        String[] tokens = func.split("");
        int control=0;
        for(int i = 0; i < tokens.length-1; i++){
            if(tokens[i].equals("quote")||tokens[i].equals("QUOTE")||tokens[i].equals("'")){
                i++;
                control = i;
            }
        }
        for(int n = control; n< tokens.length-1;n++ ){
            if(n == tokens.length-2){
                value = value + tokens[n];
            }
            else {
                value = value + tokens[n]+" ";
            }
        }
        return value;
    }

    /**
     * Verifica la existencia de una variable en el intérprete.
     * @param name Nombre de la variable.
     * @return Valor de la variable si existe, de lo contrario null.
     */
    private String verifyVariable(String name){
        String variable = null;
        if(variables.containsKey(name))
            variable = variables.get(name);
        return variable;
    }

    /**
     * Retorna la lista de instrucciones disponibles en el intérprete.
     * @return Lista de instrucciones.
     */
    public ArrayList<String> getInstructions(){
        return this.instructions;
    }

    /**
     * Implementa la funcionalidad del condicional Lisp.
     * @param lisp Lista de comandos Lisp.
     * @return Resultado del condicional.
     */
    public String cond(ArrayList<String> lisp){
        String conditional = "";
        String condition = lisp.get(1) + " ";
        int condCount = 0;
        String positive = "";
        String negative = "";
        boolean positivo = false;
        boolean optimizar = false;

        for (int i = 2; i < lisp.size(); i++) {

            
            if (condCount == 2)
                if (operate(convertToArrayList(condition), tokenizer.getCommandType(convertToArrayList(condition))).equals("True"))
                    optimizar = true;
            if (!isHere(getInstructions(), lisp.get(i)) && condCount != 2){
                condition += lisp.get(i) + " ";
                condCount++;
            }
            else if(condCount != 2){
                String expression = lisp.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < lisp.size() && flag; j++){
                    if (!isHere(getInstructions(),lisp.get(j))){
                        expression += lisp.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                condition += operate(convertToArrayList(expression), tokenizer.getCommandType(convertToArrayList(expression)));
                condCount++;
                i += cont;
            }

            
            else if (condCount == 2 && !positivo){
                if (isHere(getInstructions(),lisp.get(i)))
                    positive = lisp.get(i) + " ";
                boolean flag = true;
                int cont = 0;
                for (int j = i+1; j < lisp.size() && flag; j++){
                    if (!isHere(getInstructions(),lisp.get(j))){
                        positive += lisp.get(j) + " ";
                        cont++;
                    }
                    else
                        flag = false;
                }
                positivo = true;
                i += cont;
            }

            
            else if (condCount == 2 && positivo && !optimizar){
                if (isHere(getInstructions(),lisp.get(i)))
                    negative = lisp.get(i) + " ";
                String function = "";
                for (int j = i+1; j < lisp.size(); j++){
                    if (isHere(instructions, lisp.get(j))){
                        for (int k = j; k < lisp.size(); k++){
                            function += lisp.get(k) + " ";
                        }
                        created_instructions *= Integer.parseInt(lisp.get(j+2));
                        negative += operate(convertToArrayList(function), tokenizer.getCommandType(convertToArrayList(function)));
                    }
                    else{
                        negative += lisp.get(j) + " ";
                    }
                }
                break;
            }
        }
        if (operate(convertToArrayList(condition), tokenizer.getCommandType(convertToArrayList(condition))).equals("True"))
            conditional = operate(convertToArrayList(positive), tokenizer.getCommandType(convertToArrayList(positive))); //Condicion positiva
        else
            conditional = operate(convertToArrayList(negative), tokenizer.getCommandType(convertToArrayList(negative))); //Condicion negativa
        return conditional;
    }

    /**
     * Verifica si una instrucción está presente en una lista de instrucciones.
     * @param instructions Lista de instrucciones.
     * @param default_instructions Instrucción a verificar.
     * @return true si la instrucción está presente, de lo contrario false.
     */    
    private boolean isHere(ArrayList<String> instructions, String default_instructions){
        boolean flag = false;
        for (int i = 0; i < instructions.size() && flag == false; i++)
            if (default_instructions.contains(instructions.get(i)))
                flag = true;
        return flag;
    }

    /**
     * Convierte una cadena de comandos en una lista de comandos.
     * @param command Cadena de comandos.
     * @return Lista de comandos.
     */    
    private ArrayList<String> convertToArrayList(String command){
        String[] splitedExpression = command.split(" ");
        ArrayList<String> evaExpression = new ArrayList<String>();
        for (int j = 0; j < splitedExpression.length; j++)
            evaExpression.add(splitedExpression[j]);
        return evaExpression;
    }

    /**
     * Busca variables en una cadena de comandos y las reemplaza por sus valores si existen.
     * @param command Cadena de comandos.
     * @return Cadena de comandos con variables reemplazadas por sus valores.
     */
    private String findVariables(String command){
        String newExpression = "";
        String variable = "";
        String[] parts = command.split(" ");
        for (int i = 0; i < parts.length; i++){
            
            Pattern pattern = Pattern.compile("([a-z]+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(parts[i]);

            if (matcher.find()){
                variable = matcher.group().trim();
                if(!variable.matches("[+-]?\\d*(\\.\\d+)?"))
                    if(verifyVariable(variable) != null)
                        parts[i] = verifyVariable(variable).toString();

            }
        }
        for (int i = 0; i < parts.length; i++)
            newExpression += parts[i] + " ";

        return newExpression;
    }

    /**
     * Define una nueva variable en el intérprete utilizando el comando setq de Lisp.
     * @param lisp Lista de comandos Lisp.
     * @return Nombre y valor de la variable definida.
     */
    public String setQ(ArrayList<String> lisp){
        String name =  lisp.get(1);
        String value = "";
        for (int i = 2; i < lisp.size(); i++){
            String operation = "";
            if (isHere(instructions, lisp.get(i))){
                for (int j = i; j < lisp.size(); j++){
                    operation += lisp.get(j) + " ";
                }
                value = operate(convertToArrayList(operation), tokenizer.getCommandType(convertToArrayList(operation)));
                i = lisp.size();
            }
            else
                value = lisp.get(i);
        }
        variables.put(name, value);
        return name +": " + value;
        
    }
}