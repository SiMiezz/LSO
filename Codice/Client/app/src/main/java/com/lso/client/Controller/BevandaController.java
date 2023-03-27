package com.lso.client.Controller;

import com.google.gson.Gson;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Enum.Bevanda_Type;
import com.lso.client.Model.Ingrediente;
import com.lso.client.Model.Utente;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BevandaController {

    private ConnessioneController connessioneController;
    private ModelMapper modelMapper;
    private Gson gson;

    public BevandaController() {
        connessioneController = new ConnessioneController();
        modelMapper = new ModelMapper();
        gson = new Gson();
    }

    public List<Bevanda> getStoricoByUtenteAndBevandaType(Utente utente, Bevanda_Type bevanda_type){
        List<Bevanda> bevande = new ArrayList<>();
        // to do
        return bevande;
    }

    public ArrayList<Bevanda> getDisponibiliByBevandaType(Bevanda_Type bevanda_type){



        ArrayList<Bevanda> bevande = null;
        String result = null;

        connessioneController.startConnection();

        connessioneController.writeOnOutput("getDisponibiliByBevandaType$$"+bevanda_type.toString());

        result = connessioneController.readFromInput();

        System.out.println(result);

        if(result != null){
            result = result.replaceAll("\\}\\s*\\{", "},{");

            result = "[" + result + "]";

            Bevanda[] bevandeArray = gson.fromJson(result, Bevanda[].class);

            // Fai qualcosa con l'array di oggetti Bevanda
            for (Bevanda bevanda : bevandeArray)
                System.out.println(bevanda.getNome());
        }


        // conversione

        connessioneController.closeConnection();

        return bevande;

    }

    public List<Bevanda> getConsigliatiByBevandaTypeAndRecentiAndIngredienti(Bevanda_Type bevanda_type, boolean recenti, List<Ingrediente> ingredienti){
        List<Bevanda> bevande = new ArrayList<>();
        // to do
        return bevande;
    }

    public void acquistaBevanda(Utente utente, Bevanda bevanda){

    }

    public String bevandaToJson(Bevanda bevanda){
        String json = gson.toJson(bevanda);
        return json;
    }

    public Bevanda jsonToBevanda(String json){
        Bevanda bevanda = gson.fromJson(json, Bevanda.class);
        return bevanda;
    }

}
