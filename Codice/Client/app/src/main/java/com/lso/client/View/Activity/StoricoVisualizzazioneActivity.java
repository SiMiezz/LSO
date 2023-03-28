package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lso.client.Controller.BevandaController;
import com.lso.client.Controller.IngredienteController;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Enum.Bevanda_Type;
import com.lso.client.Model.Ingrediente;
import com.lso.client.Model.Utente;
import com.lso.client.R;
import com.lso.client.View.Adapter.StoricoAdapter;
import com.lso.client.View.Dialog.BevandaInfoDialog;

import java.util.ArrayList;

public class StoricoVisualizzazioneActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView categoryText;

    private String category;
    private ArrayList<Bevanda> bevandeArrayList;
    private StoricoAdapter storicoAdapter;

    private UtenteController utenteController = new UtenteController();
    private BevandaController bevandaController = new BevandaController();
    private IngredienteController ingredienteController = new IngredienteController();

    private ArrayList<Ingrediente> ingredienteArrayList;

    private String emailCorrente;
    private Utente utenteCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storico_visualizzazione);

        category = getIntent().getExtras().getString("category");

        backButton = findViewById(R.id.back_button_storico_visualizzazione);
        recyclerView = findViewById(R.id.storico_recyclerview);
        categoryText = findViewById(R.id.category_text_storico_visualizzazione);

        categoryText.setText(category);

        if(categoryText.getText().equals("Cocktail"))
            category = "cocktail";
        else if(categoryText.getText().equals("Frullati"))
            category = "frullato";


        bevandeArrayList = new ArrayList<>();

        new Thread(()->{
            emailCorrente = getIntent().getExtras().getString("utenteEmail");
            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

            bevandeArrayList = bevandaController.getStoricoByUtenteAndBevandaType(utenteCorrente, Bevanda_Type.valueOf(category));
            storicoAdapter = new StoricoAdapter(this, bevandeArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            runOnUiThread(()->{
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(storicoAdapter);
            });

        }).start();



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

    public void getIngredientiByBevanda(Context context, Bevanda bevanda){
        new Thread(()->{
            ingredienteArrayList = ingredienteController.getIngredientiByBevanda(bevanda);
            System.out.println("miao1"+ingredienteArrayList.size());

            runOnUiThread(()->{
                System.out.println("miao2");
                BevandaInfoDialog bevandaInfoDialog = new BevandaInfoDialog(context, ingredienteArrayList);
                bevandaInfoDialog.show();
            });

        }).start();
    }
}