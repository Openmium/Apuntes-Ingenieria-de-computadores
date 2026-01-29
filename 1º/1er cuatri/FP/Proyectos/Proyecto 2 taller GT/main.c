#include <stdio.h>
#include <stdlib.h>

int main() {
    char opcion, digito, letra;
    unsigned int DNI;

    printf("1 scan DNI y calcular letra\n2 scan DNI y letra y comprobar letra\n0 END\n");

    printf("opcion ?\n");
    scanf(" %c", &opcion);

    if (opcion == '1') {
        DNI = 0; // Reiniciar DNI

        for (int i = 1; i <= 8; i++) {
            int valorDigito;
            do {
                printf("DNI digito %i: ", i);
                fflush(stdin);
                scanf(" %c", &digito);

                if (digito >= '0' && digito <= '9') {
                    valorDigito = digito - '0';
                    if (valorDigito < 0 || valorDigito > 9) {
                        printf("Entrada no válida. Ingrese un dígito del 0 al 9.\n");
                    }
                } else {
                    printf("Entrada no válida. Ingrese un dígito del 0 al 9.\n");
                    valorDigito = -1; // Establecer un valor inválido para repetir la solicitud
                }
            } while (valorDigito >= 0 || valorDigito <= 9);

            DNI = 10 * DNI + valorDigito;
        }

        printf(">DNI: %u\n", DNI);
        printf(">restoDNI: %u\n", DNI % 23);

        switch (DNI % 23) {
            // ... Resto del código para calcular la letra
        }

    } else if (opcion == '2') {
        // Código para la opción 2

    } else if (opcion == '0') {
        return 0;
    } else {
        printf("Opción no válida\n");
    }

    return 0;
}
