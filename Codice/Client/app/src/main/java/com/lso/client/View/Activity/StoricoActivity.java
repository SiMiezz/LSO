package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.card.MaterialCardView;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Utente;
import com.lso.client.R;

public class StoricoActivity extends AppCompatActivity {

    private ImageButton backButton;
    private MaterialCardView cocktailButton;
    private MaterialCardView frullatiButton;

    private UtenteController utenteController = new UtenteController();

    private String emailCorrente;
    private Utente utenteCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storico);

        backButton = findViewById(R.id.back_button_storico);
        cocktailButton = findViewById(R.id.cocktail_button_storico);
        frullatiButton = findViewById(R.id.frullati_button_storico);

//        new Thread(()->{
//            emailCorrente = getIntent().getExtras().getString("utenteEmail");
//            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);
//        }).start();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cocktailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), StoricoVisualizzazioneActivity.class);
                    String category = "Cocktail";
                    intent.putExtra("category",category);
                    intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                    startActivity(intent);
                }).start();

            }
        });

        frullatiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), StoricoVisualizzazioneActivity.class);
                    String category = "Frullati";
                    intent.putExtra("category",category);
                    intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                    startActivity(intent);
                }).start();

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