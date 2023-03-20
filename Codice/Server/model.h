#ifndef MODEL_H
#define MODEL_H


//ENUM
typedef enum Bevanda_Type {cocktail, frullato} Bevanda_Type;


//STRUCTS
//Bar
typedef struct Bar{
    char nome[50];
} Bar; 

//Bevanda
typedef struct Bevanda{
    int id;
    char nome[50];
    float prezzo;
    Bevanda_Type tipo;
    char bar_nome[50];
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
    char bar_nome[50];
} Utente;

Bar* creaBar(char* nome);
Bevanda* creaBevanda(int id, char* nome, float prezzo, Bevanda_Type tipo, char* bar_nome);
Ingrediente* creaIngrediente(char* nome);
Utente* creaUtente(char* email, char* password, char* nome, char* cognome, char* bar_nome);

#endif