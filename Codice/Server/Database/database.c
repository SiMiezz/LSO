#include <stdio.h>
#include <stdbool.h>
#include "database.h"
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

Utente* getUtenteByEmail(char* email){

    Utente* utente;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "SELECT * FROM utente WHERE email = '%s'", email);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    //Controllo se l'utente esiste
    if(mysql_num_rows(result) == 0){
        printf("Utente non trovato\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    utente = malloc(sizeof(Utente));
    printf("Utente trovato\n");

    // Ciclo sui risultati e stampa dei valori delle colonne
    while ((row = mysql_fetch_row(result)) != NULL) {
        strcpy(utente->email, row[0]);
        strcpy(utente->password, row[1]);
        strcpy(utente->nome, row[2]);
        strcpy(utente->cognome, row[3]);
        strcpy(utente->bar_nome, row[4]);
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return utente;



}

Utente* getUtenteByEmailAndPassword(char* email, char* password){

    Utente* utente;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "SELECT * FROM utente WHERE email = '%s' AND password = '%s'", email, password);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    //Controllo se l'utente esiste
    if(mysql_num_rows(result) == 0){
        printf("Utente non trovato\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    utente = malloc(sizeof(Utente));
    printf("Utente trovato\n");
    
    // Ciclo sui risultati e stampa dei valori delle colonne
    while ((row = mysql_fetch_row(result)) != NULL) {
        strcpy(utente->email, row[0]);
        strcpy(utente->password, row[1]);
        strcpy(utente->nome, row[2]);
        strcpy(utente->cognome, row[3]);
        strcpy(utente->bar_nome, row[4]);
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return utente;
}

Bevanda** getStoricoByUtenteAndBevandaType(Utente* utente, Bevanda_Type tipo){

    Bevanda** bevande;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Nel DB le enumeration partono da 1
    tipo++;

    // Creazione Query 
    char query[1024];
    sprintf(query, "SELECT b.id,b.nome,b.prezzo,b.tipo,b.bar_nome FROM bevanda AS b JOIN acquisto AS a ON b.id=a.bevanda_id WHERE a.utente_email=\"%s\" AND b.tipo=%d", utente->email, tipo);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    // Numero di righe
    int num_rows = mysql_num_rows(result);

    //Controllo se le bevande esistono
    if(num_rows == 0){
        printf("Bevande non trovate\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    printf("Bevande trovate\n");
    bevande = malloc((num_rows + 1) * sizeof(Bevanda*));

    // Setto l'ultimo elemento a NULL (nodo sentinella)
    bevande[num_rows] = NULL;

    // Ciclo sui risultati e stampa dei valori delle colonne
    int i = 0;
    while ((row = mysql_fetch_row(result)) != NULL) {
        bevande[i] = malloc(sizeof(Bevanda));

        bevande[i]->id = atoi(row[0]);
        strcpy(bevande[i]->nome, row[1]);
        bevande[i]->prezzo = atof(row[2]);
        if(strcmp(row[3], "cocktail") == 0)
            bevande[i]->tipo = 0;
        else if(strcmp(row[3], "frullato") == 0)
            bevande[i]->tipo = 1;
        strcpy(bevande[i]->bar_nome, row[4]);

        i++;
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return bevande;
}

Ingrediente** getAllIngredienti(){
    Ingrediente** ingredienti;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)){
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "SELECT * FROM ingrediente");
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    // Numero di righe
    int num_rows = mysql_num_rows(result);

    //Controllo se gli ingredienti esistono
    if(num_rows == 0){
        printf("Ingredienti non trovati\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    printf("Ingredienti trovati\n");
    ingredienti = malloc((num_rows + 1) * sizeof(Ingrediente*));

    // Setto l'ultimo elemento a NULL (nodo sentinella)
    ingredienti[num_rows] = NULL;

    // Ciclo sui risultati e stampa dei valori delle colonne
    int i = 0;
    while ((row = mysql_fetch_row(result)) != NULL) {
        ingredienti[i] = malloc(sizeof(Ingrediente));

        strcpy(ingredienti[i]->nome, row[0]);

        i++;
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return ingredienti;
}

Ingrediente** getIngredientiByBevanda(Bevanda* bevanda){
    Ingrediente** ingredienti;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "SELECT ingrediente_nome FROM contiene WHERE bevanda_id=%d", bevanda->id);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    // Numero di righe
    int num_rows = mysql_num_rows(result);

    //Controllo se gli ingredienti esistono
    if(num_rows == 0){
        printf("Ingredienti non trovati\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    printf("Ingredienti trovati\n");
    ingredienti = malloc((num_rows + 1) * sizeof(Ingrediente*));

    // Setto l'ultimo elemento a NULL (nodo sentinella)
    ingredienti[num_rows] = NULL;

    // Ciclo sui risultati e stampa dei valori delle colonne
    int i = 0;
    while ((row = mysql_fetch_row(result)) != NULL) {
        ingredienti[i] = malloc(sizeof(Ingrediente));

        strcpy(ingredienti[i]->nome, row[0]);

        i++;
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return ingredienti;
}

Bevanda** getDisponibiliByBevandaType(Bevanda_Type tipo){

    Bevanda** bevande;

    // Nel DB le enumeration partono da 1
    tipo++;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "SELECT * FROM bevanda WHERE tipo = %d", tipo);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    // Numero di righe
    int num_rows = mysql_num_rows(result);

    //Controllo se le bevande esistono
    if(num_rows == 0){
        printf("Bevande non trovate\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    printf("Bevande trovate\n");
    bevande = malloc((num_rows + 1) * sizeof(Bevanda*));

    // Setto l'ultimo elemento a NULL (nodo sentinella)
    bevande[num_rows] = NULL;

    // Ciclo sui risultati e stampa dei valori delle colonne
    int i = 0;
    while ((row = mysql_fetch_row(result)) != NULL) {
        bevande[i] = malloc(sizeof(Bevanda));

        bevande[i]->id = atoi(row[0]);
        strcpy(bevande[i]->nome, row[1]);
        bevande[i]->prezzo = atof(row[2]);
        if(strcmp(row[3], "cocktail") == 0)
            bevande[i]->tipo = 0;
        else if(strcmp(row[3], "frullato") == 0)
            bevande[i]->tipo = 1;
        strcpy(bevande[i]->bar_nome, row[4]);

        i++;
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return bevande;
}

Bevanda** getConsigliatiByUtenteAndBevandaTypeAndRecentiAndIngredienti(Utente* utente, Bevanda_Type tipo, bool recenti, Ingrediente** ingredienti){
    Bevanda** bevande;

    // Nel DB le enumeration partono da 1
    tipo++;

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)){
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[8192];
    // Creo la stringa degli ingredienti
    char ingredienti_string[1024];
    ingredienti_string[0] = '\0';
    int i = 0;
    printf("miao\n");
    
    if(ingredienti != NULL){
        while(ingredienti[i] != NULL){
        strcat(ingredienti_string, "'");
        strcat(ingredienti_string, ingredienti[i]->nome);
        strcat(ingredienti_string, "'");
        if(ingredienti[i+1] != NULL)
            strcat(ingredienti_string, ", ");
        i++;
        }
    }

    if(i == 0)
        ingredienti_string[0] = '\0';

    printf("%s\n", ingredienti_string);

    if(recenti){
        // Creo la query
        char query1[2048];
        char query2[2048];

        if (ingredienti_string[0] == '\0'){
            sprintf(query1, "(SELECT DISTINCT b.* FROM bevanda AS b JOIN acquisto AS a ON b.id=a.bevanda_id WHERE a.utente_email=\"%s\" AND b.tipo = %d)", utente->email, tipo);
            sprintf(query2, "(SELECT b.* FROM bevanda AS b WHERE tipo=%d AND id NOT IN (SELECT bevanda_id FROM acquisto AS a WHERE a.utente_email=\"%s\"))", tipo, utente->email);
            sprintf(query, "%s UNION %s", query1, query2);
        }
        else{
            sprintf(query1, "(SELECT DISTINCT b.* FROM bevanda AS b JOIN acquisto AS a ON b.id=a.bevanda_id WHERE a.utente_email=\"%s\" AND b.tipo = %d AND b.id IN (SELECT bevanda_id FROM contiene WHERE ingrediente_nome IN (%s)))", utente->email, tipo, ingredienti_string);
            sprintf(query2, "(SELECT b.* FROM bevanda AS b WHERE tipo=%d AND id IN ((SELECT bevanda_id FROM contiene WHERE ingrediente_nome IN (%s))) AND id NOT IN (SELECT bevanda_id FROM acquisto AS a WHERE a.utente_email=\"%s\"))", tipo, ingredienti_string, utente->email);
            sprintf(query, "%s UNION %s", query1, query2);
        }
            
    } else {
        // Creo la query
        if(ingredienti_string[0] == '\0')
            sprintf(query, "SELECT * FROM bevanda WHERE tipo = %d", tipo);
        else
            sprintf(query, "SELECT * FROM bevanda WHERE tipo = %d AND id IN (SELECT bevanda_id FROM contiene WHERE ingrediente_nome IN (%s))", tipo, ingredienti_string);
    }

    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Ottenimento dei risultati della query
    result = mysql_store_result(conn);

    // Numero di righe
    int num_rows = mysql_num_rows(result);

    //Controllo se le bevande esistono
    if(num_rows == 0){
        printf("Bevande non trovate\n");
        mysql_free_result(result);
        mysql_close(conn);
        return NULL;
    }

    printf("Bevande trovate\n");
    bevande = malloc((num_rows + 1) * sizeof(Bevanda*));

    // Setto l'ultimo elemento a NULL (nodo sentinella)
    bevande[num_rows] = NULL;

    // Ciclo sui risultati e stampa dei valori delle colonne
    int j = 0;
    while ((row = mysql_fetch_row(result)) != NULL) {
        bevande[j] = malloc(sizeof(Bevanda));

        bevande[j]->id = atoi(row[0]);
        strcpy(bevande[j]->nome, row[1]);
        bevande[j]->prezzo = atof(row[2]);
        if(strcmp(row[3], "cocktail") == 0)
            bevande[j]->tipo = 0;
        else if(strcmp(row[3], "frullato") == 0)
            bevande[j]->tipo = 1;
        strcpy(bevande[j]->bar_nome, row[4]);

        j++;
    }

    // Liberazione della memoria
    mysql_free_result(result);
    mysql_close(conn);

    return bevande;
}

void acquistaBevanda(Utente* utente, Bevanda* bevanda){

    MYSQL *conn;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "INSERT INTO acquisto (utente_email, bevanda_id) VALUES ('%s', %d)", utente->email, bevanda->id);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Liberazione della memoria
    mysql_close(conn);

    printf("Bevanda acquistata\n");
}

void registraUtente(Utente* utente){

    MYSQL *conn;
    MYSQL_ROW row;

    // Connessione al database
    conn = mysql_init(NULL);
    if (!mysql_real_connect(conn, "localhost", "root", "password", "bar_lso", 0, NULL, 0)){
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Creazione Query
    char query[1024];
    sprintf(query, "INSERT INTO utente (email, password, nome, cognome, bar_nome) VALUES ('%s', '%s', '%s', '%s', '%s')", utente->email, utente->password, utente->nome, utente->cognome, utente->bar_nome);
    printf("%s\n", query);

    // Esecuzione di una query
    if (mysql_query(conn, query)) {
        fprintf(stderr, "%s\n", mysql_error(conn));
        exit(1);
    }

    // Liberazione della memoria
    mysql_close(conn);

    printf("Utente registrato\n");

}