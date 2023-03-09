package com.lso.client.Model;

public class Bevanda {

    private String nome;
    private Float prezzo;

    public Bevanda(){

    }

    public Bevanda(String nome, Float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }
}
