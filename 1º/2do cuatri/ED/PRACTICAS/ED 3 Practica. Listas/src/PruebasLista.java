import java.util.Iterator;

public class PruebasLista {
    public static void main(String[] args) {
        // 2.2
        Producto producto1 = new Producto("Mesa de escritorio", 185, 2);
        Producto producto2 = new Producto("Silla oficina", 95.9F, 3);
        Producto producto3 = new Producto("Mesa cocina", 125);
        Producto producto4 = new Producto("Sillón reclinable", 230, 2);


        Lista lista = new Lista();

        lista.insertar(producto1);
        lista.insertar(producto2);
        lista.insertar(producto3);
        lista.insertar(producto4);

        lista.mostrar();

        lista.borrar(producto2);

        lista.mostrar();


        // 3.2
        Factura factura = new Factura("12345678B", "17/03/2021");
        factura.anyadirProducto(producto1);
        factura.anyadirProducto(producto2);
        factura.anyadirProducto(producto3);
        factura.anyadirProducto(producto4);

        Producto producto5 = new Producto("Silla oficina", 95.9F);
        factura.anyadirProducto(producto5);

        factura.mostrar();


        // 3.5
        Producto producto6 = new Producto("Silla oficina", 95.9F, 2);
        Producto producto7 = new Producto("Sillón reclinable", 230, 3);

        System.out.printf("Se han eliminado %d unidades \n",
                factura.eliminarProducto(producto6) +
                factura.eliminarProducto(producto7));

        factura.mostrar();



        // 3.6
        System.out.println("Se han obtenido " + factura.mayoresPrecios(100).getNumElementos() + " productos con precio mayor a 100€ por unidad");

        factura.mayoresPrecios(100).mostrar();


        // 4
        FacturaBib facturaBib = new FacturaBib("88888888A", "08/08/2008");

        Producto productoA = new Producto("Armario", 385, 5);
        Producto productoB = new Producto("Cama", 255, 3);
        Producto productoC = new Producto("Cama", 255, 2);
        Producto productoD = new Producto("Armario", 385);

        facturaBib.anyadirProducto(productoA);
        facturaBib.anyadirProducto(productoB);
        facturaBib.anyadirProducto(productoC);

        facturaBib.eliminarProducto(productoD);

        facturaBib.mostrar();

        System.out.println("Se han obtenido " + facturaBib.mayoresPrecios(250).size() + " productos con precio mayor a 250€ por unidad");


        Iterator<Producto> it = facturaBib.mayoresPrecios(250).iterator();

        while(it.hasNext()){
            Producto prod = it.next();
            prod.mostrar();
        }


    }
}
