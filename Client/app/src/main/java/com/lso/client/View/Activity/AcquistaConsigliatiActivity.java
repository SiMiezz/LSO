package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Ingrediente;
import com.lso.client.R;
import com.lso.client.View.Adapter.ConsigliatiAdapter;
import com.lso.client.View.Adapter.IngredientiSpinnerAdapter;

import java.util.ArrayList;

public class AcquistaConsigliatiActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView categoryText;

    private CheckBox checkBox;
    private Spinner spinner;

    private FloatingActionButton carrelloButton;

    private ArrayList<Bevanda> bevandeArrayList;
    private ArrayList<Ingrediente> ingredientiArrayList;

    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_consigliati);


        backButton = findViewById(R.id.back_button_acquista_consigliati);
        recyclerView = findViewById(R.id.acquista_consigliati_recyclerview);
        categoryText = findViewById(R.id.category_text_acquista_consigliati);

        checkBox = findViewById(R.id.checkbox_acquista_consigliati);
        spinner = findViewById(R.id.spinner_acquista_consigliati);

        carrelloButton = findViewById(R.id.carrello_button);

        this.category = getIntent().getExtras().getString("category");

        categoryText.setText(category);

        bevandeArrayList = new ArrayList<>();
        ingredientiArrayList = new ArrayList<>();

        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));
        ingredientiArrayList.add(new Ingrediente("gin"));
        ingredientiArrayList.add(new Ingrediente("tonica"));


        IngredientiSpinnerAdapter ingredientiSpinnerAdapter = new IngredientiSpinnerAdapter(this, 0, ingredientiArrayList);
        spinner.setAdapter(ingredientiSpinnerAdapter);

        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));
        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));
        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));
        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));
        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));
        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));

        ConsigliatiAdapter consigliatiAdapter = new ConsigliatiAdapter(this, bevandeArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(consigliatiAdapter);


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
}