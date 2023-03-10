package com.lso.client.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lso.client.Model.Ingrediente;
import com.lso.client.R;

import java.util.ArrayList;

public class IngredientiDialogAdapter extends RecyclerView.Adapter<IngredientiDialogAdapter.IngredientiDialogHolder> {

    private Context context;
    private ArrayList<Ingrediente> ingredientiArrayList;

    public IngredientiDialogAdapter(Context context, ArrayList<Ingrediente> ingredientiArrayList){
        this.context = context;
        this.ingredientiArrayList = ingredientiArrayList;
    }

    @NonNull
    @Override
    public IngredientiDialogAdapter.IngredientiDialogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_ingredienti_dialog_recyclerview, parent, false);
        return new IngredientiDialogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientiDialogAdapter.IngredientiDialogHolder holder, int position) {

        Ingrediente ingrediente = ingredientiArrayList.get(position);

        holder.nomeIngrediente.setText(ingrediente.getNome().toUpperCase());



    }

    @Override
    public int getItemCount() {
        return ingredientiArrayList.size();
    }

    /*****************************************************************************
     *****************************************************************************
     *****************************************************************************/

    public class IngredientiDialogHolder extends RecyclerView.ViewHolder{

        private TextView nomeIngrediente;

        public IngredientiDialogHolder(@NonNull View itemView) {
            super(itemView);
            nomeIngrediente = itemView.findViewById(R.id.row_ingredienti_dialog_nome);
        }
    }

}
