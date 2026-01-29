#include <stdio.h>
#include <stdlib.h>

 struct ALUMNOS{
    char nombre[30+1];
    char dni[9+1];
    unsigned edad;
    };

int main()
{
   struct ALUMNOS a1;

    a1.edad = 19; //nos referimmos al objeto a1

   printf("> Edad? \n");
   scanf("%u", &a1.edad);

    printf("> DNI? \n");
   fflush(stdin);
   gets(a1.dni);
    strcpy(a1.dni, "43145675L");

   printf("> Nombre? \n");
   fflush(stdin);
    gets(a1.nombre);//por que no usa scanf, porque a scanf si le metes intro o blanco se para.
     strcpy(a1.nombre, "Ivan Alvarez");


return 0;
}
