package com.lso.client.Controller;

import com.google.gson.Gson;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Ingrediente;
import com.lso.client.Model.Utente;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class IngredienteController {

    private ConnessioneController connessioneController;
    private ModelMapper modelMapper;
    private Gson gson;

    public IngredienteController() {
        connessioneController = new ConnessioneController();
        modelMapper = new ModelMapper();
        gson = new Gson();
    }

    public List<Ingrediente> getIngredientiByBevanda(Bevanda bevanda){
        List<Ingrediente> ingredienti = new ArrayList<>();
        // to do
        return ingredienti;
    }

    public String ingredienteToJson(Ingrediente ingrediente){
        String json = gson.toJson(ingrediente);
        return json;
    }

    public Ingrediente jsonToIngrediente(String json){
        Ingrediente ingrediente = modelMapper.map(json, Ingrediente.class);
        return ingrediente;
    }

}
