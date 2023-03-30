package com.lso.client.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lso.client.Model.Bevanda;
import com.lso.client.R;

import java.util.ArrayList;

public class CarrelloAdapter extends RecyclerView.Adapter<CarrelloAdapter.CarrelloHolder> {

    private Context context;
    private ArrayList<Bevanda> bevandaArrayList;

    public CarrelloAdapter(Context context, ArrayList<Bevanda> bevandaArrayList){
        this.context = context;
        this.bevandaArrayList = bevandaArrayList;
    }

    @NonNull
    @Override
    public CarrelloAdapter.CarrelloHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_carrello, parent, false);
        return new CarrelloHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrelloAdapter.CarrelloHolder holder, int position) {

        Bevanda bevanda = bevandaArrayList.get(position);

        holder.nomeText.setText(bevanda.getNome().toUpperCase());

        holder.prezzoText.setText(String.valueOf(bevanda.getPrezzo()));

    }

    @Override
    public int getItemCount() {
        return bevandaArrayList.size();
    }

    /*****************************************************************************
     *****************************************************************************
     *****************************************************************************/

    public class CarrelloHolder extends RecyclerView.ViewHolder{

        private TextView nomeText;
        private TextView prezzoText;

        public CarrelloHolder(@NonNull View itemView) {
            super(itemView);
            nomeText = itemView.findViewById(R.id.row_carrello_nome);
            prezzoText = itemView.findViewById(R.id.row_carrello_prezzo);
        }
    }
}
