#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define N 100

unsigned resto_DNI(unsigned );
char letra_calculada(unsigned );

void rand_dig(char *digito);
void rand_DNI(unsigned *DNI);
void rand_DNIs(unsigned[N], char [N]);
void printf_DNId(unsigned [N], char [N]);
void calcular_frecuancias(char [N], unsigned[26], float[26]);
void print_frecuencias(unsigned [26],float[26]);
void printf_barra (float);

int main()
{
    char digitos;
    unsigned DNI = 0;
    srand(time(NULL));

    rand_dig( & digitos);



    rand_DNI(& digitos);

    return 0;
}

void rand_dig(char *digito){ // sacar digitos random de 8 en 8 osea como DNI y guardarlos en una funcion

    *digito = (*digito)*10 + rand() %10;

  /*
    for (int i = 0; i<8; i++){

    *digitos = *digitos -'0';
    digitos = (*digitos)*10 + rand() %10;
    }
 */
}

void rand_DNI(unsigned *DNI){

    for (int i = 0; i<8; i++){

    *DNI = *DNI -'0';
    *DNI = (*DNI)*10 + rand() %10;
    }
    printf("%u", *DNI);
}
