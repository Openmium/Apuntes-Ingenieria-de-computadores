package mazmorra;

import java.util.ArrayDeque;
import java.util.Stack;

public class Arbol {
    private NodoArbol raiz;

    public Arbol() {
        raiz = null;
    }

    public Arbol(Sala dato) {
        raiz = new NodoArbol(dato);
    }

    public Arbol(Sala dato, Arbol izquierdo, Arbol derecho) {
        NodoArbol nodoIzq = null;
        NodoArbol nodoDer = null;
        if (izquierdo != null) {
            nodoIzq = izquierdo.raiz;
        }
        if (derecho != null) {
            nodoDer = derecho.raiz;
        }
        raiz = new NodoArbol(dato, nodoIzq, nodoDer);
    }

    /**
     * Recorrido en preorden
     */
    public void preOrden() {
        System.out.print("Preorden: ");
        this.preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDato());
            this.preOrdenRec(nodo.getIzquierdo());
            this.preOrdenRec(nodo.getDerecho());
        }
    }

    /**
     * Recorrido en preorden indicando además el nivel de cada nodo en el árbol
     */
    public void preOrdenNivel() {
        System.out.println("Preorden con niveles: ");
        preOrdenNivelRec(raiz, 1);
    }

    private void preOrdenNivelRec(NodoArbol nodo, int nivel) {
        if (nodo != null) {
            System.out.println(nodo.getDato() + " en el nivel " + nivel);
            preOrdenNivelRec(nodo.getIzquierdo(), nivel + 1);
            preOrdenNivelRec(nodo.getDerecho(), nivel + 1);
        }
    }

    /**
     * Recorrido en orden central
     */
    public void ordenCentral() {
        System.out.print("Orden Central: ");
        this.ordenCentralRec(raiz);
        System.out.println();
    }

    private void ordenCentralRec(NodoArbol nodo) {
        if (nodo != null) {
            this.ordenCentralRec(nodo.getIzquierdo());
            System.out.println(nodo.getDato());
            this.ordenCentralRec(nodo.getDerecho());
        }
    }

    /**
     * Recorrido en postorden
     */
    public void postOrden() {
        System.out.print("Postorden: ");
        this.postOrdenRec(raiz);
        System.out.println();
    }

    private void postOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            this.postOrdenRec(nodo.getIzquierdo());
            this.postOrdenRec(nodo.getDerecho());
            System.out.println(nodo.getDato());
        }
    }

    /**
     * Recorrido en amplitud con una cola de nodos del árbol
     */
    public void amplitud() {
        ArrayDeque<NodoArbol> cola = new ArrayDeque<>();
        System.out.print("Amplitud: ");
        if (raiz != null) {
            cola.add(raiz);
            while (!cola.isEmpty()) {
                NodoArbol nodo = cola.remove();
                System.out.println(nodo.getDato());
                if (nodo.getIzquierdo() != null) {
                    cola.add(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.add(nodo.getDerecho());
                }
            }
        }
        System.out.println();
    }


    /**
     * Construir un árbol binario que represente la estructura de la mazmorra a partir de una
     * matriz bidimensional que contiene información sobre las salas
     */
    public Arbol(int[][] datosSalas) {
        if (datosSalas == null || datosSalas.length == 0) {
            this.raiz = null;
        } else {
            this.raiz = crearNodoArbol(datosSalas, 0);
        }
    }

    private static NodoArbol crearNodoArbol(int[][] datosSalas, int filaActual) {
        NodoArbol nodo = null;
        // fila>= 0 y < tamaño array
        if (filaActual >= 0 && filaActual < datosSalas.length) {

            Sala sala = new Sala(filaActual + 1, datosSalas[filaActual][2]); // el idSala empieza en 1 como las lineas del fichero (+1)

            // id de los hijos de la fila actual
            int hijoIzqId = datosSalas[filaActual][0];
            int hijoDerId = datosSalas[filaActual][1];


            // Necesitamos pasar los id fila a la matriz y para ello les restamos 1
            int hijoIzq = -1; // -1 si no hay hijo
            int hijoDer = -1; // -1 si no hay hijo

            // Para el hijo izquierdo
            if (hijoIzqId != -1) { // Mantener -1 si no hay hijo
                hijoIzq = hijoIzqId - 1;
            }

            // Para el hijo derecho
            if (hijoDerId != -1) { // Mantener -1 si no hay hijo
                hijoDer = hijoDerId - 1;
            }

            // Llamada recursiva con los indices bien para el array
            NodoArbol nodoDerecho = null;
            NodoArbol nodoIzquierdo = null;

            if (hijoIzq != -1) {
                nodoIzquierdo = crearNodoArbol(datosSalas, hijoIzq);
            }

            if (hijoDer != -1) {
                nodoDerecho = crearNodoArbol(datosSalas, hijoDer);
            }


            nodo = new NodoArbol(sala, nodoIzquierdo, nodoDerecho);
        }

        return nodo;
    }


    /**
     * Cadena con la secuencia óptima de transiciones entre salas
     * para llegar al tesoro con mayor valor en la mazmorra
     */

    // Clase para guardar ruta y tesoro
    private static class Ruta {
        int valor;
        String camino;

        Ruta(int valor, String camino) {
            this.valor = valor;
            this.camino = camino;
        }
    }

    public String rutaMejorTesoro() {
        if (raiz == null) return "->";

        Ruta mejor = rutaRecursiva(raiz);
        return "-> " + mejor.camino + " (" + mejor.valor + ") X";
    }


    private Ruta rutaRecursiva(NodoArbol nodo) {
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) { // nodo es hoja
            return new Ruta(nodo.getDato().getValor(), nodo.getDato().getId() + " ");
        }

        Ruta mejor = null;
        String simbolo = "| ";

        if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) { // puede ser / o \
            Ruta izq = rutaRecursiva(nodo.getIzquierdo());
            Ruta der = rutaRecursiva(nodo.getDerecho());

            if (der.valor > izq.valor) { // comprueba valor tesoro izq con der
                mejor = der;
                simbolo = "\\ ";
            } else {
                mejor = izq;
                simbolo = "/ ";
            }

            // caso |
        } else if (nodo.getIzquierdo() != null) { // | izq
            mejor = rutaRecursiva(nodo.getIzquierdo());

        } else { // | der
            mejor = rutaRecursiva(nodo.getDerecho());
        }

        return new Ruta(mejor.valor, nodo.getDato().getId() + " " + simbolo + mejor.camino);
    }


    /**
     * devolver una cadena representando la ruta que llega a un tesoro y que
     * implica enfrentarse a monstruos cuya suma acumulada de niveles sea mínima
     */

    public String rutaMasFacil() {
        String res;
        if (raiz == null) {
            res = "->";
        } else {
            Ruta mejor = buscarRutaMasFacil(raiz, 0);
            res = "-> " + mejor.camino + "X";
        }
        return res;
    }

    private Ruta buscarRutaMasFacil(NodoArbol nodo, int sumaPrev) {
        int valorNodo = nodo.getDato().getValor();

        int nivel;
        if (valorNodo > 0) { // nivel local (monstruos >0, en caso contrario 0)
            nivel = valorNodo;
        } else {
            nivel = 0;
        }

        int suma = sumaPrev + nivel;

        // hoja
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            if (valorNodo >= 0) { // Hoja con tesoro aunque valga 0
                String hoja = nodo.getDato().getId() + " (" + valorNodo + ") ";
                return new Ruta(suma, hoja);
            } else { // Hoja sin tesoro, sala vacía
                return new Ruta(-1, "");
            }
        }

        // recursión
        Ruta izqRuta = null, derRuta = null;
        if (nodo.getIzquierdo() != null) {
            izqRuta = buscarRutaMasFacil(nodo.getIzquierdo(), suma);
        }
        if (nodo.getDerecho() != null) {
            derRuta = buscarRutaMasFacil(nodo.getDerecho(), suma);
        }

        // segun el enunciado la ruta debe tener un tesoro
        boolean izqValida = izqRuta != null && izqRuta.valor >= 0;
        boolean derValida = derRuta != null && derRuta.valor >= 0;

        Ruta mejorRuta;
        String direccion;
        if (izqValida && (!derValida || izqRuta.valor <= derRuta.valor)) {
            mejorRuta = izqRuta;
            direccion = "/ ";
        } else if (derValida) { // izq no valida ó izq.valor > der.valor
            mejorRuta = derRuta;
            direccion = "\\ ";
        } else { // Ninguna rama lleva a un tesoro
            return new Ruta(-1, "");
        }

        // concatenamos nodo actual
        String prefijo;
        if (suma > 0) {
            prefijo = nodo.getDato().getId() + "[" + suma + "] ";
        } else {
            prefijo = nodo.getDato().getId() + " ";
        }

        String camino  = prefijo + direccion + mejorRuta.camino;

        // suma completa (mejorRuta.valor)
        return new Ruta(mejorRuta.valor, camino);
    }



    /**
     * construirá un diccionario con las salas que contengan monstruos
     * (salas internas con valor positivo). La clave de este diccionario será el nivel de cada
     * monstruo.
     */
    public DiccionarioABB getMonstruos() {
        DiccionarioABB monstruos = new DiccionarioABB();
        preordenMonstruos(this.raiz, monstruos);
        return monstruos;
    }

    private void preordenMonstruos(NodoArbol nodo, DiccionarioABB monstruos) {
        if (nodo == null) return;

        int valor = nodo.getDato().getValor();
        if (valor > 0 && (nodo.getIzquierdo() != null || nodo.getDerecho() != null)) { // valor positivo y al menos un hijo
            monstruos.insertar(valor, nodo.getDato());
        }

        preordenMonstruos(nodo.getIzquierdo(), monstruos);
        preordenMonstruos(nodo.getDerecho(), monstruos);
    }


    /**
     * construirá un diccionario con las salas que contengan tesoros (salas hoja
     * con valor positivo). La clave de este diccionario será el valor de cada tesoro.
     */
    public DiccionarioABB getTesoros() {
        DiccionarioABB tesoros = new DiccionarioABB();
        preordenTesoros(this.raiz, tesoros);
        return tesoros;
    }

    private void preordenTesoros(NodoArbol nodo, DiccionarioABB tesoros) {
        if (nodo == null) return;

        int valor = nodo.getDato().getValor();
        if (valor > 0 && nodo.getIzquierdo() == null && nodo.getDerecho() == null) { // valor positivo sin hijos
            tesoros.insertar(valor, nodo.getDato());
        }

        preordenTesoros(nodo.getIzquierdo(), tesoros);
        preordenTesoros(nodo.getDerecho(), tesoros);
    }


}
