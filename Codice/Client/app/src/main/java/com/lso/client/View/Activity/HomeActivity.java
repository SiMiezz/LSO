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

public class HomeActivity extends AppCompatActivity {

    private ImageButton logoutButton;
    private MaterialCardView acquistaButton;
    private MaterialCardView storicoButton;

    private UtenteController utenteController = new UtenteController();

    private String emailCorrente;
    private Utente utenteCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutButton = findViewById(R.id.logout_button_home);
        acquistaButton = findViewById(R.id.acquista_button_home);
        storicoButton = findViewById(R.id.storico_button_home);

//        new Thread(()->{
//            emailCorrente = getIntent().getExtras().getString("utenteEmail");
//            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);
//        }).start();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        acquistaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), AcquistaActivity.class);
                    intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                    startActivity(intent);
                }).start();

            }
        });

        storicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), StoricoActivity.class);
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