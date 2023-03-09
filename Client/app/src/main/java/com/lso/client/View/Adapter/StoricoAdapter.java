package com.lso.client.View.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lso.client.Model.Bevanda;
import com.lso.client.R;

import java.util.ArrayList;

public class StoricoAdapter extends RecyclerView.Adapter<StoricoAdapter.StoricoHolder> {

    private ArrayList<Bevanda> bevandeArrayList;

    @NonNull
    @Override
    public StoricoAdapter.StoricoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StoricoAdapter.StoricoHolder holder, int position) {

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

        public StoricoHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_storico_clickable_item);
            nomeBevanda = itemView.findViewById(R.id.row_storico_text);
        }
    }
}
