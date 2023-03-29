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

    public ArrayList<Ingrediente> getIngredientiByBevanda(Bevanda bevanda){
        ArrayList<Ingrediente> ingredienti = new ArrayList<>();
        String result = null;

        connessioneController.startConnection();

        //richiesta
        connessioneController.writeOnOutput("getIngredientiByBevanda$$"+bevandaToJson(bevanda));

        //ricezione
        result = connessioneController.readFromInput();

        System.out.println(result);

        // Separo i JSON e creo un array che riverserò nell'arraylist
        if(result != null){
            result = result.replaceAll("\\}\\s*\\{", "},{");

            result = "[" + result + "]";

            Ingrediente[] ingredienteArray = gson.fromJson(result, Ingrediente[].class);

            // Fai qualcosa con l'array di oggetti Bevanda
            for (Ingrediente ingrediente : ingredienteArray)
                ingredienti.add(ingrediente);
        }


        return ingredienti;
    }

    public ArrayList<Ingrediente> getAllIngredienti(){
        ArrayList<Ingrediente> ingredienteArrayList = new ArrayList<>();
        String result = null;

        connessioneController.startConnection();

        // richiesta
        connessioneController.writeOnOutput("getAllIngredienti");

        // ricezione
        result = connessioneController.readFromInput();

        System.out.println(result);

        // Separo i JSON e creo un array che riverserò nell'arraylist
        if(result != null){
            result = result.replaceAll("\\}\\s*\\{", "},{");

            result = "[" + result + "]";

            Ingrediente[] ingredienteArray = gson.fromJson(result, Ingrediente[].class);

            // Fai qualcosa con l'array di oggetti Bevanda
            for (Ingrediente ingrediente : ingredienteArray)
                ingredienteArrayList.add(ingrediente);
        }


        return ingredienteArrayList;
    }

    public String ingredienteToJson(Ingrediente ingrediente){
        String json = gson.toJson(ingrediente);
        return json;
    }

    public Ingrediente jsonToIngrediente(String json){
        Ingrediente ingrediente = gson.fromJson(json, Ingrediente.class);
        return ingrediente;
    }

    public String bevandaToJson(Bevanda bevanda){
        String json = gson.toJson(bevanda);
        return json;
    }

}
