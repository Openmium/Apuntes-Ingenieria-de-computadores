#include <stdio.h>
#include <stdlib.h>

int main()
{
    unsigned s1[2]={0};
    unsigned s2[2]={3,4};



    for(int i=0; i<2; i++){

        printf("ANTES %u\n", s1[i]);
    }


    memcpy(s1,s2, 2*sizeof(unsigned));// el 2 hace que se muevan 8 bytes // esto copia s2 en s1 desde el donde se quiera (como hacer s2=s1 pero legal xd)


    for(int i=0; i<2; i++){

        printf("DESPUES %u\n", s1[i]);
    }

    return 0;
}

/*
int main(){

    if(memcmp(m1,m2, N*sizeof(char)) == 0){//te compara las los primeros Nbytes y si son iguales, lo printa

        printf("iguales");
    }

}
*/
