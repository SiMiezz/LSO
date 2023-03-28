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
import com.lso.client.View.Activity.StoricoVisualizzazioneActivity;
import com.lso.client.View.Dialog.BevandaInfoDialog;

import java.util.ArrayList;

public class StoricoAdapter extends RecyclerView.Adapter<StoricoAdapter.StoricoHolder> {

    private Context context;
    private ArrayList<Bevanda> bevandeArrayList;
    private StoricoVisualizzazioneActivity storicoVisualizzazioneActivity;

    public StoricoAdapter(Context context, ArrayList<Bevanda> bevandeArrayList){
        this.context = context;
        this.bevandeArrayList = bevandeArrayList;
        storicoVisualizzazioneActivity = (StoricoVisualizzazioneActivity) context;
    }

    @NonNull
    @Override
    public StoricoAdapter.StoricoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_storico_recyclerview, parent, false);
        return new StoricoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoricoAdapter.StoricoHolder holder, int position) {

        Bevanda bevanda = bevandeArrayList.get(position);

        holder.nomeBevanda.setText(bevanda.getNome().toUpperCase());

        holder.prezzoBevanda.setText(String.valueOf(bevandeArrayList.get(position).getPrezzo()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BevandaInfoDialog bevandaInfoDialog = new BevandaInfoDialog(context, bevanda);
//                bevandaInfoDialog.show();
                storicoVisualizzazioneActivity.getIngredientiByBevanda(context, bevanda);
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

    public class StoricoHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView nomeBevanda;
        private TextView prezzoBevanda;

        public StoricoHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_storico_clickable_item);
            nomeBevanda = itemView.findViewById(R.id.row_storico_nome);
            prezzoBevanda = itemView.findViewById(R.id.row_storico_prezzo);
        }
    }
}
