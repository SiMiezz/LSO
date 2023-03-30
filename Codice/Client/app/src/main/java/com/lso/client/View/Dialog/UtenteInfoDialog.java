package com.lso.client.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lso.client.Model.Utente;
import com.lso.client.R;

public class UtenteInfoDialog extends Dialog {

    private Utente utenteCorrente;
    private TextView emailText;
    private TextView nomeText;
    private TextView cognomeText;

    public UtenteInfoDialog(@NonNull Context context, Utente utenteCorrente) {
        super(context);
        this.utenteCorrente = utenteCorrente;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.utente_info_dialog);

        emailText = findViewById(R.id.email_utente_dialog);
        nomeText = findViewById(R.id.nome_utente_dialog);
        cognomeText = findViewById(R.id.cognome_utente_dialog);

        emailText.setText(utenteCorrente.getEmail());
        nomeText.setText(utenteCorrente.getNome());
        cognomeText.setText(utenteCorrente.getCognome());


    }

}
