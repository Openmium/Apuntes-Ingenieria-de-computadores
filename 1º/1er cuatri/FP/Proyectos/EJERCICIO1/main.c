#include <stdio.h>
#include <stdlib.h>



int main()
{
    /*
    Escribir un programa en C que lea caracteres de teclado (‘#’ para finalizar) e imprima por pantalla,
    al final, las dos últimas letras mayúsculas leídas según el orden de aparición. Si no aparece una o las dos, se
    imprimirá el carácter ‘*’ en su lugar. Ejemplos de secuencias de entrada y resultados a imprimir:

    a$%t5wrrmasGt67/()|╗!HhNmsR*+mqX# RX

    a$%t5wrrmasGt67#                  G*

    #                                 **

    */
    char letra, parletra[2]={'\0'};

    do{
    scanf("%c", &letra);
    if(letra >='A' && letra<='Z'){

            parletra[0]=letra;
            parletra[1]=parletra[0];
    }
  }while(letra != '#');

     for(int i=0; i<2; i++){
        if(parletra[i]>='A' && parletra[i]<='Z'){
            printf("%c", parletra[i]);
        }else printf("*");
     }
    return 0;
}
