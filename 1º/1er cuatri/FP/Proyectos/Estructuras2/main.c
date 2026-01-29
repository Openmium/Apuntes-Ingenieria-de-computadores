#include <stdio.h>
#include <stdlib.h>

 struct ALUMNOS{
    char nombre[30+1];
    char dni[9+1];
    unsigned edad;
    };

void print_ALUMNOS(struct ALUMNOS a);

int main()
{
   struct ALUMNOS a1, a2;
    print_ALUMNOS(a1);

    a1.edad = 19; //nos referimmos al objeto a1


   scanf("%u", &a1.edad);
   scanf("%u", &a1.edad);


   fflush(stdin);
   gets(a1.dni);
    strcpy(a1.dni, "43145675L");


   fflush(stdin);
    gets(a1.nombre);//por que no usa scanf, porque a scanf si le metes intro o blanco se para.
     strcpy(a1.nombre, "Ivan Alvarez");


return 0;
}

void print_ALUMNOS(struct ALUMNOS a){
     printf("> Edad? \n", a.edad);
      printf("> DNI? \n",  a.dni);
    printf("> Nombre? \n", a.nombre);

}
