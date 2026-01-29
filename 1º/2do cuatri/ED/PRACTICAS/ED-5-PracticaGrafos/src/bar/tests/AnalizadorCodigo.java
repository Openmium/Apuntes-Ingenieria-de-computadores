package tests;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AnalizadorCodigo {

    // Lee el contenido completo del fichero fuente
    public static String leerFichero(String ruta) throws Exception {
        return new String(Files.readAllBytes(Paths.get(ruta)), StandardCharsets.UTF_8);
    }

    // Genera las variantes del nombre (para admitir las variaciones de ñ)
    public static String[] generarVariantes(String baseName) {
        return new String[] {
                baseName,
                baseName.replace("ñ", "n"),
                baseName.replace("ñ", "ny"),
                baseName.replace("ñ", "nh"),
                baseName.replace("ñ", "nn")
        };
    }

    // Extrae el cuerpo del método de la clase, usando el nombre (o cualquiera de sus variantes)
    public static String obtenerCodigoMetodoAlternativo(String rutaFichero, String baseName) throws Exception {
        String contenido = leerFichero(rutaFichero);
        String cuerpo = "";
        for (String nombreMetodo : generarVariantes(baseName)) {
            // Buscamos "... <nombreMetodo>(...){"
            Pattern p = Pattern.compile("\\s+" + Pattern.quote(nombreMetodo) + "\\s*\\([^)]*\\)\\s*\\{", Pattern.DOTALL);
            Matcher m = p.matcher(contenido);
            if (m.find()) {
                int inicio = m.end() - 1; // posicion de la llave '{'
                int fin = encontrarCierre(contenido, inicio);
                if (fin != -1) {
                    cuerpo = contenido.substring(inicio + 1, fin).trim();
                    break;
                }
            }
        }
        return cuerpo;
    }

    // Encuentra la posicion de la llave de cierre correspondiente al bloque que inicia en posInicio.
    private static int encontrarCierre(String contenido, int posInicio) {
        int contador = 0;
        for (int i = posInicio; i < contenido.length(); i++) {
            char c = contenido.charAt(i);
            if (c == '{') {
                contador++;
            } else if (c == '}') {
                contador--;
                if (contador == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Comprueba que en el codigo se usa iterador (buscando "getIterador(")
    public static boolean usaIterador(String codigo) {
        //System.out.println(codigo);
        return codigo.contains("getIterador(") || codigo.contains("Iterator") ;
    }

    // Comprueba que en el codigo no se llama a getElemento
    public static boolean noUsaGetElemento(String codigo) {
        return !codigo.contains("getElemento(");
    }

    // Comprueba que no hay "return" dentro de bucles (for o while)
    public static boolean noReturnEnBucles(String codigo) {
        // Usamos un patron no codicioso para extraer cada bloque for o while
        Pattern loopPattern = Pattern.compile("(for|while)\\s*\\(.*?\\)\\s*\\{(.*?)\\}", Pattern.DOTALL);
        Matcher loopMatcher = loopPattern.matcher(codigo);
        while(loopMatcher.find()){
            String bloque = loopMatcher.group(2);
            if(bloque.contains("return")){
                return false;
            }
        }
        return true;
    }



    // Comprueba que en el código no haya mas de un "for" y mas de un "while"
    public static boolean comprobarUnicidadBucles(String codigo) {
        int countFor = 0, countWhile = 0;
        Pattern forPattern = Pattern.compile("\\bfor\\b");
        Matcher mFor = forPattern.matcher(codigo);
        while (mFor.find()) {
            countFor++;
        }
        Pattern whilePattern = Pattern.compile("\\bwhile\\b");
        Matcher mWhile = whilePattern.matcher(codigo);
        while (mWhile.find()) {
            countWhile++;
        }
        return countFor <= 1 && countWhile <= 1;
    }

    public static boolean noWhileConContador(String codigo) {
        // Busca en bloques while que contengan getNumElementos o this.numElementos y luego "+1"
        Pattern p = Pattern.compile("while\\s*\\(.*?(getNumElementos|numElementos|size).*?\\+\\s*1", Pattern.DOTALL);
        Matcher m = p.matcher(codigo);
        return !m.find();
    }
    public static boolean forCondicionAvanceCorrecto(String codigo) {
        // Busca un for con la estructura: for(...; condicion; avance)
        // Intenta evitar que usen for + hasNext().. .no es perfecto para comprobar que el for es de verdad pero menos da una piedra
        Pattern p = Pattern.compile("\\bfor\\s*\\(\\s*([^;]+);\\s*([^;]+);\\s*([^\\)]+)\\)");
        Matcher m = p.matcher(codigo);
        while (m.find()) {
            String condicion = m.group(2);
            String avance = m.group(3);
            // Verificar que la condicion usa <= o >=. Si no, falla.
            boolean condicionValida = condicion.contains("<=") || condicion.contains(">=");
            // Verificar que el avance es un ++ o un --. Se admite que aparezca en forma postfix o prefix.
            boolean avanceValido = avance.contains("++") || avance.contains("--");
            if (!condicionValida || !avanceValido) {
                return false;
            }
        }
        return true;
    }


    // Agrupa todas las comprobaciones para el metodo (por ejemplo, anyadirProducto)
    public static boolean comprobarMetodo(String rutaFichero, String baseName) throws Exception {
        String codigo = obtenerCodigoMetodoAlternativo(rutaFichero, baseName);
        boolean condicion1 = usaIterador(codigo);
        boolean condicion2 = noUsaGetElemento(codigo);
        boolean condicion3 = noReturnEnBucles(codigo);
        boolean condicion4 = comprobarUnicidadBucles(codigo);
        boolean condicion5 = noWhileConContador(codigo);
        boolean condicion6 = forCondicionAvanceCorrecto(codigo);
        System.out.println("Verificacion del metodo " + baseName + ":");
        System.out.println(codigo);
        System.out.println(" - Usa iterador: " + condicion1);
        System.out.println(" - No usa getElemento [ No doble recorrido ]: " + condicion2);
        System.out.println(" - No hay return dentro de bucles: " + condicion3);
        System.out.println(" - Unicidad bucles for/while [ No doble recorrido ]: " + condicion4);
        System.out.println(" - No uso de while como for: " + condicion5);
        System.out.println(" - For de verdad: " + condicion6);

        return condicion1 && condicion2 && condicion3 && condicion4 && condicion5 && condicion6;
    }

    // Ejemplo de uso
    public static void main(String[] args) throws Exception {
        // Por ejemplo, suponemos que el fichero fuente es "src/Factura.java" y queremos comprobar el metodo anyadirProducto
        String rutaFichero = "src/Factura.java";
        boolean ok = comprobarMetodo(rutaFichero, "importeTotal");
        if (! ok) {
            System.out.println("Resultado de la comprobacion: " + ok);
        }
    }
}