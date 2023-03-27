#include "server.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* handleRequest(char* request){

    printf("dentro handleRequest\n");

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
        printf("dentro getIngredientiByBevanda\n");
        return NULL;
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


void* threadManagement(void* arg){
    int new_socket = *((int*) arg);
    char buffer[1024] = {0};
    int valread = read(new_socket, buffer, 1024);
    buffer[valread] = '\0';
    char* request = buffer;

    fflush(stdout);
    printf("Ricevuto: %s\n", buffer);

    // Elabora la richiesta e restituisci il risultato
    fflush(stdout);
    printf("Elaborazione richiesta...\n");

    char* result = handleRequest(request);
    if(result != NULL){
        fflush(stdout);
        printf("Risultato: %s\n", result);
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
    printf("Connessione chiusa\n");

    pthread_exit(NULL);
}