public class Nodo {

    private Producto producto;
    private Nodo siguiente;

    public Nodo(Producto producto, Nodo siguiente) {
        this.producto = producto;
        this.siguiente = siguiente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Producto getDato() {
        return producto;
    }

    public void setDato(Producto producto) {
        this.producto = producto;
    }
}
