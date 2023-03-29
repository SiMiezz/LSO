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

public class AcquistaSceltaCocktailActivity extends AppCompatActivity {

    private ImageButton backButton;
    private MaterialCardView disponibiliButton;
    private MaterialCardView consigliatiButton;

    private String emailCorrente;
    private Utente utenteCorrente;

    private UtenteController utenteController = new UtenteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_scelta_cocktail);

        backButton = findViewById(R.id.back_button_acquista_scelta_cocktail);
        disponibiliButton = findViewById(R.id.acquista_scelta_cocktail_button_disponibili);
        consigliatiButton = findViewById(R.id.acquista_scelta_cocktail_button_consigliati);

        disponibiliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), AcquistaDisponibiliActivity.class);
                    intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                    String category = "Cocktail";
                    intent.putExtra("category",category);
                    startActivity(intent);
                }).start();
            }
        });

        consigliatiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), AcquistaConsigliatiActivity.class);
                    intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                    String category = "Cocktail";
                    intent.putExtra("category",category);
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