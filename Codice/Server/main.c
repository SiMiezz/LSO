#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <pthread.h>
#include <unistd.h>
#include "Model/model.h"
#include "Database/database.h"
#include "Server/server.h"


int main(int argc, char* argv[]){

    // Socket
    int server_fd, new_socket, valread;
    struct sockaddr_in address;
    int addrlen = sizeof(address);
    char buffer[1024] = {0};

    // Crea la socket del server
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Imposta le opzioni della socket del server
    int opt = 1;
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) {
        perror("setsockopt failed");
        exit(EXIT_FAILURE);
    }

    // Imposta l'indirizzo e la porta del server
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(1926);

    // Collega la socket del server all'indirizzo e alla porta specificati
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("bind failed");
        exit(EXIT_FAILURE);
    } else {
        fflush(stdout);
        printf("Collegata alla porta 1926\n");
    }

    // Metti in ascolto la socket del server per le connessioni in ingresso
    if (listen(server_fd, SOMAXCONN) < 0) {
        perror("listen failed");
        exit(EXIT_FAILURE);
    } else {
        printf("In ascolto sulla porta 1926\n");
    }

    // Ciclo infinito per gestire le connessioni in ingresso 
    while (1) {
        sleep(1);
        printf("In attesa di una connessione...\n\n\n");


        bzero(&address, sizeof(address));
        bzero(buffer, 1024);

        // Accetta una connessione in ingresso dalla socket del server
        if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen)) < 0) {
            perror("accept failed");
            exit(EXIT_FAILURE);
        } else {
            printf("Connessione accettata\n\n");
        }

        // Crea un thread per gestire la richiesta del client
        pthread_t thread;
        int* arg = malloc(sizeof(int));
        *arg = new_socket;
        pthread_create(&thread, NULL, creazioneThread, arg);
        pthread_detach(thread);
    }

    return 0;   
    
}