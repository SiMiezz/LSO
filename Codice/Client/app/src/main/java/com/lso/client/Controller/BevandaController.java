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

    public ArrayList<Bevanda> getStoricoByUtenteAndBevandaType(Utente utente, Bevanda_Type bevanda_type){
        ArrayList<Bevanda> bevande = new ArrayList<>();
        String result = null;

        connessioneController.startConnection();

        connessioneController.writeOnOutput("getStoricoByUtenteAndBevandaType$$"+utenteToJson(utente)+"$$"+bevanda_type.toString());

        result = connessioneController.readFromInput();

        System.out.println(result);

        // Separo i JSON e creo un array che riverso nell'arraylist
        if(result != null){
            result = result.replaceAll("\\}\\s*\\{", "},{");

            result = "[" + result + "]";

            Bevanda[] bevandeArray = gson.fromJson(result, Bevanda[].class);

            // Fai qualcosa con l'array di oggetti Bevanda
            for (Bevanda bevanda : bevandeArray)
                bevande.add(bevanda);
        }

        connessioneController.closeConnection();

        return bevande;
    }

    public ArrayList<Bevanda> getDisponibiliByBevandaType(Bevanda_Type bevanda_type){

        ArrayList<Bevanda> bevande = new ArrayList<>();
        String result = null;

        connessioneController.startConnection();

        connessioneController.writeOnOutput("getDisponibiliByBevandaType$$"+bevanda_type.toString());

        // Recupero la stringa contenente più JSON {json1}{json2}{...}
        result = connessioneController.readFromInput();

        System.out.println(result);

        // Separo i JSON e creo un array che riverserò nell'arraylist
        if(result != null){
            result = result.replaceAll("\\}\\s*\\{", "},{");

            result = "[" + result + "]";

            Bevanda[] bevandeArray = gson.fromJson(result, Bevanda[].class);

            // Fai qualcosa con l'array di oggetti Bevanda
            for (Bevanda bevanda : bevandeArray)
                bevande.add(bevanda);
        }


        // conversione

        connessioneController.closeConnection();

        return bevande;

    }

    public ArrayList<Bevanda> getConsigliatiByUtenteAndBevandaTypeAndRecentiAndIngredienti(Utente utente, Bevanda_Type bevanda_type, boolean recenti, List<Ingrediente> ingredienti){

        ArrayList<Bevanda> bevande = new ArrayList<>();
        String result = null;

        connessioneController.startConnection();

        String ingredientiJson = "";
        for(Ingrediente ingrediente : ingredienti)
            ingredientiJson = ingredientiJson+ingredienteToJson(ingrediente);
        System.out.println(utenteToJson(utente) +" "+ bevanda_type +" "+ recenti +" "+ ingredientiJson);

        connessioneController.writeOnOutput("getConsigliatiByUtenteAndBevandaTypeAndRecentiAndIngredienti$$"+bevanda_type+"$$"+recenti+"$$"+ingredientiJson);

        // Recupero la stringa contenente più JSON {json1}{json2}{...}
        result = connessioneController.readFromInput();

        System.out.println(result);

        // Separo i JSON e creo un array che riverserò nell'arraylist
        if(result != null){
            result = result.replaceAll("\\}\\s*\\{", "},{");

            result = "[" + result + "]";

            Bevanda[] bevandeArray = gson.fromJson(result, Bevanda[].class);

            // Fai qualcosa con l'array di oggetti Bevanda
            for (Bevanda bevanda : bevandeArray)
                bevande.add(bevanda);
        }

        connessioneController.closeConnection();

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

    public String utenteToJson(Utente utente){
        String json = gson.toJson(utente);
        return json;
    }

    public String ingredienteToJson(Ingrediente ingrediente){
        String json = gson.toJson(ingrediente);
        return json;
    }

}
