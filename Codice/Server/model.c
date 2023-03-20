#include "model.h"
#include <string.h>


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