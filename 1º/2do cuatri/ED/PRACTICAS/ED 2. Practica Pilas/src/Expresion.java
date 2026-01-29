import java.util.Stack;

public class Expresion {
    String expresion;

    public Expresion(String expresion) {
        this.expresion = expresion;
    }

    private static boolean esOperando(char car) {
        return car >= 'a' && car <= 'e';
    }

    private static boolean esOperador(char car) {
        return car == '+' || car == '-' || car == '/' || car == '*';
    }

    private static int precedencia(char op) {
        int resultado = 0;
        if (op == '+' || op == '-') {
            resultado = 1;
        } else if (op == '*' || op == '/') {

            resultado = 2;
        }
        return resultado;
    }

    public boolean validarEstructura() {
        boolean correcto;
        if (expresion.isEmpty() ||
                (expresion.charAt(0) != '(' && !esOperando(expresion.charAt(0))) ||
                (expresion.charAt(expresion.length() - 1) != ')' &&
                        !esOperando(expresion.charAt(expresion.length() - 1)))) {
            correcto = false;
        } else {
            correcto = true;
            int i = 0;
            while (i < expresion.length() - 1 && correcto) {
                if (esOperando(expresion.charAt(i)) || expresion.charAt(i) == ')') {
                    if (!esOperador(expresion.charAt(i + 1)) && expresion.charAt(i + 1) != ')') {
                        correcto = false;
                    }
                } else if (esOperador(expresion.charAt(i)) || expresion.charAt(i) == '(') {
                    if (!esOperando(expresion.charAt(i + 1)) && expresion.charAt(i + 1) != '(') {
                        correcto = false;
                    }
                } else {  // Caracter desconocido
                    correcto = false;
                }
                i++;
            }
        }
        return correcto;
    }

    public boolean comprobarParentesis() {
        // Apartado 5.2

        boolean correct = true;
        Stack <Character> pila = new Stack<>();

        for(char a : expresion.toCharArray()){
            if(a == '(' || a == ')'){
                if (a == '(') {
                    pila.push(a);
                } else if (a == ')' && !pila.empty()) {
                    pila.pop();
                } else {
                    correct = false;
                }
            }
        }

        if(!pila.empty()){
            correct = false;
        }

        return correct;
    }

    public String notacionPostfija() {
        // Apartado 5.3

        Stack <Character> op = new Stack<>();
        String notacion = "";

        for(char n : expresion.toCharArray()){
            if(esOperando(n)){
                notacion = notacion + n;
            }else if(esOperador(n)){
                while(!op.empty() && precedencia(n) <= precedencia(op.peek())){
                    notacion = notacion + op.pop();
                }
                op.push(n);
            }else if(n == '('){
                op.push(n);
            }else if(n == ')'){
                while(!op.empty() && op.peek() != '('){
                    notacion = notacion + op.pop();
                }
                if(!op.empty() && op.peek() == '('){
                    op.pop(); // Elimina el '('
                }
            }
        }
        while(!op.empty()){
            notacion = notacion + op.pop();
        }

        return notacion;
    }

    public double calcularResultado(double[] operandos) {
        // Apartado 5.4

        Stack <Double> val = new Stack<>();
        double resul = 0;

        for(char s : expresion.toCharArray()){
            if(esOperando(s)){
                if(s == 'a'){
                    val.push(operandos[0]);
                }else if(s == 'b'){
                    val.push(operandos[1]);
                }else if(s == 'c'){
                    val.push(operandos[2]);
                }else if(s == 'd'){
                    val.push(operandos[3]);
                }else if(s == 'e'){
                    val.push(operandos[4]);
                }
            }else{ // es operador
                double op2 = val.pop();
                double op1 = val.pop();

                if(s == '+'){
                    resul = op1 + op2;
                }else if(s == '-'){
                    resul = op1 - op2;
                }else if(s == '*'){
                    resul = op1 * op2;
                }else if(s == '/'){
                    resul = op1 / op2;
                }
                val.push(resul);
            }
        }

        return val.pop();
    }

}
