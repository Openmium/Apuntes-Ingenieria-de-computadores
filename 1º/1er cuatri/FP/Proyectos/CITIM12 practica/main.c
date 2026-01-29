#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define M 4
#define N 5


void matriz1(char m1[M][N]);
void printMatriz(char m1[M][N]);// Prototipo de funciones
void rotarMatriz(char m1[M][N], char temp[M][N]);


int main() {
    char m1[M][N];
    char temp[M][N];

    srand(time(NULL));

    matriz1(m1); // funcion(m1), funcion(m1[M][N]) y funcion(&m1[M][N]) tendría el mismo efecto,
    printMatriz(m1);// ya que todos indican la dirección de memoria del primer elemento del arreglo.
// un arreglo cuando se pasa como parámetro a una función se convierte en un puntero,
// esencialmente se está pasando la dirección de memoria del primer elemento, que es un puntero a un arreglo de N elementos.
    rotarMatriz(m1, temp);
    printf("\n");
    printMatriz(temp);


    return 0;
}

void matriz1(char m1[M][N]) {
    for (int fila = 0; fila < M; fila++) {
        for (int columna = 0; columna < N; columna++) {
            m1[fila][columna] = rand() % ('Z'-'A'+1) + 'A';
        }
    }
}

void printMatriz(char  m1[M][N]){

      for (int fila = 0; fila < M; fila++) {
        for (int columna = 0; columna < N; columna++) {
            printf("%c ", m1[fila][columna]); // Imprime matriz
        }
        printf("\n");
      }
}

void rotarMatriz(char m1[M][N], char temp[M][N]){

    for(int c = 1; c<N-1; c++){ //parte interna sin rotación

        temp[1][c]=m1[1][c];

        temp[2][c]=m1[2][c];
      }


     for(int c = 1; c<=N-1; c++){//parte de abajo
            temp[M-1][c-1]=m1[M-1][c];
        }

     for(int c = 1; c<=N-1; c++){//parte superior
            temp[0][c]=m1[0][c-1];
          }


     for(int f = 0; f<M-1; f++){//parte lateral derecha
        temp[f+1][N-1]=m1[f][N-1];
     }

     for(int f = 0; f<M-1; f++){//parte lateral izquierda
      temp[f][0]=m1[f+1][0];
    }

}
