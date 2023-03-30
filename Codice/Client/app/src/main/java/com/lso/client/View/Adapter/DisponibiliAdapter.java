package com.lso.client.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lso.client.Model.Bevanda;
import com.lso.client.R;
import com.lso.client.View.Activity.AcquistaDisponibiliActivity;

import java.util.ArrayList;

public class DisponibiliAdapter extends RecyclerView.Adapter<DisponibiliAdapter.DisponibiliHolder> {

    private Context context;
    private ArrayList<Bevanda> bevandeArrayList;
    private AcquistaDisponibiliActivity acquistaDisponibiliActivity;

    public DisponibiliAdapter(Context context, ArrayList<Bevanda> bevandeArrayList){
        this.context = context;
        this.bevandeArrayList = bevandeArrayList;
        acquistaDisponibiliActivity = (AcquistaDisponibiliActivity) context;
    }


    @NonNull
    @Override
    public DisponibiliAdapter.DisponibiliHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_acquista_disponibili_recyclerview, parent, false);
        return new DisponibiliHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisponibiliAdapter.DisponibiliHolder holder, int position) {

        Bevanda bevanda = bevandeArrayList.get(position);


        holder.nomeBevanda.setText(bevanda.getNome().toUpperCase());

        holder.prezzoBevanda.setText(String.valueOf(bevanda.getPrezzo()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acquistaDisponibiliActivity.getIngredientiByBevanda(context, bevanda);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AcquistaDisponibiliActivity acquistaDisponibiliActivity = (AcquistaDisponibiliActivity) context;
//                acquistaDisponibiliActivity.cartAnimation(bevanda);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return bevandeArrayList.size();
    }

    /*****************************************************************************
     *****************************************************************************
     *****************************************************************************/

    public class DisponibiliHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView nomeBevanda;
        private TextView prezzoBevanda;

        public DisponibiliHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_acquista_disponibili_clickable_item);
            nomeBevanda = itemView.findViewById(R.id.row_acquista_disponibili_nome);
            prezzoBevanda = itemView.findViewById(R.id.row_acquista_disponibili_prezzo);
        }
    }


}
