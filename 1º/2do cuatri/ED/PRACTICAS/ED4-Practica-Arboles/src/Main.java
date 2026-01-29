import mazmorra.Arbol;
import mazmorra.DiccionarioABB;

public class Main {
    public static void main(String[] args) {
        // 1: Construcción de la mazmorra
        Arbol arbol = new Arbol(Utilidades.leerMazmorra("mazmorra1.txt"));

        // 2 y 3: Visualizacion de rutas
        System.out.println("2,3- ");

        arbol.preOrdenNivel();
        System.out.println(arbol.rutaMejorTesoro());

        System.out.println(arbol.rutaMasFacil()); // que termine en tesoro

        System.out.println();

        // 4: Creacion de diccionarios
        System.out.println("4- ");

        DiccionarioABB diccionarioMonstruos = arbol.getMonstruos();
        DiccionarioABB diccionarioTesoros = arbol.getTesoros();

        System.out.println(diccionarioMonstruos);
        System.out.println(diccionarioTesoros);

        // 5: Visualizacion de DiccionarioABB en 2D
        System.out.println("5- ");

        diccionarioMonstruos.mostrar2D();

        System.out.println();

        diccionarioTesoros.mostrar2D();

        // 6: Visualizacion del metodo partir
        System.out.println("6- ");
            // pruebe a partir el diccionario de monstruos según la clave 5
            DiccionarioABB[] dir = diccionarioMonstruos.partir(5);

            dir[0].mostrar2D();

            System.out.println();

            dir[1].mostrar2D();

      /*
2 3 7
4 5 3
-1 12 20
6 -1 2
-1 -1 15
7 8 5
-1 -1 25
9 10 4
-1 -1 30
11 -1 6
-1 -1 40
-1 -1 22


2 3 -1
4 5 10
-1 6 -1
-1 -1 50
-1 8 15
9 10 20
-1 -1 -1
11 12 -1
-1 -1 30
-1 13 25
-1 -1 60
-1 14 5
-1 -1 40
-1 -1 70


-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
-1 -1 -1
         */

    }
}
