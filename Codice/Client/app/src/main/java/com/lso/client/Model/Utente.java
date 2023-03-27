package com.lso.client.Model;

public class Utente {

    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String bar_nome;

    public Utente(){

    }

    public Utente(String email, String password, String nome, String cognome, String bar_nome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.bar_nome = bar_nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getBar_nome() {
        return bar_nome;
    }

    public void setBar_nome(String nomeBar) {
        this.bar_nome = nomeBar;
    }
}
