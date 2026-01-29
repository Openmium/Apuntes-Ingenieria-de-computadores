import java.util.Iterator;
import java.util.LinkedList;

public class Factura {
    private String dni;
    private String fecha;
    private Lista listaProductos;
    private boolean cobrada;

    public Factura(String dni, String fecha) {
        this.dni = dni;
        this.fecha = fecha;
        listaProductos = new Lista();
        cobrada = false;
    }

    public String getDNI() {
        return dni;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean estaCobrada() {
        return cobrada;
    }

    public void cobrada() {
        cobrada = true;
    }

    public void anyadirProducto(Producto producto) {
        Iterador iterator = listaProductos.getIterador();
        boolean encontrado = false;

        while (iterator.hasNext() && !encontrado) {
            Producto product = iterator.next();

            if (product.equals(producto)) { // si encuentra el producto
                product.setUnidades(product.getUnidades() + producto.getUnidades());
                encontrado = true;

            }
        }
        if(!encontrado){// si no encuentra el producto
            listaProductos.insertar(producto);
        }
    }

    public void mostrar() {
        System.out.println("Factura de:" + dni + ". " + "Fecha: " + fecha);

        for(int i = 0; i<listaProductos.getNumElementos(); i++){
            listaProductos.getElemento(i).mostrar();
        }

        System.out.println("IMPORTE TOTAL: " + importeTotal());

    }

    public float importeTotal() {
        float total = 0.0F;
        int i = 0;
        // teniendo en cuenta el precio por unidad y el numero de unidades
        Iterador iterador = listaProductos.getIterador();

        while(iterador.hasNext()){
            Producto product = iterador.next();
            int unit = product.getUnidades();
            float price = product.getPrecio();
            total += unit * price;
        }
        return total;
    }

    public int eliminarProducto(Producto producto) {
        int resul = 0;
        Iterador it = listaProductos.getIterador();

        if(listaProductos.contiene(producto)){ // Si se encuentra el producto
            while(it.hasNext()){
                Producto prod = it.next();
                if(prod.equals(producto)){
                    if(producto.getUnidades() < prod.getUnidades()){
                        resul = producto.getUnidades(); // nº de ocurrencias eliminadas
                        prod.setUnidades(prod.getUnidades() - resul); // Actualiza unidades

                    }else{ // Si el numero de unidades es igual o superior
                        resul = prod.getUnidades();
                        listaProductos.borrar(prod); // Elimina el prod de la lista
                    }
                }
            }
        }// Si no se encuentra mantenemos resul = 0

        return resul;
    }

    public Lista mayoresPrecios(float precio) {
        Lista listaFiltrada = new Lista();
        Iterador iter = listaProductos.getIterador();

        while (iter.hasNext()) {
            Producto prod = iter.next();
            if (prod.getPrecio() > precio) {
                listaFiltrada.insertar(prod);
            }
        }

        return listaFiltrada;
    }

}
