#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define M 4
#define N 5

void matriz1(char m1[M][N]);
void printMatriz(char m1[M][N]);
void rotarMatriz(char m1[][N], char temp[][N]);

int main() {
    char m1[M][N];
    char temp[M][N];

    srand(time(NULL));

    matriz1(m1);
    printf("Matriz original:\n");
    printMatriz(m1);

    rotarMatriz(m1, temp);

    printf("\nMatriz rotada:\n");
    printMatriz(temp);

    return 0;
}

void matriz1(char m1[M][N]) {
    for (int fila = 0; fila < M; fila++) {
        for (int columna = 0; columna < N; columna++) {
            m1[fila][columna] = rand() % ('Z' - 'A' + 1) + 'A';
        }
    }
}

void printMatriz(char m1[M][N]) {
    for (int fila = 0; fila < M; fila++) {
        for (int columna = 0; columna < N; columna++) {
            printf("%c ", m1[fila][columna]);
        }
        printf("\n");
    }
}

void rotarMatriz(char m1[][N], char temp[][N]) {
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            temp[i][j] = m1[i][j];
        }
    }

    for (int i = 1; i < M - 1; i++) {
        for (int j = 1; j < N - 1; j++) {
            temp[i - 1][j] = m1[i][j - 1];
        }
    }

    for (int j = 1; j < N - 1; j++) {
        temp[M - 2][j] = m1[M - 1][j - 1];
    }

    for (int i = 1; i < M - 1; i++) {
        temp[i][N - 1] = m1[i - 1][N - 1];
    }

    for (int j = N - 2; j > 0; j--) {
        temp[1][j] = m1[0][j - 1];
    }

    for (int i = M - 2; i > 0; i--) {
        temp[i][0] = m1[i + 1][0];
    }
}



