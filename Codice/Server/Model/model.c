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

    sprintf(result, "{\"nome\":\"%s\"}", bar->nome);

    return result;
}

char* bevandaToJson(Bevanda* bevanda){
    
    char* result = malloc(256 * sizeof(char));

    sprintf(result, "{\"id\":%d, \"nome\":\"%s\", \"prezzo\":%f, \"tipo\":%d, \"bar_nome\":\"%s\"}", bevanda->id, bevanda->nome, bevanda->prezzo, bevanda->tipo, bevanda->bar_nome);

    return result;
}

char* ingredienteToJson(Ingrediente* ingrediente){
    
    char* result = malloc(64 * sizeof(char));

    sprintf(result, "{\"nome\":\"%s\"}", ingrediente->nome);

    return result;
}

char* utenteToJson(Utente* utente){
        
    char* result = malloc(512 * sizeof(char));

    sprintf(result, "{\"email\":\"%s\", \"password\":\"%s\", \"nome\":\"%s\", \"cognome\":\"%s\", \"bar_nome\":\"%s\"}", utente->email, utente->password, utente->nome, utente->cognome, utente->bar_nome);

    return result;
}



Bar* jsonToBar(char* json) {
    Bar* bar = malloc(sizeof(Bar));
    if (bar == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        return NULL;
    }
    
    // Creare una copia della stringa json
    char* jsonCopy = strdup(json);
    if (jsonCopy == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        free(bar);
        return NULL;
    }

    jsonCopy = deleteOccurrences(jsonCopy, '{');
    jsonCopy = deleteOccurrences(jsonCopy, '}');
    jsonCopy = deleteOccurrences(jsonCopy, '"');

    char* token = strtok(jsonCopy, ",");

    while (token != NULL) {
        char* key = strtok(token, ":");
        char* value = strtok(NULL, ":");

        if (strcmp(key, "nome") == 0) {
            strcpy(bar->nome, value);
        }
        token = strtok(NULL, ",");
    }

    free(jsonCopy);

    return bar;
}

Bevanda* jsonToBevanda(char* json) {
    Bevanda* bevanda = malloc(sizeof(Bevanda));
    if (bevanda == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        return NULL;
    }
    
    // Creare una copia della stringa json
    char* jsonCopy = strdup(json);
    if (jsonCopy == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        free(bevanda);
        return NULL;
    }

    jsonCopy = deleteOccurrences(jsonCopy, '{');
    jsonCopy = deleteOccurrences(jsonCopy, '}');
    jsonCopy = deleteOccurrences(jsonCopy, '"');
    jsonCopy = deleteOccurrences(jsonCopy, ' ');

    char* token = strtok(jsonCopy, ",");

    while (token != NULL) {

        char* tokenCopy = strdup(token);

        //strchr trova prima occorrenza di : in tokencopy e separa le due stringhe 
        int index = strchr(tokenCopy , ':') - tokenCopy;
        char* key = substring(tokenCopy, 0, index);
        char* value = substring(tokenCopy, index + 1, strlen(tokenCopy));

        if (strcmp(key, "id") == 0) {
            bevanda->id = atoi(value);
        } else if (strcmp(key, "nome") == 0) {
            strcpy(bevanda->nome, value);
        } else if (strcmp(key, "prezzo") == 0) {
            bevanda->prezzo = atof(value);
        } else if (strcmp(key, "tipo") == 0) {
            if(strcmp(value, "cocktail") == 0)
                bevanda->tipo = 0;
            else if(strcmp(value, "frullato") == 0)
                bevanda->tipo = 1;
            else
                bevanda->tipo = 0;
        } else if (strcmp(key, "bar_nome") == 0) {
            strcpy(bevanda->bar_nome, value);
        }
        token = strtok(NULL, ",");
    }

    free(jsonCopy);

    return bevanda;
}

Ingrediente* jsonToIngrediente(char* json) {
    Ingrediente* ingrediente = malloc(sizeof(Ingrediente));
    if (ingrediente == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        return NULL;
    }
    
    // Creare una copia della stringa json
    char* jsonCopy = strdup(json);
    if (jsonCopy == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        free(ingrediente);
        return NULL;
    }

    jsonCopy = deleteOccurrences(jsonCopy, '{');
    jsonCopy = deleteOccurrences(jsonCopy, '}');
    jsonCopy = deleteOccurrences(jsonCopy, '"');
    jsonCopy = deleteOccurrences(jsonCopy, ' ');

    char* token = strtok(jsonCopy, ",");

    while (token != NULL) {
        char* key = strtok(token, ":");
        char* value = strtok(NULL, ":");

        if (strcmp(key, "nome") == 0) {
            strcpy(ingrediente->nome, value);
        }
        token = strtok(NULL, ",");
    }

    free(jsonCopy);

    return ingrediente;
}

Utente* jsonToUtente(char* json){
    Utente* utente = malloc(sizeof(Utente));
    if (utente == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        return NULL;
    }
    
    // Creare una copia della stringa json
    char* jsonCopy = strdup(json);
    if (jsonCopy == NULL) {
        printf("Errore: malloc ha restituito un puntatore nullo.\n");
        free(utente);
        return NULL;
    }

    jsonCopy = deleteOccurrences(jsonCopy, '{');
    jsonCopy = deleteOccurrences(jsonCopy, '}');
    jsonCopy = deleteOccurrences(jsonCopy, '"');
    jsonCopy = deleteOccurrences(jsonCopy, ' ');

    char* token = strtok(jsonCopy, ",");

    while (token != NULL) {

        char* tokenCopy = strdup(token);

        int index = strchr(tokenCopy , ':') - tokenCopy;
        char* key = substring(tokenCopy, 0, index);
        char* value = substring(tokenCopy, index + 1, strlen(tokenCopy));

        if (strcmp(key, "email") == 0) {
            strcpy(utente->email, value);
        } else if (strcmp(key, "password") == 0) {
            strcpy(utente->password, value);
        } else if (strcmp(key, "nome") == 0) {
            strcpy(utente->nome, value);
        } else if (strcmp(key, "cognome") == 0) {
            strcpy(utente->cognome, value);
        } else if (strcmp(key, "bar_nome") == 0) {
            strcpy(utente->bar_nome, value);
        }
        token = strtok(NULL, ",");
    }

    free(jsonCopy);

    return utente;
}








