#include "stdio.h"
#include "stdlib.h"

    void printMenu();
    int scanOpcion(int *);
    void scanDigito(char *, int *);
    unsigned int restoDNI(int *);
    char letraCalculada(int *);
    void scaneoLetra(int *, char *);

int main() {
    int opcion, DNI;
    char digito, letra;
    unsigned int resto;

    do {
        printMenu();
        opcion = scanOpcion(&opcion);

        if (opcion == 1) {
        scanDigito(&digito, &DNI);
        resto = restoDNI(&DNI);
        printf("> Resto DNI: %u\n", resto);

        letra = letraCalculada(&DNI);
        printf("> Letra DNI: %c", letra);

        }else if (opcion == 2) {
        scanDigito(&digito, &DNI);
        resto = restoDNI(&DNI);
        printf("> Resto DNI: %u\n", resto);

        scaneoLetra(&DNI, &letra);

        letra = letraCalculada(&DNI);
        printf("> Letra DNI: %c", letra);
        }

    } while (opcion != 2 && opcion != 1 && opcion != 0);

    return 0;
}

void printMenu(){
    printf("1 scan DNI y calcula letra \n2 scan DNI y letra y comprobar letra \n0 END\n");
}

int scanOpcion(int *opc){
    scanf(" %i", opc);
    return *opc;
}

void scanDigito(char *d, int *DNI) {
    *DNI = 0;

    for (int i = 1; i <= 8; i++) {
        printf("DNI digito %i ?\n", i);
        fflush(stdin);
        scanf(" %c", d);

        if (*d < '0' || *d > '9') {
            i--;
        }

        *DNI = *DNI * 10 + (*d - '0');
    }
    printf("\n> DNI: %i\n", *DNI);
}

unsigned int restoDNI(int *DNI) {
    return *DNI % 23;
}

char letraCalculada(int *DNI){
    char l;
     switch (*DNI % 23) {
        case 0:
            l = 'T';
            break;
        case 1:
            l = 'R';
            break;
        case 2:
            l = 'W';
            break;
        case 3:
            l = 'A';
            break;
        case 4:
            l = 'G';
            break;
        case 5:
            l= 'M';
            break;
        case 6:
            l = 'Y';
            break;
        case 7:
            l = 'F';
            break;
        case 8:
            l = 'P';
            break;
        case 9:
            l = 'D';
            break;
        case 10:
            l = 'X';
            break;
        case 11:
            l = 'B';
            break;
        case 12:
            l = 'N';
            break;
        case 13:
            l = 'J';
            break;
        case 14:
            l = 'Z';
            break;
        case 15:
            l = 'S';
            break;
        case 16:
            l = 'Q';
            break;
        case 17:
            l = 'V';
            break;
        case 18:
            l = 'H';
            break;
        case 19:
            l = 'L';
            break;
        case 20:
            l = 'C';
            break;
        case 21:
            l = 'K';
            break;
        case 22:
            l = 'E';
            break;
        default:
            printf("Opción no válida\n");
            break;
    }

    return l;
}

void scaneoLetra(int *DNI, char *letra) {
    printf("Letra del DNI?\n");
    scanf(" %c", letra);

    if ((*DNI % 23 == 0 && *letra == 'T') ||
        (*DNI % 23 == 1 && *letra == 'R') ||
        (*DNI % 23 == 2 && *letra == 'W') ||
        (*DNI % 23 == 3 && *letra == 'A') ||
        (*DNI % 23 == 4 && *letra == 'G') ||
        (*DNI % 23 == 5 && *letra == 'M') ||
        (*DNI % 23 == 6 && *letra == 'Y') ||
        (*DNI % 23 == 7 && *letra == 'F') ||
        (*DNI % 23 == 8 && *letra == 'P') ||
        (*DNI % 23 == 9 && *letra == 'D') ||
        (*DNI % 23 == 10 && *letra == 'X') ||
        (*DNI % 23 == 11 && *letra == 'B') ||
        (*DNI % 23 == 12 && *letra == 'N') ||
        (*DNI % 23 == 13 && *letra == 'J') ||
        (*DNI % 23 == 14 && *letra == 'Z') ||
        (*DNI % 23 == 15 && *letra == 'S') ||
        (*DNI % 23 == 16 && *letra == 'Q') ||
        (*DNI % 23 == 17 && *letra == 'V') ||
        (*DNI % 23 == 18 && *letra == 'H') ||
        (*DNI % 23 == 19 && *letra == 'L') ||
        (*DNI % 23 == 20 && *letra == 'C') ||
        (*DNI % 23 == 21 && *letra == 'K') ||
        (*DNI % 23 == 22 && *letra == 'E')) {
        printf(">letra %c correcta\n", *letra);
    } else {
        printf(">letra incorrecta\n");
    }
}

