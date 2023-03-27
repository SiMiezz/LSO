#ifndef SERVER_H
#define SERVER_H

#include "../Database/database.h"

void* creazioneThread(void* arg);
char* estraiRichiesta(char* request);
char* discriminaRichiesta(char* method, char* path);

#endif