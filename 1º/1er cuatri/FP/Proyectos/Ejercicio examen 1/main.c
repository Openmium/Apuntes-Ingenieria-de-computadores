#include <stdio.h>
#include <stdlib.h>
#define M 5
#define N 3
 void encontrar(int matriz[M][N],int fmax, int cmax); //nos pueden dar esta linea como prototipo y a partir de aqui hacerlo
int main()
{
  /*Implementar una funcion que, pasandole como parametro
una matriz de MxN enteros (siendo M y N ctes definidas previamente),
devuelva __________(a través de los parametros) la fila y la columna en la que se encuentra el mayor valor.*/


//declarar array

   int max = matriz[0][0]; //ni cero ni nada, porque los int pueden ser positivos o negativos y al hacer 00 comenzamos a trabajar con un numero
   int f,c;
   int *fmax //declaramos como direcciones para usarlos más tarde
   int *cmax

   for(f=0; f<=M-1; f++)
     for(c=0; c<=N-1; c++)

   if(matriz[f][c] >= max){ //el = sirve para inicializar que se tome el primer valor para trabajar
    max = matriz[f][c]; //hay que tratar maz  fmax cmax como direcciones
    *fmax = f;
    *cmax  = c;
   }
    return 0;
}
