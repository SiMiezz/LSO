package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
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
import com.lso.client.View.Adapter.DisponibiliAdapter;
import com.lso.client.View.Dialog.AcquistoInfoDialog;
import com.lso.client.View.Dialog.BevandaInfoDialog;

import java.util.ArrayList;

public class AcquistaDisponibiliActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView categoryText;

    private FloatingActionButton carrelloButton;

    private String category;
    private ArrayList<Bevanda> bevandeArrayList;

    private BevandaController bevandaController = new BevandaController();
    private IngredienteController ingredienteController = new IngredienteController();

    private DisponibiliAdapter disponibiliAdapter;

    private ArrayList<Ingrediente> ingredienteArrayListPerDialog;

    private String emailCorrente;
    private Utente utenteCorrente;

    private Context context;

    private UtenteController utenteController = new UtenteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_disponibili);

        context = this;

        this.category = getIntent().getExtras().getString("category");

        backButton = findViewById(R.id.back_button_acquista_disponibili);
        recyclerView = findViewById(R.id.acquista_disponibili_recyclerview);
        categoryText = findViewById(R.id.category_text_acquista_disponibili);
        carrelloButton = findViewById(R.id.carrello_button);

        categoryText.setText(category);

        if(categoryText.getText().equals("Cocktail"))
            category = "cocktail";
        else if(categoryText.getText().equals("Frullati"))
            category = "frullato";

        bevandeArrayList = new ArrayList<>();

        new Thread(()->{
            emailCorrente = getIntent().getExtras().getString("utenteEmail");
            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

            bevandeArrayList = bevandaController.getDisponibiliByBevandaType(Bevanda_Type.valueOf(category));
            disponibiliAdapter = new DisponibiliAdapter(this, bevandeArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            runOnUiThread(()->{
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(disponibiliAdapter);
            });

        }).start();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcquistoInfoDialog acquistoInfoDialog = new AcquistoInfoDialog(context);
                acquistoInfoDialog.show();

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


    public void cartAnimation(){
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);

        carrelloButton.startAnimation(bounceAnimation);
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