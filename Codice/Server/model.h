#ifndef MODEL_H
#define MODEL_H


//ENUM
typedef enum tipo_bevanda {cocktail, frullato} tipo_bevanda;


//STRUCTS

//BAR
typedef struct bar{
    char nome[50];
} bar; 

//BEVANDA
typedef struct bevanda{
    int id;
    char nome[50];
    float prezzo;
    tipo_bevanda tipo;
    char bar_nome[50];
} bevanda;

//INGREDIENTE
typedef struct ingrediente{
    char nome[50];
} ingrediente;

//UTENTE
typedef struct utente{
    char email[50];
    char password[50];
    char nome[50];
    char cognome[50];
    char bar_nome[50];
} utente;

#endif