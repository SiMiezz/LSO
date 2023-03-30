#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "model.h"


Bevanda* creaBevanda(int id, char* nome, float prezzo, Bevanda_Type tipo){
    Bevanda* bevanda = malloc(sizeof(Bevanda));
    bevanda->id = id;
    strcpy(bevanda->nome, nome);
    bevanda->prezzo = prezzo;
    bevanda->tipo = tipo;
    return bevanda;
}

Ingrediente* creaIngrediente(char* nome){
    Ingrediente* ingrediente = malloc(sizeof(Ingrediente));
    strcpy(ingrediente->nome, nome);
    return ingrediente;
}

Utente* creaUtente(char* email, char* password, char* nome, char* cognome){
    Utente* utente = malloc(sizeof(Utente));
    strcpy(utente->email, email);
    strcpy(utente->password, password);
    strcpy(utente->nome, nome);
    strcpy(utente->cognome, cognome);
    return utente;
}



char* bevandaToJson(Bevanda* bevanda){
    
    char* result = malloc(256 * sizeof(char));

    sprintf(result, "{\"id\":%d, \"nome\":\"%s\", \"prezzo\":%f, \"tipo\":%d}", bevanda->id, bevanda->nome, bevanda->prezzo, bevanda->tipo);

    return result;
}

char* ingredienteToJson(Ingrediente* ingrediente){
    
    char* result = malloc(64 * sizeof(char));

    sprintf(result, "{\"nome\":\"%s\"}", ingrediente->nome);

    return result;
}

char* utenteToJson(Utente* utente){
        
    char* result = malloc(512 * sizeof(char));

    sprintf(result, "{\"email\":\"%s\", \"password\":\"%s\", \"nome\":\"%s\", \"cognome\":\"%s\"}", utente->email, utente->password, utente->nome, utente->cognome);

    return result;
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
        }
        token = strtok(NULL, ",");
    }

    free(jsonCopy);

    return utente;
}


Ingrediente** jsonMultipliToIngredienti(char* jsonIngredienti){

    int numIngredienti = 0;
    for (int i = 0; jsonIngredienti[i] != '\0'; i++) {
        if (jsonIngredienti[i] == '{') {
            numIngredienti++;
        }
    }

    Ingrediente** ingredienti = malloc(sizeof(Ingrediente*) * (numIngredienti + 1));

    const char* start = jsonIngredienti;
    const char* end = jsonIngredienti;
    int i = 0;
    while (*end != '\0') {
        if (*end == '{') {
            start = end;
        } else if (*end == '}') {
            // crea una sottostringa contenente l'oggetto JSON
            int len = (int)(end - start) + 1;
            char* jsonString = malloc(len + 1);
            strncpy(jsonString, start, len);
            jsonString[len] = '\0';

            // converte la stringa JSON in un oggetto Ingrediente
            Ingrediente* ingrediente = jsonToIngrediente(jsonString);
            ingredienti[i++] = ingrediente;

            // passa al prossimo oggetto JSON
            start = end + 1;
        }
        end++;
    }

    ingredienti[i] = NULL;

    return ingredienti;
}








