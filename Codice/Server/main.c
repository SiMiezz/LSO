#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/socket.h>
#include "Model/model.h"
#include "Database/database.h"


int main(int argc, char* argv[]){

    Bar* bar = creaBar("Bar");
    barToJson(bar);

    Bevanda* bevanda = creaBevanda(1, "Bevanda", 1.0, frullato, "Bar");
    bevandaToJson(bevanda);

    Ingrediente* ingrediente = creaIngrediente("Ingrediente");
    ingredienteToJson(ingrediente);

    Utente* utente = creaUtente("email", "password", "nome", "cognome", "Bar");
    utenteToJson(utente);

    return 0;   
    
}

