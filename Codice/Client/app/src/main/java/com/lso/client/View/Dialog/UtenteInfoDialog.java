package com.lso.client.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Utente;
import com.lso.client.R;
import com.lso.client.View.Activity.HomeActivity;
import com.lso.client.View.Activity.LoginActivity;

public class UtenteInfoDialog extends Dialog {

    private Utente utenteCorrente;
    private TextView emailText;
    private TextView nomeText;
    private TextView cognomeText;

    private AppCompatButton cambiaPasswordButton;
    private AppCompatButton okButton;
    private AppCompatButton logoutButton;

    private EditText nuovaPassword;
    private EditText confermaPassword;

    private HomeActivity homeActivity;

    private UtenteController utenteController = new UtenteController();

    public UtenteInfoDialog(@NonNull Context context, Utente utenteCorrente, HomeActivity homeActivity) {
        super(context);
        this.utenteCorrente = utenteCorrente;
        this.homeActivity = homeActivity;
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
        logoutButton = findViewById(R.id.utente_dialog_logout_button);

        emailText.setText(utenteCorrente.getEmail());
        nomeText.setText(utenteCorrente.getNome());
        cognomeText.setText(utenteCorrente.getCognome());

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        cambiaPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nuovaPassword.getText().toString().equals(confermaPassword.getText().toString())){
                    new Thread(()->{
                        utenteController.cambiaPasswordUtente(utenteCorrente, nuovaPassword.getText().toString());
                    }).start();

                    nuovaPassword.setText("");
                    confermaPassword.setText("");
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                homeActivity.finishAffinity();
                homeActivity.startActivity(new Intent(homeActivity, LoginActivity.class));
            }
        });


    }

}
