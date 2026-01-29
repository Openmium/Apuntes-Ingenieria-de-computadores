#include <stdio.h>

#define N 10

int main() {
    int matriz[N];

    // Inicializa el array con un valor no ingresado por el usuario
    for (int i = 0; i < N; i++) {
        matriz[i] = -1;
    }

    int num;
    int indexNegativos = 0;
    int indexPositivos = N - 1;

    printf("Ingrese una secuencia de números (finalice con 0):\n");

    // Leer los números hasta que el array esté lleno o se introduzca un 0
    while (indexNegativos < indexPositivos) {
        scanf("%d", &num);

        if (num < 0) {
            // Almacena los números negativos en las primeras posiciones
            matriz[indexNegativos++] = num;
        } else if (num > 0) {
            // Almacena los números positivos en las últimas posiciones
            matriz[indexPositivos--] = num;
        }
        // No hacer nada con el 0 según el enunciado
    }

    // Imprime el array con guiones (-) y signos más (+)
    for (int i = 0; i < N; i++) {
        if (matriz[i] < 0) {
            printf(" -,");
        } else if (matriz[i] > 0) {
            printf(" +,");
        }
        // Omitir los 0 (valor inicial) y cualquier otro número introducido
    }

    return 0;
}
