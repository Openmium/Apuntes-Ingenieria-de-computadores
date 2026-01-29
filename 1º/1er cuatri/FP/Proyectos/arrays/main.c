#include <stdio.h>
#include <stdlib.h>
#define M 3
#define N 2

//lo de abajo es muy guarro↓
/*void blancos {
  int f, c;

    for (f=0; f<= m-1 f++)
        for(c=0; c<=n-1 c++)

         m[f][c]=' ';

         if (c>=c1 && c<=c2) m[f][c]=' '
}
*/ //limpiamos lo de arriba:

void blanco(char m[M][N], char c1, char c2){
int f, c;
for (f=0; f<= M-1; f++)
        for(c=0; c<=N-1; c++)

         if (c>=c1 && c<=c2) m[f][c]=' ';
}

int main()
{

   /* char m[M][N]; //matriz
    char c1, c2; //variable que pide
    char c1 ='b', c2='b';

    int f, c; //fila columna

    for (f=0; f<= m-1 f++) //va mirando por filas hasta que termine (m-1) empieza en 0 (0,1,2,3,4...)
        for(c=0; c<=n-1 c++) // y mirando por columnas hasta que termine (n-1) empieza en 0

         //   m[f][c]=' '; //pone todo a blancos por tanto hacemos lo de abajo para cumplir con lo que nos piden↓

         // if (c>=c1 && c<=c2) m[f][c]=' ' // darle cambiazo a funcion↓
*/
    int f, c;
        char m[M][N]={{'a','b'},{'b', 'a'}, {'c', 'd'}};

        blanco(m, 'c', 'a');

    for (f=0; f<= M-1; f++){
        for(c=0; c<=N-1; c++)
            printf(" %c", m[f][c]);
            printf("\n");
    }
    return 0;
}
