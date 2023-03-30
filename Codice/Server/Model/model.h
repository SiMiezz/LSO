#ifndef MODEL_H
#define MODEL_H

#include "../Utils/utils.h"

//ENUM
typedef enum Bevanda_Type {cocktail, frullato} Bevanda_Type;


//STRUCTS

//Bevanda
typedef struct Bevanda{
    int id;
    char nome[50];
    float prezzo;
    Bevanda_Type tipo;
} Bevanda;

//Ingrediente
typedef struct Ingrediente{
    char nome[50];
} Ingrediente;

//Utente
typedef struct Utente{
    char email[50];
    char password[50];
    char nome[50];
    char cognome[50];
} Utente;


Bevanda* creaBevanda(int id, char* nome, float prezzo, Bevanda_Type tipo);
Ingrediente* creaIngrediente(char* nome);
Utente* creaUtente(char* email, char* password, char* nome, char* cognome);


char* bevandaToJson(Bevanda* bevanda);
char* ingredienteToJson(Ingrediente* ingrediente);
char* utenteToJson(Utente* utente);


Bevanda* jsonToBevanda(char* json);
Ingrediente* jsonToIngrediente(char* json);
Utente* jsonToUtente(char* json);


#endif