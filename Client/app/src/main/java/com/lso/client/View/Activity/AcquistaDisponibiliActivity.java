package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lso.client.Model.Bevanda;
import com.lso.client.R;
import com.lso.client.View.Adapter.DisponibiliAdapter;

import java.util.ArrayList;

public class AcquistaDisponibiliActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView categoryText;

    private FloatingActionButton carrelloButton;

    private String category;
    private ArrayList<Bevanda> bevandeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acquista_disponibili);

        this.category = getIntent().getExtras().getString("category");

        backButton = findViewById(R.id.back_button_acquista_disponibili);
        recyclerView = findViewById(R.id.acquista_disponibili_recyclerview);
        categoryText = findViewById(R.id.category_text_acquista_disponibili);
        carrelloButton = findViewById(R.id.carrello_button);

        categoryText.setText(category);

        // set array list con presenter ( query filtrando cocktail/frullati a seconda del categoryText.getText() )

        bevandeArrayList = new ArrayList<>();

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


        DisponibiliAdapter disponibiliAdapter = new DisponibiliAdapter(this, bevandeArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(disponibiliAdapter);


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