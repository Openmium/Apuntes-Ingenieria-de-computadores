#include <stdio.h>
#include <stdlib.h>
#define RSV 100

    void scan_dig(char *, unsigned*);
    void scan_DNI(unsigned *);
    unsigned resto_DNI(unsigned *);
    void scan_letra(char *);
    char letra_calculada(unsigned *);
    void validar_letra_DNI(unsigned *);

int main() {
    int DNI = 0;
    unsigned resto = 0;
    char digito, *ptr = NULL, letraS, letraC;


    ptr = malloc(RSV * sizeof(char));
    if (ptr != NULL) {
        scan_dig(&digito, &DNI);
        scan_DNI(&DNI);

        scan_letra(&letraS);

        resto = resto_DNI(&DNI);
        printf("\n> Resto DNI: %u ", resto);

        letraC = letra_calculada(&DNI);


        if (letraC != letraS){
            printf("\n> Letra DNI introducida INCORRECTA");
        }else{printf("\n> Letra DNI introducida CORRECTA");
            }

        validar_letra_DNI(&DNI);

     } else {
        printf("Memoria asignada insuficiente\n");
           }
free(ptr);
    return 0;
}

void scan_dig(char *d, unsigned *DNI) {
    for (int i = 1; i <= 8; i++) {
        printf("DNI digito %i: ", i);
        scanf(" %c", d);

        (*DNI) = (*DNI) * 10 + ((*d) - '0');
    }
}

void scan_DNI(unsigned *DNI){
printf("\nDNI: %u", (*DNI));
}

unsigned resto_DNI(unsigned *DNI){
    unsigned r;
    r = (*DNI)%23;
    return r;
}

void scan_letra(char *l){
printf("\nLetra DNI? ");
scanf(" %c", l);

}

char letra_calculada(unsigned *DNI){

 char letras[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

 return letras[(*DNI) % 23];
}

void validar_letra_DNI(unsigned *DNI){
    char letraCorrect;
 char letras[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
 letraCorrect = letras[(*DNI) % 23];
 printf("\n> Letra correcta: %c", letraCorrect);
}

