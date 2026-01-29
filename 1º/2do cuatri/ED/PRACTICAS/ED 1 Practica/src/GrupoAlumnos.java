public class GrupoAlumnos {
    private String nombre;
    private Alumno[] listaAlumnos;
    private int numAlumnos;
    private static final int MAXIMO = 10;


    public GrupoAlumnos(String nombre, int maximo) {
        this.nombre = nombre;
        numAlumnos = 0;
        int maximo1 = maximo;
        listaAlumnos = new Alumno[maximo1];
    }

    public GrupoAlumnos() {
        this("Grupo Desconocido", MAXIMO);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMAXIMO() {
        return MAXIMO;
    }

    public boolean insertarAlumno(Alumno alumno) {
        if (numAlumnos < MAXIMO) {
            listaAlumnos[numAlumnos] = alumno;
            numAlumnos++;
            return true;
        } else {
            return false;
        }
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public Alumno getAlumno(int i) {
        if (i <= getNumAlumnos()) {
            return listaAlumnos[i];
        } else {
            return null;
        }
    }


    public void mostrarGrupo() {
        System.out.printf("GRUPO: %s: %d alumnos\n", nombre, numAlumnos);
        for (int i = 0; i < getNumAlumnos(); i++) {
            listaAlumnos[i].mostrarAlumno();
        }
    }


    public double mediaCalificaciones() {
        double suma = 0;
        double total = 0;

        if (numAlumnos != 0) {
            for (int i = 0; i < numAlumnos; i++) {
                suma = suma + listaAlumnos[i].getCalificacion();
            }
            total = suma / numAlumnos;
        } else {
            total = -1;
        }

        return total;
    }

    public Alumno mejorAlumno() {
        int elMejor = 0;
        if (numAlumnos >= 1) {
            for (int i = 1; i < numAlumnos; i++) {
                if (listaAlumnos[i].getCalificacion() > listaAlumnos[elMejor].getCalificacion()) {
                    elMejor = i;
                }
            }
        } else {
            listaAlumnos[elMejor] = null;
        }

        return listaAlumnos[elMejor];
    }

    public boolean eliminarAlumno(String nombreAlumno) {
        for (int i = 0; i < numAlumnos; i++) {
            if (nombreAlumno.equals(listaAlumnos[i].getNombre())) {
                for (int j = i; j < numAlumnos - 1; j++) {
                    listaAlumnos[j] = listaAlumnos[j + 1];
                }
                listaAlumnos[numAlumnos - 1] = null;
                numAlumnos--;
                return true;
            }
        }
        return false;
    }


}
