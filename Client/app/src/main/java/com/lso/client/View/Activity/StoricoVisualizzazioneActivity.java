package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lso.client.Model.Bevanda;
import com.lso.client.R;
import com.lso.client.View.Adapter.StoricoAdapter;

import java.util.ArrayList;

public class StoricoVisualizzazioneActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView categoryText;
    private String category;
    private ArrayList<Bevanda> bevandeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storico_visualizzazione);

        Bundle extras = getIntent().getExtras();
        this.category = getIntent().getExtras().getString("category");

        backButton = findViewById(R.id.back_button_storico_visualizzazione);
        recyclerView = findViewById(R.id.storico_recyclerview);
        categoryText = findViewById(R.id.category_text_storico_visualizzazione);

        categoryText.setText(category);

        //presenter.getBevandeFromDB()

        bevandeArrayList = new ArrayList<>();

        // set array list con presenter ( query filtrando cocktail/frullati a seconda del categoryText.getText() )

        bevandeArrayList.add(new Bevanda("negroni", 5.99f));
        bevandeArrayList.add(new Bevanda("spritz", 4.99f));
        bevandeArrayList.add(new Bevanda("gin tonic", 6.99f));
        bevandeArrayList.add(new Bevanda("frullato alla fragola", 7.99f));

        StoricoAdapter storicoAdapter = new StoricoAdapter(this, bevandeArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(storicoAdapter);

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