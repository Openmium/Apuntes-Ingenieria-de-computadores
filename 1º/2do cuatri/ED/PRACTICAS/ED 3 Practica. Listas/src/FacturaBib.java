import java.util.LinkedList;
import java.util.Iterator;

public class FacturaBib {
    private String dni;
    private String fecha;
    LinkedList<Producto> listaProductos;
    private boolean cobrada;

    public FacturaBib(String dni, String fecha) {
        this.dni = dni;
        this.fecha = fecha;
        listaProductos = new LinkedList<>();
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
        Iterator<Producto> it = listaProductos.iterator();
        boolean encontrado = false;

        while (it.hasNext() && !encontrado) {
            Producto product = it.next();

            if (product.equals(producto)) { // si encuentra el producto
                product.setUnidades(product.getUnidades() + producto.getUnidades());
                encontrado = true;

            }
        }
        if(!encontrado){// si no encuentra el producto
            listaProductos.add(producto);
        }
    }

    public void mostrar() {
        System.out.println("Factura de:" + dni + ". " + "Fecha: " + fecha);

        Iterator<Producto> iterador = listaProductos.iterator();

        while (iterador.hasNext()) {
            Producto producto = iterador.next();
            producto.mostrar();
        }

        System.out.println("IMPORTE TOTAL: " + importeTotal());

    }

    public float importeTotal() {
        float total = 0.0F;
        int i = 0;
        // teniendo en cuenta el precio por unidad y el número de unidades
        Iterator<Producto> iterador = listaProductos.iterator();

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
        Iterator<Producto> it = listaProductos.iterator();

        if(listaProductos.contains(producto)){ // Si se encuentra el producto
            while(it.hasNext()){
                Producto prod = it.next();
                if(prod.equals(producto)){
                    if(producto.getUnidades() < prod.getUnidades()){
                        resul = producto.getUnidades(); // nº de ocurrencias eliminadas
                        prod.setUnidades(prod.getUnidades() - resul); // Actualiza unidades

                    }else{ // Si el numero de unidades es igual o superior
                        resul = prod.getUnidades();
                        listaProductos.remove(prod); // Elimina el prod de la lista
                    }
                }
            }
        }// Si no se encuentra mantenemos resul = 0

        return resul;
    }

    public LinkedList<Producto> mayoresPrecios(float precio) {
        LinkedList<Producto> listaFiltrada = new LinkedList<>();

        Iterator<Producto> iter = listaProductos.iterator();
        while (iter.hasNext()) {
            Producto prod = iter.next();
            if (prod.getPrecio() > precio) {
                listaFiltrada.add(prod); // Añade a la nueva lista
            }
        }

        return listaFiltrada;
    }

}
