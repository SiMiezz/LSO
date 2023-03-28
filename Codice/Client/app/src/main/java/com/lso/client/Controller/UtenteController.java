package com.lso.client.Controller;

import com.google.gson.Gson;
import com.lso.client.Model.Utente;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.IOException;

public class UtenteController {

    private ConnessioneController connessioneController;
    private ModelMapper modelMapper;
    private Gson gson;

    public UtenteController(){
        connessioneController = new ConnessioneController();
        modelMapper = new ModelMapper();
        gson = new Gson();
    }

    public Utente getUtenteByEmail(String email){

        Utente utente = null;
        String result = null;

        connessioneController.startConnection();

        connessioneController.writeOnOutput("getUtenteByEmail$$"+email);

        result = connessioneController.readFromInput();

        System.out.println(result);

        utente = jsonToUtente(result);

        connessioneController.closeConnection();

        return utente;

    }

    public Utente getUtenteByEmailAndPassword(String email, String password){

        System.out.println("inizio metodo");

        Utente utente = null;
        String result = null;

        connessioneController.startConnection();

        System.out.println("dopo connessione");

        //richiesta
        connessioneController.writeOnOutput("getUtenteByEmailAndPassword$$"+email+"$$"+password);

        System.out.println("dopo scrittura");

        //ricezione
        result = connessioneController.readFromInput();

        System.out.println(result);

        System.out.println("dopo lettura");

        // conversione da json ad oggetto
        utente = jsonToUtente(result);

        System.out.println("dopo conversione");

        connessioneController.closeConnection();

        System.out.println("dopo chiusura");

        return utente;
    }

    public void registraUtente(Utente utente){

        connessioneController.startConnection();

        //richiesta
        connessioneController.writeOnOutput("registraUtente$$"+utenteToJson(utente));

        connessioneController.closeConnection();

    }


    public String utenteToJson(Utente utente){
        String json = gson.toJson(utente);
        return json;
    }

    public Utente jsonToUtente(String json){
        Utente utente = gson.fromJson(json, Utente.class);
        return utente;
    }



}
