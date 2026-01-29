#include <stdio.h>
#include <stdlib.h>

int main()
{
    char caracter, caracter2, caracter3, secuencia=0;

   do{
      scanf(" %c", &caracter);
       scanf(" %c", &caracter2);
        scanf(" %c", &caracter3);
        if (caracter == caracter2 && caracter2 == caracter3){
               secuencia = 3;
        }

   }while (secuencia == 3);
    return 0;
}
