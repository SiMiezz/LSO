#! /bin/bash

gcc -o main main.c Database/database.c Model/model.c Utils/utils.c Server/server.c -I/usr/include/mysql -lmysqlclient

# Aggiungere i file .c da compilare 

# Per compilare digitare ./build.sh su terminale (`chmod +x build.sh` per rendere eseguibile)
# Per eseguire digitare ./main su terminale

