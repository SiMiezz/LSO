#include "utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* substring(char* string, int start, int end){
    char* result = malloc((end - start + 1) * sizeof(char));
    int i = 0;
    for(i = start; i < end; i++){
        result[i - start] = string[i];
    }
    result[i - start] = '\0';
    return result;
}

char* deleteOccurrences(char* string, char toDelete){
    int i = 0;
    int j = 0;
    int len = strlen(string);
    char* result = malloc(len * sizeof(char));
    for(i = 0; i < len; i++){
        if(string[i] != toDelete){
            result[j] = string[i];
            j++;
        }
    }
    result[j] = '\0';
    return result;
}