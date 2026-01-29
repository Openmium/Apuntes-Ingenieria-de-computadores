#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define M 5
#define N 6

void valoresM(int matriz[M][N]);
void mayor(int matriz[M][N]);
void printM(int matriz[M][N]);

int main()
{
    int matriz[M][N];
    srand(time(NULL));
/*
    Implementar una funcion que, pasandole como parametro una matriz de MxN enteros
    (siendo M y N ctes definidas previamente), devuelva __________(a través de los parametros) **no se puede devolver dos datos por una funcion,1printas o 2 apuntas
    la fila y la columna en la que se encuentra el mayor valor.
*/
    valoresM(matriz);
    mayor(matriz);
    printM(matriz);


    return 0;
}

void valoresM(int matriz[M][N]){

    for(int f=0; f<M; f++){
        for(int c=0; c<N; c++){
            matriz[f][c]=rand()%101;
        }
    }
}

void mayor(int matriz[M][N]){
    int mayor = matriz[0][0];
    int fila, columna;
    for(int f=0; f<M; f++){
        for(int c=0; c<N; c++){

            if(matriz[f][c]> mayor){
                mayor = matriz[f][c];
                  fila=f;
                  columna=c;
            }

        }

    }
    printf("La fila del mayor es %i y la columna %i\n", fila, columna);
}

void printM(int matriz[M][N]){
    for(int f=0; f<M;f++){
        for(int c=0; c<N;c++){
            printf(" %i", matriz[f][c]);
        }
          printf("\n");
    }

}
