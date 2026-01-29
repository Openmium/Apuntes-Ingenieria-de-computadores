#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 4

void cal_digito(unsigned int matriz[N][N]);

int main() {
    unsigned int matriz[N][N];
    srand(time(NULL));

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            matriz[i][j] = rand() % 10; // Rellena la matriz con dígitos aleatorios del 0 al 9
        }
    }

    cal_digito(matriz);

    return 0;
}

void cal_digito(unsigned int matriz[N][N]) {
    unsigned int digitos[10] = {0}; // Inicializa la matriz de conteo de dígitos a cero

    for (int f = 0; f < N; f++) {
        for (int c = 0; c < N; c++) {
            digitos[matriz[f][c]]++; // Incrementa el contador para el dígito actual
        }
    }

    // Imprime la frecuencia de aparición de cada dígito
    for (int i = 0; i < 10; i++) {
        printf("El dígito %d aparece %d veces\n", i, digitos[i]);
    }
}
