package com.lso.client.Controller;

import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Enum.Bevanda_Type;
import com.lso.client.Model.Ingrediente;
import com.lso.client.Model.Utente;

import java.util.ArrayList;
import java.util.List;

public class BevandaController {

    public static List<Bevanda> getStoricoByUtenteAndBevandaType(Utente utente, Bevanda_Type bevanda_type){
        List<Bevanda> bevande = new ArrayList<>();
        // to do
        return bevande;
    }

    public static List<Bevanda> getDisponibiliByBevandaType(Bevanda_Type bevanda_type){
        List<Bevanda> bevande = new ArrayList<>();
        // to do
        return bevande;
    }

    public static List<Bevanda> getConsigliatiByBevandaTypeAndRecentiAndIngredienti(Bevanda_Type bevanda_type, boolean recenti, List<Ingrediente> ingredienti){
        List<Bevanda> bevande = new ArrayList<>();
        // to do
        return bevande;
    }

    public static void acquistaBevanda(Utente utente, Bevanda bevanda){

    }

}
