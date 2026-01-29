public class Alumno {

    String nombre;
    String matricula;
    Double calificacion;
    String[] asignaturas;
    int numAsignaturas;

    public Alumno() {
        this("", "", 0.0);
    }

    public Alumno(String nombre, String matricula, Double calificacion) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.calificacion = calificacion;
        this.asignaturas = new String[4];
        this.numAsignaturas = 0;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public void setAsignatura(String asignatura) {
        if (numAsignaturas < 5) {
            asignaturas[numAsignaturas] = asignatura;
            numAsignaturas++;
        } else {
            System.out.println("No es posible añadir más asignaturas.");
        }
    }

    public int getNumAsignaturas() {
        return numAsignaturas;
    }

    public void getAsignaturas() {
        if (numAsignaturas != 0) {
            System.out.printf("%d asignaturas: \n", numAsignaturas);
            for (int i = 0; i < numAsignaturas; i++) {
                System.out.printf("\t-%s.\n", asignaturas[i]);
            }
        } else {
            System.out.println("No está matriculado en ninguna asignatura");
        }
    }


    public void mostrarAlumno() {
        System.out.println(getNombre() + ". Matrz: " + getMatricula() + "(" + getCalificacion() + ")");
        getAsignaturas();
    }


}



