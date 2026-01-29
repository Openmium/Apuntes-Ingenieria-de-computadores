public class Principal {
    public static void main(String[] args) {

        Alumno[] alumno = {
                new Alumno("Felipe Arias González", "aa1253", 3.50),
                new Alumno("Manuel García Sacedón", "ax0074", 8.35),
                new Alumno("Margarita López Medina", "mj7726", 7.70),
                new Alumno("Estela Sánchez Arellano", "bc2658", 6.75)
        };

        GrupoAlumnos grupo2 = new GrupoAlumnos("XF21", 1);

        for (int i = 0; i < 4; i++) {
            alumno[i].setAsignatura("Estructuras de datos");
        }

        alumno[3].setAsignatura("Algebra");
        alumno[3].setAsignatura("Estructuras de Computadores");


        GrupoAlumnos grupo1 = new GrupoAlumnos("CX11", 20);
        for (int i = 0; i < 4; i++) {
            grupo1.insertarAlumno(alumno[i]);
        }

        grupo1.mostrarGrupo();
        grupo1.getAlumno(0).mostrarAlumno();


        if (-1 == grupo1.mediaCalificaciones()) {
            System.out.println("Grupo vacio");
        } else {
            System.out.printf("\nMedia del grupo: %.2f \n", grupo1.mediaCalificaciones());
        }


        if (grupo1.mejorAlumno() != null) {
            grupo1.mejorAlumno().mostrarAlumno();
        } else {
            System.out.println("\nEl grupo esta vacio");
        }


        if (grupo1.eliminarAlumno("Manuel García Sacedón")) {
            grupo1.mostrarGrupo();
        } else {
            System.out.println("No esta en el grupo");
        }

        if (grupo1.eliminarAlumno("Estela Sánchez Arellano")) {
            grupo1.mostrarGrupo();
        } else {
            System.out.println("No esta en el grupo");
        }

        if (grupo1.eliminarAlumno("Felipe Segovia López")) {
            grupo1.mostrarGrupo();
        } else {
            System.out.println("No esta en el grupo");
        }


    }
}
