package com.lso.client.Controller;

import com.google.gson.Gson;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Carrello;
import com.lso.client.Model.Utente;
import com.lso.client.View.Adapter.CarrelloAdapter;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;

public class CarrelloController {

    private ConnessioneController connessioneController;
    private ModelMapper modelMapper;
    private Gson gson;

    public CarrelloController(){
        connessioneController = new ConnessioneController();
        modelMapper = new ModelMapper();
        gson = new Gson();
    }

    public Carrello getCarrelloByUtente(Utente utente){
        Carrello carrello = null;

        connessioneController.startConnection();

        // to do

        connessioneController.closeConnection();

        return carrello;
    }

    public ArrayList<Bevanda> getBevandeByCarrello(Carrello carrello){
        ArrayList<Bevanda> bevandaArrayList = null;

        connessioneController.startConnection();

        // to do

        connessioneController.closeConnection();

        return bevandaArrayList;
    }


}
