#include<stdio.h>
#include<string.h>

int ler_string( char vet[], int MAX){
    int ultimo;

    fflush(stdin);
    fgets(vet,MAX,stdin);
    ultimo = strlen(vet)-1;
    if(vet[ultimo] == '\n')
        vet[ultimo]= '\x0';

    return 1;}





