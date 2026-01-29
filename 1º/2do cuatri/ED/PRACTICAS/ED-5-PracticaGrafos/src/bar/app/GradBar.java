/*
TODO pon aquí tu nombre y grupo
Nombre y apellidos: Pablo Del Pozuelo Escalona
Grupo: CITIM-11
*/

package app;

import grafo.GrafoMA;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;


public class GradBar {
    private GrafoMA red;
    private Cliente[] clientes;
    private int numClientes;

    //Completar Constructor
    public GradBar(Cliente[] clientes) {
        this.numClientes = clientes.length;
        this.clientes = clientes;
        this.red = new GrafoMA(numClientes, false);
    }

    //Completar getNumClientes
    public int getNumClientes() {
        return numClientes;
    }

    // Completar getIndice
    public int getIndice(Cliente cliente) {
        int res = -1;

        for (int i = 0; i < numClientes && (res == -1); i++) {
            if (cliente.equals(clientes[i])) res = i;
        }

        return res;
    }

    // Completar conectarClientes
    public boolean conectarClientes(Cliente c1, Cliente c2) {
        boolean res = false;

        if (c1 != null && c2 != null) {
            int client1 = getIndice(c1);
            int client2 = getIndice(c2);

            if (client1 != -1 && client2 != -1) {
                res = true;
                red.insertarArista(client1, client2);
            }
        }

        return res;
    }

    // Completar mostrar
    public void mostrar() {
        for (int i = 0; i < numClientes; i++) {
            System.out.println(clientes[i]);
        }
        red.mostrar();
    }


    // Completar mostrarAmigos
    public int mostrarAmigos(Cliente cliente) {
        int res = 0;

        for (int i = 0; i < numClientes; i++) {
            if (red.existeArista(getIndice(cliente), i)) { // sus amigos son con los que comparte arista
                System.out.println(clientes[i]);
                res++;
            }
        }

        return res;
    }


    // Completar contarGrupos
    public int contarGrupos() {
        boolean[] visitados = new boolean[numClientes];
        int numGrupos = 0;

        for (int i = 0; i < numClientes; i++) {
            if (!visitados[i]) {
                boolean[] grupo = red.amplitudDesdeVertice(i);  // o profundidadDesdeVertice

                for (int j = 0; j < numClientes; j++) { // marca visitados
                    if (grupo[j]) {
                        visitados[j] = true;
                    }
                }

                System.out.println(clientes[i]);    // representante

                numGrupos++;
            }
        }

        return numGrupos;
    }

    // Completar mayorGrupo
    public ArrayList<Cliente> mayorGrupo() {
        boolean[] visitados = new boolean[numClientes];
        int max = 0;
        ArrayList<Cliente> grupoMasGrande = new ArrayList<>();

        for (int i = 0; i < numClientes; i++) {
            if (!visitados[i]) {
                boolean[] grupoActual = red.amplitudDesdeVertice(i); // o profundidadDesdeVertice(i)
                ArrayList<Cliente> miembrosGrupo = new ArrayList<>();

                for (int j = 0; j < numClientes; j++) { // marco los vistos y guardo los clientes del grupo
                    if (grupoActual[j]) {
                        visitados[j] = true;
                        miembrosGrupo.add(clientes[j]);
                    }
                }

                if (miembrosGrupo.size() > max) { // comparamos el grupo con lo anterior registrado
                    max = miembrosGrupo.size();
                    grupoMasGrande = miembrosGrupo;
                }
            }
        }

        return grupoMasGrande;
    }

    // Completar bebidaGrupo
    public String bebidaGrupo(Cliente cliente) {
        int indice = getIndice(cliente);
        String bebidaPredominante = "";
        if (indice != -1) {  // cliente existe

            boolean[] grupo = red.amplitudDesdeVertice(indice);
            TreeMap<String, Integer> contador = new TreeMap<>();

            for (int i = 0; i < numClientes; i++) { // Contar bebidas favoritas

                if (grupo[i]) {
                    String bebida = clientes[i].getBebida_favorita();

                    if (contador.containsKey(bebida)) {
                        contador.put(bebida, contador.get(bebida) + 1);
                    } else {
                        contador.put(bebida, 1);
                    }
                }
            }

            int max = 0;
            Iterator<String> it = contador.keySet().iterator(); // Bebida favorita
            while (it.hasNext()) {
                String bebida = it.next();
                int cantidad = contador.get(bebida);
                if (cantidad > max) {
                    max = cantidad;
                    bebidaPredominante = bebida;
                }
            }
        }

        return bebidaPredominante;
    }


    public void mostrarGrafico() {
        JFrame frame = new JFrame("Grafo de Clientes");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int n = getNumClientes();
                if (n == 0) return;

                // Usamos un layout circular para posicionar los vértices
                int radius = Math.min(getWidth(), getHeight()) / 2 - 50;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                Point[] puntos = new Point[n];

                // Dibujar los vértices (círculos) y el nombre del cliente
                for (int i = 0; i < n; i++) {
                    double angle = 2 * Math.PI * i / n;
                    int x = centerX + (int) (radius * Math.cos(angle));
                    int y = centerY + (int) (radius * Math.sin(angle));
                    puntos[i] = new Point(x, y);

                    // Dibujar el vértice
                    g.setColor(Color.WHITE);
                    g.fillOval(x - 20, y - 20, 40, 40);
                    g.setColor(Color.BLACK);
                    g.drawOval(x - 20, y - 20, 40, 40);

                    // Dibujar el nombre del cliente
                    String nombre = clientes[i].getNombre(); // Asegúrate de tener este método en Cliente
                    FontMetrics fm = g.getFontMetrics();
                    int textWidth = fm.stringWidth(nombre);
                    g.drawString(nombre, x - textWidth / 2, y - 25);
                }

                // Dibujar las aristas (flechas si es dirigido)
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (red.existeArista(i, j)) {
                            Point p1 = puntos[i];
                            Point p2 = puntos[j];

                            // Dibuja una línea entre p1 y p2
                            g.drawLine(p1.x, p1.y, p2.x, p2.y);

                            // Si el grafo es dirigido, dibuja una flecha en el extremo
                            if (red.getDirigido()) {
                                dibujarFlecha(g, p1, p2);
                            }
                        }
                    }
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    // Método auxiliar para dibujar una flecha en la línea
    private void dibujarFlecha(Graphics g1, Point pInicio, Point pFin) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = pFin.x - pInicio.x;
        double dy = pFin.y - pInicio.y;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        // Traslada el origen al final de la línea
        AffineTransform at = AffineTransform.getTranslateInstance(pInicio.x, pInicio.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Dibuja la línea (desde 0,0 hasta len,0)
        g.drawLine(0, 0, len, 0);

        // Dibuja la cabeza de la flecha en (len,0)
        int arrowSize = 10;
        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(len, 0);
        arrowHead.addPoint(len - arrowSize, -arrowSize / 2);
        arrowHead.addPoint(len - arrowSize, arrowSize / 2);
        g.fill(arrowHead);
        g.dispose();
    }

}

