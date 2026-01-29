package tests.basic;

/*
>> No modificar <<
Estos tests comprueban la resolución correcta de los ejercicios propuestos para los ejemplos del enunciado.
 */

import app.Cliente;
import app.GradBar;
import app.Pruebas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ejercicio2 {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
    }

    @Test
    public void testEjercicio2() {
        // Crear clientes
        Cliente juan = new Cliente("Juan", 0, 20, "Cerveza");
        Cliente jose = new Cliente("José", 1, 17, "Agua");
        Cliente eva = new Cliente("Eva", 2, 18, "Refresco de cola");
        Cliente alicia = new Cliente("Alicia", 3, 24, "Refresco de naranja");
        Cliente ernesto = new Cliente("Ernesto", 4, 17, "Zumo de naranja");
        Cliente guillermo = new Cliente("Guillermo", 5, 21, "Refresco de limón");
        Cliente alberto = new Cliente("Alberto", 6, 22, "Refresco de cola");
        Cliente lucas = new Cliente("Lucas", 7, 19, "Cerveza");
        Cliente clara = new Cliente("Clara", 8, 16, "Agua");
        Cliente rosa = new Cliente("Rosa", 9, 18, "Cerveza sin alcohol");

        Cliente[] clientes = new Cliente[]{juan, jose, eva, alicia, ernesto, guillermo, alberto, lucas, clara, rosa};
        GradBar red = new GradBar(clientes);

        // Conexiones
        red.conectarClientes(juan, jose);
        red.conectarClientes(juan, ernesto);
        red.conectarClientes(juan, guillermo);
        red.conectarClientes(jose, ernesto);
        red.conectarClientes(ernesto, guillermo);
        red.conectarClientes(eva, lucas);
        red.conectarClientes(eva, rosa);
        red.conectarClientes(lucas, rosa);
        red.conectarClientes(alberto, clara);

        // Contar grupos
        int grupos = red.contarGrupos();
        assertEquals("Debe haber 4 grupos", 4, grupos);

        // Amigos

        int amigosAlicia = red.mostrarAmigos(alicia);
        assertEquals("El grupo de Alicia debe tener 0 personas (además de ella)", 0, amigosAlicia);

        int amigosGuillermo = red.mostrarAmigos(ernesto);
        assertEquals("El grupo de Ernesto debe tener 3 amigos (además de él)", 3, amigosGuillermo);

        // Mayor grupo
        ArrayList<Cliente> obtenido = red.mayorGrupo();
        ArrayList<Cliente> esperado = new ArrayList<Cliente>();
        esperado.add(juan);
        esperado.add(jose);
        esperado.add(ernesto);
        esperado.add(guillermo);
        assertEquals("El grupo más grande debe tener 4 miembros", 4, obtenido.size());
        for (Cliente c : esperado) {
            assertTrue("Falta " + c + " en el grupo más grande", obtenido.contains(c));
        }

        // Bebida del grupo más grande
        String bebida = red.bebidaGrupo(jose);
        assertEquals("La bebida más repetida del grupo de Jose es la cerveza", "Cerveza", bebida);
        bebida = red.bebidaGrupo(clara);
        assertEquals("La bebida más repetida del grupo de clara es el refresco de cola", "Refresco de cola", bebida);
    }
}

