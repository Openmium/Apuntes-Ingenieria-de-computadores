#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define N 100
/*
unsigned resto_DNI(unsigned );
char letra_calculada(unsigned );

void rand_dig(char *);
void rand_DNI(unsigned *);
void rand_DNIs(unsigned[N], char [N]);
void printf_DNId(unsigned [N], char [N]);
void calcular_frecuancias(char [N], unsigned[26], float[26]);
void print_frecuencias(unsigned [26],float[26]);
void printf_barra (float);
*/

int main()
{

    /*
    srand() y rand() con las bibliotecas time y stdlib, rand es numero aleatorio y srand es la semilla aleatoria (seed random),
    rand() te devuelve un unsigned que se procesa con %; rand()%10 da un numero del 0 al 9,


    void rand_idg(char *d)
    *d=(rand()%10)
    ↓↓ lo meto en:
    rand_DNI ←esto forma un array de enteros unsigned DNIs[N]

    creamos otro array de chars char letras[N]
    */

    srand(time(NULL));
    int random_variable = rand();
    printf("Random value on [0,%d]: %d\n", RAND_MAX, random_variable);

    return 0;
}
/*
void rand_dig(char *){

}

unsigned resto_DNI(unsigned DNI){
}*/

    return DNI%23;

}
