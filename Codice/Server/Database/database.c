#include <stdio.h>
#include <stdbool.h>
#include "database.h"


Utente* getUtenteByEmailAndPassword(char* email, char* password){
    Utente* utente;

    // to do

    //QUERY
    char query[1024];
    sprintf(query, "SELECT * FROM utente WHERE email = '%s' AND password = '%s'", email, password);

    // to do

    return utente;
}

Bevanda** getStoricoByUtenteAndBevandaType(Utente* utente, Bevanda_Type tipo){
    Bevanda** bevande;

    // todo

    return bevande;
}

Ingrediente** getIngredientiByBevanda(Bevanda* bevanda){
    Ingrediente** ingredienti;

    // todo

    return ingredienti;
}

Bevanda** getDisponibiliByBevandaType(Bevanda_Type tipo){
    Bevanda** bevande;

    // todo

    return bevande;
}

Bevanda** getConsigliatiByBevandaTypeAndRecentiAndIngredienti(Bevanda_Type tipo, bool recenti, Ingrediente** ingredienti){
    Bevanda** bevande;

    // todo

    return bevande;
}

void acquistaBevanda(Utente* utente, Bevanda* bevanda){
    // todo
}