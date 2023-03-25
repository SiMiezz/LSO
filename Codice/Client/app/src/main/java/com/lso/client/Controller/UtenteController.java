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

    public Utente getUtenteByEmailAndPassword(String email, String password){
        Utente utente = null;
        String result = null;

        connessioneController.startConnection();

        //richiesta
        connessioneController.writeOnOutput("getUtenteByEmailAndPassword");
        connessioneController.writeOnOutput(email);
        connessioneController.writeOnOutput(password);

        //ricezione
        result = connessioneController.readFromInput();

        // conversione da json ad oggetto
        utente = jsonToUtente(result);

        connessioneController.closeConnection();

        return utente;
    }


    public String utenteToJson(Utente utente){
        String json = gson.toJson(utente);
        return json;
    }

    public Utente jsonToUtente(String json){
        Utente utente = modelMapper.map(json, Utente.class);
        return utente;
    }



}
