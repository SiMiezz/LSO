package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lso.client.Controller.BevandaController;
import com.lso.client.Controller.IngredienteController;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Enum.Bevanda_Type;
import com.lso.client.Model.Ingrediente;
import com.lso.client.Model.Utente;
import com.lso.client.R;
import com.lso.client.View.Adapter.ConsigliatiAdapter;
import com.lso.client.View.Adapter.IngredientiSpinnerAdapter;
import com.lso.client.View.Dialog.AcquistoInfoDialog;
import com.lso.client.View.Dialog.BevandaInfoDialog;

import java.util.ArrayList;

public class AcquistaConsigliatiActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView categoryText;

    private CheckBox checkBox;
    private Spinner spinner;

    private FloatingActionButton carrelloButton;
    private Button cercaButton;

    private ArrayList<Bevanda> bevandeArrayList;
    private ArrayList<Ingrediente> ingredientiArrayList;
    private ArrayList<Ingrediente> ingredientiSelezionatiArrayList;

    private String category;

    private ArrayList<Ingrediente> ingredienteArrayListPerDialog;

    private Context context;

    private IngredienteController ingredienteController = new IngredienteController();
    private BevandaController bevandaController = new BevandaController();

    private String emailCorrente;
    private Utente utenteCorrente;

    private IngredientiSpinnerAdapter ingredientiSpinnerAdapter;

    private UtenteController utenteController = new UtenteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_consigliati);

        context = this;

        backButton = findViewById(R.id.back_button_acquista_consigliati);
        recyclerView = findViewById(R.id.acquista_consigliati_recyclerview);
        categoryText = findViewById(R.id.category_text_acquista_consigliati);

        checkBox = findViewById(R.id.checkbox_acquista_consigliati);
        spinner = findViewById(R.id.spinner_acquista_consigliati);
        cercaButton = findViewById(R.id.cerca_consigliati_button);

        carrelloButton = findViewById(R.id.carrello_button);

        this.category = getIntent().getExtras().getString("category");

        categoryText.setText(category);

        if(categoryText.getText().equals("Cocktail"))
            category = "cocktail";
        else if(categoryText.getText().equals("Frullati"))
            category = "frullato";

        bevandeArrayList = new ArrayList<>();
        ingredientiArrayList = new ArrayList<>();

        new Thread(()->{
            emailCorrente = getIntent().getExtras().getString("utenteEmail");
            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

            ingredientiArrayList = ingredienteController.getAllIngredienti();

            runOnUiThread(()->{
                ingredientiSpinnerAdapter = new IngredientiSpinnerAdapter(this, 0, ingredientiArrayList);
                spinner.setAdapter(ingredientiSpinnerAdapter);
            });


        }).start();

        cercaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    bevandeArrayList = bevandaController.getConsigliatiByUtenteAndBevandaTypeAndRecentiAndIngredienti(utenteCorrente, Bevanda_Type.valueOf(category),  checkBox.isChecked(), ingredientiSpinnerAdapter.getIngredientiSelezionatiArrayList());
                    ConsigliatiAdapter consigliatiAdapter = new ConsigliatiAdapter(context, bevandeArrayList);

                    runOnUiThread(()->{
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(consigliatiAdapter);
                    });

                }).start();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcquistoInfoDialog acquistoInfoDialog = new AcquistoInfoDialog(context);
                acquistoInfoDialog.show();
            }
        });

        carrelloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(()->{
                    emailCorrente = getIntent().getExtras().getString("utenteEmail");
                    utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

                    Intent intent = new Intent(getApplicationContext(), CarrelloActivity.class);
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

    public void cartAnimation(Bevanda bevanda){
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);

        carrelloButton.startAnimation(bounceAnimation);

        new Thread(()->{
            bevandaController.aggiungiACarrello(utenteCorrente, bevanda);
        }).start();
    }

    public void getIngredientiByBevanda(Context context, Bevanda bevanda){
        new Thread(()->{
            ingredienteArrayListPerDialog = ingredienteController.getIngredientiByBevanda(bevanda);

            runOnUiThread(()->{
                System.out.println("miao2");
                BevandaInfoDialog bevandaInfoDialog = new BevandaInfoDialog(context, ingredienteArrayListPerDialog);
                bevandaInfoDialog.show();
            });

        }).start();
    }
}