public class Aplicacion {

    public static void main(String[] args) {
        String expr = "a*(b+a)/c";
        //String expr = "((a*(b+c))/(d-e))-(a+b)*c";

        System.out.println("Expresion: " + expr);

        Expresion exp = new Expresion(expr);


        Fichero dat = new Fichero("datos.txt");
        double[] valor;
        Expresion postf = new Expresion(exp.notacionPostfija());


        if(exp.validarEstructura() && exp.comprobarParentesis()){
            System.out.println("Notacion postfija: " + exp.notacionPostfija());
            while((valor = dat.datosLinea()) != null){
                for (double v : valor) {
                    System.out.printf("%.1f  ", v);
                }
                System.out.printf("Resultado: %.2f\n", postf.calcularResultado(valor));
            }
        }else if(!exp.validarEstructura()){
            System.out.println("Estructura incorrecta");
        }else if(!exp.comprobarParentesis()){
            System.out.println("Parentesis mal emparejados");
        }

    }
}
