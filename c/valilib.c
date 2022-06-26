


#include<stdio.h>
#include<limits.h>


int valor_positivo_0_int(){
    int num;
    do{
        scanf("%d", &num );

        if(num<=0)
            printf("Numero invalido\nDigite Novamente\n");

    }while(num<=0);

    return num;}

int valor_positivo_int(){
    int num;
    do{
        scanf("%d", &num );

        if(num<0)
            printf("Numero invalido\nDigite Novamente\n");

    }while(num<0);

    return num;}

float valor_positivo_0_float(){
    float num;
    do{
        scanf("%f", &num );

        if(num<=0)
            printf("Numero invalido\nDigite Novamente\n");

    }while(num<=0);

    return num;}

float valor_positivo_float(){
    float num;
    do{
        scanf("%f", &num );

        if(num<0)
            printf("Numero invalido\nDigite Novamente\n");

    }while(num<0);

    return num;}

double valor_positivo_0_double(){
    float num;
    do{
        scanf("%lf", &num );

        if(num<=0)
            printf("Numero invalido\nDigite Novamente\n");

    }while(num<=0);

    return num;}

double valor_positivo_double(){
    float num;
    do{
        scanf("%lf", &num );

        if(num<0)
            printf("Numero invalido\nDigite Novamente\n");

    }while(num<0);

    return num;}



int sim_nao(char *op){

    printf("<S/N>");

    do{

        fflush(stdin);
        scanf("%c", &*op);

        if(*op!='n'&&*op!='N'&&*op!='s'&&*op!='S')
            printf("\nEntrada Invalida!!!\n");


        }while(*op!='n'&&*op!='N'&&*op!='s'&&*op!='S');
return 1;}


