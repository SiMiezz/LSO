package com.lso.client.Controller;

import com.google.gson.Gson;
import com.lso.client.Model.Bar;
import com.lso.client.Model.Utente;

import org.modelmapper.ModelMapper;

public class BarController {

    private ConnessioneController connessioneController;
    private ModelMapper modelMapper;
    private Gson gson;

    public BarController() {
        connessioneController = new ConnessioneController();
        modelMapper = new ModelMapper();
        gson = new Gson();
    }

    public String barToJson(Bar bar){
        String json = gson.toJson(bar);
        return json;
    }

    public Bar jsonToBar(String json){
        Bar bar = gson.fromJson(json, Bar.class);
        return bar;
    }
}
