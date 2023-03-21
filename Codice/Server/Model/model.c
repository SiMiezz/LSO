#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "model.h"


Bar* creaBar(char* nome){
    Bar* bar = malloc(sizeof(Bar));
    strcpy(bar->nome, nome);
    return bar;
}

Bevanda* creaBevanda(int id, char* nome, float prezzo, Bevanda_Type tipo, char* bar_nome){
    Bevanda* bevanda = malloc(sizeof(Bevanda));
    bevanda->id = id;
    strcpy(bevanda->nome, nome);
    bevanda->prezzo = prezzo;
    bevanda->tipo = tipo;
    strcpy(bevanda->bar_nome, bar_nome);
    return bevanda;
}

Ingrediente* creaIngrediente(char* nome){
    Ingrediente* ingrediente = malloc(sizeof(Ingrediente));
    strcpy(ingrediente->nome, nome);
    return ingrediente;
}

Utente* creaUtente(char* email, char* password, char* nome, char* cognome, char* bar_nome){
    Utente* utente = malloc(sizeof(Utente));
    strcpy(utente->email, email);
    strcpy(utente->password, password);
    strcpy(utente->nome, nome);
    strcpy(utente->cognome, cognome);
    strcpy(utente->bar_nome, bar_nome);
    return utente;
}

char* barToJson(Bar* bar){

    char* result = malloc(64 * sizeof(char));

    sprintf(result, "{\"nome\":%s}", bar->nome);

    printf("%s\n", result);

    return result;
}

char* bevandaToJson(Bevanda* bevanda){
    
    char* result = malloc(256 * sizeof(char));

    sprintf(result, "{\"id\":%d, \"nome\":%s, \"prezzo\":%f, \"tipo\":%d, \"bar_nome\":%s}", bevanda->id, bevanda->nome, bevanda->prezzo, bevanda->tipo, bevanda->bar_nome);

    printf("%s\n", result);

    return result;
}

char* ingredienteToJson(Ingrediente* ingrediente){
    
    char* result = malloc(64 * sizeof(char));

    sprintf(result, "{\"nome\":%s}", ingrediente->nome);

    printf("%s\n", result);

    return result;
}

char* utenteToJson(Utente* utente){
        
    char* result = malloc(512 * sizeof(char));

    sprintf(result, "{\"email\":%s, \"password\":%s, \"nome\":%s, \"cognome\":%s, \"bar_nome\":%s}", utente->email, utente->password, utente->nome, utente->cognome, utente->bar_nome);

    printf("%s\n", result);

    return result;
}

