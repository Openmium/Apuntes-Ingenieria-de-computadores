#include <stdio.h>
#include <stdlib.h>

#define N 100

int main()
{
    /*
    Escribir un programa que lea de teclado mayúculas (cualquier otro carácter introducido
    no se tendrá en cuenta) hasta que aparezca la Z. Después deberá imprimir, para las mayusculas que hayan aparecido,
    cuantas veces lo han hecho y su porcentaje frente al resto, si una letra no ha aparecido
    no se debera mostrar.
    */

    int cont[N]={0};
    float total =0;
    char letras[N]={0}, mayus;
     do{

        scanf("%c", &mayus);
        if(mayus>='A' && mayus<='Z'){
            cont[mayus]++;
            letras[mayus]=mayus;
         }

     }while(mayus != 'Z');

      for(int i=0; i<N; i++){
        total = total+cont[i];
      }

        for(int i=0; i<N; i++){
         if(cont[i] != 0){
            printf("%c  %i  %.2f\n", letras[i], cont[i], (cont[i]*100)/total);
          }
        }
    return 0;
}
