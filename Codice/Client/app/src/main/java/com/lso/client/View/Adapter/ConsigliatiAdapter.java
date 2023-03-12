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
import com.lso.client.View.Activity.AcquistaConsigliatiActivity;
import com.lso.client.View.Activity.AcquistaDisponibiliActivity;

import java.util.ArrayList;

public class ConsigliatiAdapter extends RecyclerView.Adapter<ConsigliatiAdapter.ConsigliatiHolder> {

    private Context context;
    private ArrayList<Bevanda> bevandeArrayList;

    public ConsigliatiAdapter(Context context, ArrayList<Bevanda> bevandeArrayList){
        this.context = context;
        this.bevandeArrayList = bevandeArrayList;
    }

    @NonNull
    @Override
    public ConsigliatiAdapter.ConsigliatiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_acquista_consigliati_recyclerview, parent, false);
        return new ConsigliatiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsigliatiAdapter.ConsigliatiHolder holder, int position) {

        Bevanda bevanda = bevandeArrayList.get(position);

        holder.nomeBevanda.setText(bevanda.getNome().toUpperCase());

        holder.prezzoBevanda.setText(String.valueOf(bevanda.getPrezzo()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aggiunta al carrello
                AcquistaConsigliatiActivity acquistaConsigliatiActivity = (AcquistaConsigliatiActivity) context;
                acquistaConsigliatiActivity.cartAnimation();
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

    public class ConsigliatiHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView nomeBevanda;
        private TextView prezzoBevanda;

        public ConsigliatiHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_acquista_consigliati_clickable_item);
            nomeBevanda = itemView.findViewById(R.id.row_acquista_consigliati_nome);
            prezzoBevanda = itemView.findViewById(R.id.row_acquista_consigliati_prezzo);
        }
    }
}
