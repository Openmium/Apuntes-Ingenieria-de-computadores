package app;

public class Cliente {
    private String nombre;
    private int edad;
    private int id;
    private String bebida_favorita;

    // Completar Constructor
    public Cliente(String nombre, int id, int edad, String bebida_favorita) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.bebida_favorita = bebida_favorita;
    }


    // Realizar Getters

    public String getNombre(){
        return nombre;
    }
    public int getId() {
        return id;
    }
    public int getEdad(){
        return edad;
    }
    public String getBebida_favorita(){
        return bebida_favorita;
    }


    // Realizar Setters

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setEdad(int edad){
        this.edad = edad;
    }
    public void setBebida_favorita(String bebida_favorita){
        this.bebida_favorita = bebida_favorita;
    }


    // Completar toString
    public String toString() { // Cliente <nº>: { <nombre> , edad: <>, bebida: <>}
        return ("Cliente" + id + ": { " + nombre + ", edad: " + edad + ", bebida: " + bebida_favorita + " }");
    }

    // Completar equals
    public boolean equals(Cliente c1) { // Debe retornar True cuando el id de cliente sea el mismo que el actual. False en caso contrario.
        boolean res = false;
        if(id == c1.id) res = true;

        return res;
    }


}
