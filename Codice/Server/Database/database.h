#ifndef DATABASE_H
#define DATABASE_H

#include "../Model/model.h"
#include <stdio.h>
#include <stdbool.h>


Utente* getUtenteByEmailAndPassword(char* email, char* password);
Bevanda** getStoricoByUtenteAndBevandaType(Utente* utente, Bevanda_Type bevanda_type);
Ingrediente** getIngredientiByBevanda(Bevanda* bevanda);
Bevanda** getDisponibiliByBevandaType(Bevanda_Type bevanda_type);
Bevanda** getConsigliatiByBevandaTypeAndRecentiAndIngredienti(Bevanda_Type bevanda_type, bool recenti, Ingrediente** ingredienti);
void acquistaBevanda(Utente* utente, Bevanda* bevanda);
void registraUtente(Utente* utente);


#endif