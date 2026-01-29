package app;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Pruebas {
    public static void main(String[] args) {
        System.out.println("Practica 5 grafos");
        //ejercicio_1();
        ejercicio_2();
    }

    // Completar ejercicio_1
    public static void ejercicio_1(){
        System.out.println("--------- Ejercicio 1 ---------");

        // Clara y Guillermo
        Cliente cliente8 = new Cliente("Clara", 8,16, "Agua");
        Cliente cliente5 = new Cliente("Guillermo", 5,21, "Refresco de Limón");

        Cliente[] clientes = {cliente8, cliente5};

        GradBar RinconTapa = new GradBar(clientes);

        RinconTapa.conectarClientes(cliente8, cliente5);

        RinconTapa.mostrar();

        RinconTapa.mostrarGrafico();

    }

    // Completar ejercicio_2
    public static void ejercicio_2() {

        System.out.println("--------- Ejercicio 2 ---------");

        Cliente[] clientesBar = new Cliente[10];

        clientesBar[0] = new Cliente("Juan", 0,20, "Cerveza");
        clientesBar[1] = new Cliente("José", 1,17, "Agua");
        clientesBar[2] = new Cliente("Eva", 2,18, "Refresco de cola");
        clientesBar[3] = new Cliente("Alicia", 3,24, "Refresco de naranja");
        clientesBar[4] = new Cliente("Ernesto", 4,17, "Cerveza");
        clientesBar[5] = new Cliente("Guillermo", 5,21, "Refresco de Limón");
        clientesBar[6] = new Cliente("Alberto", 6,22, "Refresco de cola");
        clientesBar[7] = new Cliente("Lucas", 7,19, "Cerveza");
        clientesBar[8] = new Cliente("Clara", 8,16, "Agua");
        clientesBar[9] = new Cliente("Rosa", 9,18, "Cerveza sin alcohol");

        GradBar RinconBarLaTapa = new GradBar(clientesBar);

        RinconBarLaTapa.conectarClientes(clientesBar[0],clientesBar[1]);
        RinconBarLaTapa.conectarClientes(clientesBar[0],clientesBar[5]);
        RinconBarLaTapa.conectarClientes(clientesBar[0],clientesBar[4]);
        RinconBarLaTapa.conectarClientes(clientesBar[1],clientesBar[4]);
        RinconBarLaTapa.conectarClientes(clientesBar[4],clientesBar[5]);

        RinconBarLaTapa.conectarClientes(clientesBar[2],clientesBar[9]);
        RinconBarLaTapa.conectarClientes(clientesBar[2],clientesBar[7]);
        RinconBarLaTapa.conectarClientes(clientesBar[7],clientesBar[9]);

        RinconBarLaTapa.conectarClientes(clientesBar[6],clientesBar[8]);


        RinconBarLaTapa.mostrar();
        RinconBarLaTapa.mostrarGrafico();

        System.out.println("--------------------------------");

        System.out.println( clientesBar[4].getNombre() + " tiene " + RinconBarLaTapa.mostrarAmigos(clientesBar[4]) + " amigos");

        System.out.println("--------------------------------");

        System.out.println( clientesBar[3].getNombre() + " tiene " + RinconBarLaTapa.mostrarAmigos(clientesBar[3]) + " amigos");


        System.out.println("--------------------------------");

        System.out.println("En el Rincón de la Tapa tenemos " + RinconBarLaTapa.contarGrupos() + " grupos de amigos" );


        ArrayList<Cliente> grupoMayor = RinconBarLaTapa.mayorGrupo();

        System.out.println("--------------------------------");

        System.out.println("El grupo más grande es de " + grupoMayor.size() + " personas, que son:");
        Iterator<Cliente> it = grupoMayor.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("--------------------------------");

        System.out.println("Bebida del grupo de " + clientesBar[1].getNombre() + ": " + RinconBarLaTapa.bebidaGrupo(clientesBar[1]));
        System.out.println("Bebida del grupo de " + clientesBar[8].getNombre() + ": " + RinconBarLaTapa.bebidaGrupo(clientesBar[8]));



    }

}
