#include "server.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* handleRequest(char* request){

    // Rimuovo il carattere di fine stringa
    request[strlen(request) - 1] = '\0';

    // Copia la stringa per non modificare quella passata come parametro
    char* requestCopy = malloc(strlen(request) + 1);
    strcpy(requestCopy, request);

    // Estrae il metodo dalla richiesta e il path (stringa request - stringa method)
    char* method = strtok(requestCopy, "$$");
    char* path = request + strlen(method) + 2;

    printf("Metodo: %s\n", method);
    printf("Path: %s\n", path);

    // Discrimina la richiesta in base al metodo
    if(strcmp(method, "getUtenteByEmailAndPassword") == 0){
        // Estraggo i due parametri dalla richiesta
        char* email = strtok(path, "$$");
        char* password = path + strlen(email) + 2;
        char* json = NULL;

        // Chiamo la funzione del database, converto l'oggetto in JSON e lo restituisco
        Utente* utente = getUtenteByEmailAndPassword(email, password);
        if(utente != NULL)
            json = utenteToJson(utente);
        return json;
    } 
    else if(strcmp(method, "getStoricoByUtenteAndBevandaType") == 0){
        
    } 
    else if(strcmp(method, "getIngredientiByBevanda") == 0){
        printf("muiao\n");
        // Estraggo il parametro dalla richiesta
        printf("path: %s\n", path);
        return path;
    }
    else if(strcmp(method, "getDisponibiliByBevandaType") == 0){
        
    }
    else if(strcmp(method, "getConsigliatiByBevandaTypeAndRecentiAndIngredienti") == 0){
        
    }
    else if(strcmp(method, "acquistaBevanda") == 0){
        
    }
    else {
        return "Metodo non supportato";
    }

}