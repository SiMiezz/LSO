package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Utente;
import com.lso.client.R;

public class AcquistaActivity extends AppCompatActivity {

    private ImageButton backButton;
    private MaterialCardView cocktailButton;
    private MaterialCardView frullatiButton;
    private FloatingActionButton carrelloButton;

    private String emailCorrente;
    private Utente utenteCorrente;

    private UtenteController utenteController = new UtenteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista);

        cocktailButton = findViewById(R.id.cocktail_button_acquista);
        frullatiButton = findViewById(R.id.frullati_button_acquista);
        carrelloButton = findViewById(R.id.carrello_button);

        cocktailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), AcquistaSceltaCocktailActivity.class);
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

                    Intent intent = new Intent(getApplicationContext(), AcquistaSceltaFrullatiActivity.class);
                    intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                    startActivity(intent);
                }).start();


            }
        });

        carrelloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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