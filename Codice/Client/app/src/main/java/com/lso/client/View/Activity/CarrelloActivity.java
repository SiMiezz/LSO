package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lso.client.Controller.BevandaController;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Utente;
import com.lso.client.R;
import com.lso.client.View.Adapter.CarrelloAdapter;

import java.util.ArrayList;

public class CarrelloActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppCompatButton acquistaButton;

    private String emailCorrente;
    private Utente utenteCorrente;

    private ArrayList<Bevanda> bevandaArrayList;

    private CarrelloAdapter carrelloAdapter;

    private UtenteController utenteController = new UtenteController();
    private BevandaController bevandaController = new BevandaController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        recyclerView = findViewById(R.id.carrello_recyclerview);
        acquistaButton = findViewById(R.id.carrello_acquista_button);

        bevandaArrayList = new ArrayList<>();

        new Thread(()->{
            emailCorrente = getIntent().getExtras().getString("utenteEmail");
            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

            bevandaArrayList = bevandaController.getCarrelloByUtente(utenteCorrente);
            carrelloAdapter = new CarrelloAdapter(this, bevandaArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            runOnUiThread(()->{
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(carrelloAdapter);
            });
        }).start();

        acquistaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bevandaArrayList.size() > 0){
                    new Thread(()->{
                        for(Bevanda bevanda : bevandaArrayList)
                            bevandaController.acquistaBevanda(utenteCorrente, bevanda);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                        startActivity(intent);
                    }).start();
                }
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