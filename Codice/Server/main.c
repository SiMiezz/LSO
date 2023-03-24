#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/socket.h>
#include <sys/types.h>
#include "Model/model.h"
#include "Database/database.h"


int main(int argc, char* argv[]){


    // Inizio prova creazione oggetti e serializzazione in JSON
    Bar* bar = creaBar("barLSO");
    barToJson(bar);
    printf("\n");

    Bevanda* bevanda = creaBevanda(1, "Gin Tonic", 1.0, cocktail, "barLSO");
    bevandaToJson(bevanda);
    printf("\n");

    Ingrediente* ingrediente = creaIngrediente("Gin");
    ingredienteToJson(ingrediente);
    printf("\n");

    Utente* utente = creaUtente("gianm@cacca.it", "caccamiao", "Gian Marco", "Addati", "barLSO");
    utenteToJson(utente);
    printf("\n");
    // Fine prova creazione oggetti e serializzazione in JSON

    printf("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");

    // Inizio prova deserializzazione in oggetti e stampa a video
    char* jsonBar = "{\"nome\":\"barLSO\"}";
    Bar* bar2 = jsonToBar(jsonBar);
    printf("\n");

    char* jsonBevanda = "{\"id\":1, \"nome\":\"Gin Tonic\", \"prezzo\":7, \"tipo\":cocktail, \"bar_nome\":\"barLSO\"}";
    Bevanda* bevanda2 = jsonToBevanda(jsonBevanda);
    printf("\n");

    char* jsonIngrediente = "{\"nome\":\"Gin\"}";
    Ingrediente* ingrediente2 = jsonToIngrediente(jsonIngrediente);
    printf("\n");

    char* jsonUtente = "{\"email\":\"gianm@cacca.it\", \"password\":\"caccamiao\", \"nome\":\"Gian Marco\", \"cognome\":\"Addati\", \"bar_nome\":\"barLSO\"}";
    Utente* utente2 = jsonToUtente(jsonUtente);
    printf("\n");
    // Fine prova deserializzazione in oggetti e stampa a video


    // Inizio prova connessione al database e stampa a video dei risultati
    getUtenteByEmailAndPassword("gi.addati","ok");
    getDisponibiliByBevandaType(frullato);
    // Fine prova connessione al database e stampa a video dei risultati

    return 0;   
    
}

