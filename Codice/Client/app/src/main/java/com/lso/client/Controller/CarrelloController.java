package com.lso.client.Controller;

import com.google.gson.Gson;

import org.modelmapper.ModelMapper;

public class CarrelloController {

    private ConnessioneController connessioneController;
    private ModelMapper modelMapper;
    private Gson gson;

    public CarrelloController(){
        connessioneController = new ConnessioneController();
        modelMapper = new ModelMapper();
        gson = new Gson();
    }




}
