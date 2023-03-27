package com.lso.client.Model;

import com.lso.client.Model.Enum.Bevanda_Type;

public class Bevanda {

    private Integer id;
    private String nome;
    private Float prezzo;
    private Bevanda_Type tipo;
    private String bar_nome;

    public Bevanda(){

    }

    public Bevanda(String nome, Float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Bevanda(Integer id, String nome, Float prezzo, Bevanda_Type tipo, String bar_nome) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.tipo = tipo;
        this.bar_nome = bar_nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Bevanda_Type getTipo() {
        return tipo;
    }

    public void setTipo(Bevanda_Type tipo) {
        this.tipo = tipo;
    }

    public String getBar_nome() {
        return bar_nome;
    }

    public void setBar_nome(String bar_nome) {
        this.bar_nome = bar_nome;
    }
}
