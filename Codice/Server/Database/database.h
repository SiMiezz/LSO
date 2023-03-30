#ifndef DATABASE_H
#define DATABASE_H

#include "../Model/model.h"
#include <stdio.h>
#include <stdbool.h>


Utente* getUtenteByEmail(char* email);
Utente* getUtenteByEmailAndPassword(char* email, char* password);
Bevanda** getStoricoByUtenteAndBevandaType(Utente* utente, Bevanda_Type bevanda_type);
Ingrediente** getAllIngredienti();
Ingrediente** getIngredientiByBevanda(Bevanda* bevanda);
Bevanda** getDisponibiliByBevandaType(Bevanda_Type bevanda_type);
Bevanda** getConsigliatiByUtenteAndBevandaTypeAndRecentiAndIngredienti(Utente* utente, Bevanda_Type bevanda_type, bool recenti, Ingrediente** ingredienti);
void aggiungiACarrello(Utente* utente, Bevanda* bevanda);
void acquistaBevanda(Utente* utente, Bevanda* bevanda);
void registraUtente(Utente* utente);
void cambiaPasswordUtente(Utente* utente, char* nuovaPassword);


#endif