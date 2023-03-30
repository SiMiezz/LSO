package com.lso.client.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.lso.client.Model.Utente;
import com.lso.client.R;

public class UtenteInfoDialog extends Dialog {

    private Utente utenteCorrente;
    private TextView emailText;
    private TextView nomeText;
    private TextView cognomeText;

    private AppCompatButton cambiaPasswordButton;
    private AppCompatButton okButton;

    private EditText nuovaPassword;
    private EditText confermaPassword;

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

        nuovaPassword = findViewById(R.id.nuova_password_utente_dialog);
        confermaPassword = findViewById(R.id.conferma_password_utente_dialog);

        cambiaPasswordButton = findViewById(R.id.cambia_password_conferma_button);
        okButton = findViewById(R.id.utente_dialog_ok_button);

        emailText.setText(utenteCorrente.getEmail());
        nomeText.setText(utenteCorrente.getNome());
        cognomeText.setText(utenteCorrente.getCognome());

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }

}
