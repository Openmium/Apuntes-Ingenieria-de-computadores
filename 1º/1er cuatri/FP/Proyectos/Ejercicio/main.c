   /*Escribir un programa en C que lea
caracteres por teclado, si se lee una letra
mayúscula imprima la correspondiente
minúscula y viceversa, si lee un dígito
imprima el valor resultante de sumarle
80. Cualquier otro carácter no se
imprimirá.
El programa termina cuando se lea un ‘*’
*/
#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a;
    do {
        printf("Que quiere\n");
        scanf("%i", &a);
    } while (a == 0);

    return 0;
}
