#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/socket.h>
#include "Model/model.h"
#include "Database/database.h"


int main(int argc, char* argv[]){



    // Inizio prova creazione oggetti e serializzazione in JSON

    Bar* bar = creaBar("Bar");
    barToJson(bar);
    printf("\n");

    Bevanda* bevanda = creaBevanda(1, "Bevanda", 1.0, frullato, "Bar");
    bevandaToJson(bevanda);
    printf("\n");

    Ingrediente* ingrediente = creaIngrediente("Ingrediente");
    ingredienteToJson(ingrediente);
    printf("\n");

    Utente* utente = creaUtente("email", "password", "nome", "cognome", "Bar");
    utenteToJson(utente);
    printf("\n");

    // Fine prova creazione oggetti e serializzazione in JSON




    // Inizio prova deserializzazione in oggetti e stampa a video
    char* jsonBar = "{\"nome\":\"Bar\"}";
    Bar* bar2 = jsonToBar(jsonBar);
    printf("\n");

    char* jsonBevanda = "{\"id\":2, \"nome\":\"cccevanda\", \"prezzo\":5.99, \"tipo\":frullato, \"bar_nome\":\"car\"}";
    Bevanda* bevanda2 = jsonToBevanda(jsonBevanda);
    printf("\n");

    char* jsonIngrediente = "{\"nome\":\"Ingrediente\"}";
    Ingrediente* ingrediente2 = jsonToIngrediente(jsonIngrediente);
    printf("\n");

    char* jsonUtente = "{\"email\":\"email\", \"password\":\"password\", \"nome\":\"nome\", \"cognome\":\"cognome\", \"bar_nome\":\"Bar\"}";
    Utente* utente2 = jsonToUtente(jsonUtente);
    printf("\n");

    // Fine prova deserializzazione in oggetti e stampa a video


    return 0;   
    
}

