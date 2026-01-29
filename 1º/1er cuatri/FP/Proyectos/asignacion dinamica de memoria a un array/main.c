#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    int cols = 0;
    printf("Ingrese el número de columnas: ");
    scanf("%i", &cols);

    int *matrix;
    int (*ptr)[cols];

    matrix = (int *)malloc(cols * sizeof(int));

    ptr = (int (*)[cols])matrix;

    srand(time(NULL)); // Inicializar la semilla para generar números aleatorios

    // Rellenar la "matriz" con números aleatorios
    for (int j = 0; j < cols; j++) {
        (*ptr)[j] = rand() % 100; // Generar números aleatorios entre 0 y 99
    }

    // Imprimir la "matriz"
    printf("La matriz generada es:\n");
    for (int j = 0; j < cols; j++) {
        printf("%d ", (*ptr)[j]);
    }
    printf("\n");

    free(matrix);

    return 0;
}
