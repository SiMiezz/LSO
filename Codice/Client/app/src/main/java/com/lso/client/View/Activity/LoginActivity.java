package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Utente;
import com.lso.client.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button accediButton;
    private TextView registratiLink;

    private UtenteController utenteController = new UtenteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.username_text_login);
        passwordText = findViewById(R.id.password_text_login);
        accediButton = findViewById(R.id.accedi_button_login);
        registratiLink = findViewById(R.id.registrati_link_text_login);
        

        accediButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(()->{
                    String email = usernameText.getText().toString();
                    String password = passwordText.getText().toString();
                    Utente utente = null;

                    if(!(email.isEmpty()) && !(password.isEmpty()))
                        utente = utenteController.getUtenteByEmailAndPassword(email, password);

                    if(utente != null){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("utenteEmail", utente.getEmail());
                        startActivity(intent);
                    } else {
                        System.out.println("Utente non trovato");
                    }
                }).start();

            }
        });

        registratiLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}