#include<stdio.h>
#include<conio.h>
#define MAX 100


int listar_vet(float mat[MAX][MAX],int *cont){
    int i,t;

    for(i=0;i< *cont;i++){
            for(i=0;i< *cont;i++){
                    printf("%2.d-%2.d---->%2.2f\t",i+1,t+1, mat[i][t]);
    }
    printf("\n");
    }
return 1;
}
