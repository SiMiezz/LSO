#include "server.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/socket.h>

void* creazioneThread(void* arg){
    int new_socket = *((int*) arg);
    char buffer[1024] = {0};
    int valread = read(new_socket, buffer, 1024);
    buffer[valread] = '\0';
    char* request = buffer;

    fflush(stdout);
    printf("Ricevuto: %s", buffer);

    // Elabora la richiesta e restituisci il risultato
    fflush(stdout);
    printf("Elaborazione richiesta...\n");

    char* result = estraiRichiesta(request);
    if(result != NULL){
        fflush(stdout);
        printf("Risultato: %s\n\n", result);
    }

    // Invia il risultato al client 
    if(result != NULL){
        fflush(stdout);
        send(new_socket, result, strlen(result), 0);
    }

    // Chiudi la connessione
    bzero(buffer, 1024);
    close(new_socket);
    fflush(stdout);
    printf("Connessione chiusa\n\n\n");

    pthread_exit(NULL);
}

char* estraiRichiesta(char* request){

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

    return discriminaRichiesta(method, path);

}

char* discriminaRichiesta(char* method, char* path){
    // Discrimina la richiesta in base al metodo
    if(strcmp(method, "getUtenteByEmail") == 0){
        // Estraggo il parametro dalla richiesta
        char* email = path;
        char* json = NULL;

        // Chiamo la funzione del database, converto l'oggetto in JSON e lo restituisco
        Utente* utente = getUtenteByEmail(email);
        if(utente != NULL)
            json = utenteToJson(utente);
        return json;
    } 
    else if(strcmp(method, "getUtenteByEmailAndPassword") == 0){
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
        // Estraggo i due parametri dalla richiesta
        char* jsonUtente = strtok(path, "$$");
        char* bevandaType = path + strlen(jsonUtente) + 2;
        char* json = NULL;

        // Converto la stringa in un oggetto Utente
        Utente* utente = jsonToUtente(jsonUtente);

        // Converto la stringa in un bevandatype e la passo alla funzione
        Bevanda_Type tipo;
        if(strcmp(bevandaType, "cocktail") == 0)
            tipo = 0;
        else if(strcmp(bevandaType, "frullato") == 0)
            tipo = 1;

        // Chiamo la funzione del database, converto l'oggetto in JSON e lo restituisco
        Bevanda** bevande = getStoricoByUtenteAndBevandaType(utente, tipo);

        if(bevande == NULL){
            return NULL;
        }

        // Per ogni bevanda, converto l'oggetto in JSON e lo concateno alla stringa json
        for(int i = 0; bevande[i] != NULL; i++){
            char* jsonBevanda = bevandaToJson(bevande[i]);
            if(json == NULL){
                json = malloc(strlen(jsonBevanda) + 1);
                strcpy(json, jsonBevanda);
            }
            else {
                json = realloc(json, strlen(json) + strlen(jsonBevanda) + 1);
                strcat(json, jsonBevanda);
            }
        }

        return json;

    } 
    else if(strcmp(method, "getIngredientiByBevanda") == 0){
        // Estraggo il parametro dalla richiesta
        char* jsonBevanda = path;
        char* json = NULL;

        // Converto la stringa in un oggetto Bevanda
        Bevanda* bevanda = jsonToBevanda(jsonBevanda);

        // Chiamo la funzione del database, converto l'oggetto in JSON e lo restituisco
        Ingrediente** ingredienti = getIngredientiByBevanda(bevanda);

        if(ingredienti == NULL){
            return NULL;
        }

        // Per ogni ingrediente, converto l'oggetto in JSON e lo concateno alla stringa json
        for(int i = 0; ingredienti[i] != NULL; i++){
            char* jsonIngrediente = ingredienteToJson(ingredienti[i]);
            if(json == NULL){
                json = malloc(strlen(jsonIngrediente) + 1);
                strcpy(json, jsonIngrediente);
            }
            else {
                json = realloc(json, strlen(json) + strlen(jsonIngrediente) + 1);
                strcat(json, jsonIngrediente);
            }
        }
        
        return json;
    }
    else if(strcmp(method, "getDisponibiliByBevandaType") == 0){
        // Estraggo il parametro dalla richiesta
        char* bevandaType = path;
        char* json = NULL;

        printf("BevandaType: %s\n", bevandaType);

        // Converto la stringa in un bevandatype e la passo alla funzione
        Bevanda_Type tipo;
        if(strcmp(bevandaType, "cocktail") == 0)
            tipo = 0;
        else if(strcmp(bevandaType, "frullato") == 0)
            tipo = 1;

        // Chiamo la funzione del database, converto l'oggetto in JSON e lo restituisco
        Bevanda** bevande = getDisponibiliByBevandaType(tipo);

        // Per ogni bevanda, converto l'oggetto in JSON e lo concateno alla stringa json
        for(int i = 0; bevande[i] != NULL; i++){
            char* jsonBevanda = bevandaToJson(bevande[i]);
            if(json == NULL){
                json = malloc(strlen(jsonBevanda) + 1);
                strcpy(json, jsonBevanda);
            }
            else {
                json = realloc(json, strlen(json) + strlen(jsonBevanda) + 1);
                strcat(json, jsonBevanda);
            }
        }
        
        return json;
    }
    else if(strcmp(method, "getConsigliatiByBevandaTypeAndRecentiAndIngredienti") == 0){
        
    }
    else if(strcmp(method, "acquistaBevanda") == 0){
        // Estraggo i due parametri della richiesta
        char* jsonUtente = strtok(path, "$$");
        char* jsonBevanda = path + strlen(jsonUtente) + 2;
        char* json = NULL;

        // Converto le stringhe in oggetti Utente e Bevanda
        Utente* utente = jsonToUtente(jsonUtente);
        Bevanda* bevanda = jsonToBevanda(jsonBevanda);

        // Chiamo la funzione del database
        acquistaBevanda(utente, bevanda);

        return "Bevanda acquistata";
    }
    else if(strcmp(method, "registraUtente") == 0){
        // Estraggo il parametro dalla richiesta
        char* jsonUtente = path;

        // Converto la stringa in un oggetto Utente
        Utente* utente = jsonToUtente(jsonUtente);

        // Chiamo la funzione del database
        registraUtente(utente);

        return "Utente registrato";
    }
    else {
        return "Metodo non supportato";
    }
}
