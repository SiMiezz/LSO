package com.lso.client.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lso.client.Controller.IngredienteController;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Ingrediente;
import com.lso.client.R;
import com.lso.client.View.Adapter.IngredientiDialogAdapter;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class BevandaInfoDialog extends Dialog{

    private AppCompatButton okButton;
    private RecyclerView recyclerView;

    private Bevanda bevandaSelezionata;
    private ArrayList<Ingrediente> ingredienteArrayList;
    private IngredientiDialogAdapter ingredientiDialogAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Context context;

    private IngredienteController ingredienteController = new IngredienteController();

    public BevandaInfoDialog(@NonNull Context context, ArrayList<Ingrediente> ingredienteArrayList) {
        super(context);
        this.context = context;
        this.ingredienteArrayList = ingredienteArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bevanda_info_dialog_layout);

        okButton = findViewById(R.id.bevanda_info_dialog_ok_button);
        recyclerView = findViewById(R.id.lista_ingredienti_dialog_recyclerview);

        ingredientiDialogAdapter = new IngredientiDialogAdapter(context, ingredienteArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ingredientiDialogAdapter);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


    public Bevanda getBevandaSelezionata() {
        return bevandaSelezionata;
    }

    public void setBevandaSelezionata(Bevanda bevandaSelezionata) {
        this.bevandaSelezionata = bevandaSelezionata;
    }

    public void notifyAdapter(){
        ingredientiDialogAdapter.notifyDataSetChanged();
    }
}
