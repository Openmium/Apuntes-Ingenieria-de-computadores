public class Lista {
    private Nodo inicio, fin;
    private int numElementos;

    // Inicializa una lista vacía (sin elementos)
    public Lista() {
        inicio = null;
        fin = null;
        numElementos = 0;
    }

    // Determina si la lista está vacía o no (sin elementos)
    public boolean vacia() {
        return inicio == null;
    }

    // Añade un nuevo dato al final de la lista
    public void insertar(Producto producto) {
        Nodo nuevo = new Nodo(producto, null);  // Crear un nodo nuevo
        if (inicio == null) {  // Insertar el nodo al final de la lista enlazada
            inicio = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        numElementos++;
    }


    // Devuelve el elemento que ocupa una posicion dada.
    // Si no existe la posicion, devuelve -1
    public Producto getElemento(int posicion) {
        if (posicion < 0 || posicion >= numElementos) {
            return null;
        } else {
            // Avanzar en la lista enlazada tantos nodos como indique posicion
            Nodo actual = inicio;
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            return actual.getDato();
        }
    }

    // Almacena elemento en la posicion indicada por posicion
    // Si la posicion es incorrecta, devuelve false
    public boolean setElemento(Producto producto, int posicion) {
        if (posicion < 0 || posicion >= numElementos) {
            return false;
        } else {
            Nodo actual = inicio;
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            actual.setDato(producto);
            return true;
        }
    }

    // Borra la primera ocurrencia del par�metro dato (si existe)
    public boolean borrar(Producto producto) {
        Nodo actual = inicio;
        Nodo anterior = null;
        while (actual != null && !actual.getDato().equals(producto)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (actual != null) {  // dato encontrado.
            if (actual == inicio) {   // Borrar el primero de la lista
                inicio = actual.getSiguiente();
            } else {  // Borrar nodo que no es el primero
                anterior.setSiguiente(actual.getSiguiente());
            }
            if (actual == fin) {  // Se ha borrado el último de la lista
                fin = anterior;
            }
            numElementos--;
            return true;
        } else {
            return false;
        }
    }

    // Devuelve la primera posición en la que se encuentra el parámetro dato (si existe)
    public int posicion(Producto producto) {
        Nodo actual = inicio;
        int posicion = 0;
        while (actual != null && !actual.getDato().equals(producto)) {
            actual = actual.getSiguiente();
            posicion++;
        }
        if (actual != null) {  // Dato encontrado
            return posicion;
        } else {
            return -1;
        }
    }

    // Determina si el parametro dato existe en la lista.
    public boolean contiene(Producto producto) {
        return this.posicion(producto) >= 0;
    }

    // Devuelve el número de elementos que tiene la lista
    public int getNumElementos() {
        return numElementos;
    }

    // Devuelve un iterador para recorrer la lista desde el principio
    public Iterador getIterador() {
        return new Iterador(inicio);
    }

    // Muestra el contenido de la lista.
    public void mostrar() {
        if (this.vacia()) {
            System.out.println("Lista vacia");
        } else {
            Nodo actual = inicio;
            while (actual != null) {
                actual.getDato().mostrar();
                actual = actual.getSiguiente();
            }
            System.out.println();
        }
    }

}
