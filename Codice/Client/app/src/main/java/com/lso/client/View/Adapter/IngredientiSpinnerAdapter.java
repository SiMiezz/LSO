package com.lso.client.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lso.client.Model.Ingrediente;
import com.lso.client.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientiSpinnerAdapter extends ArrayAdapter<Ingrediente> {

    private Context context;
    private ArrayList<Ingrediente> ingredientiArrayList;
    private ArrayList<Ingrediente> ingredientiSelezionatiArrayList;

    public IngredientiSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Ingrediente> ingredientiList) {
        super(context, resource, ingredientiList);
        this.context = context;
        this.ingredientiArrayList = (ArrayList<Ingrediente>) ingredientiList;
        ingredientiSelezionatiArrayList = new ArrayList<>();
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent){
        final IngredienteHolder holder;

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.row_ingrediente_spinner, null);

            holder = new IngredienteHolder();
            holder.nomeIngrediente = (TextView) convertView.findViewById(R.id.ingrediente_text_spinner);
            holder.checkBoxIngrediente = (CheckBox) convertView.findViewById(R.id.ingrediente_checkbox_spinner);
            convertView.setTag(holder);
        }
        else{
            holder = (IngredienteHolder) convertView.getTag();
        }

        Ingrediente currentIngrediente = ingredientiArrayList.get(position);
        
        holder.nomeIngrediente.setText(currentIngrediente.getNome().toUpperCase());

        holder.checkBoxIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBoxIngrediente.isChecked())
                    if(!(ingredientiSelezionatiArrayList.contains(currentIngrediente)))
                        ingredientiSelezionatiArrayList.add(currentIngrediente);

                if(!(holder.checkBoxIngrediente.isChecked()))
                    if(ingredientiSelezionatiArrayList.contains(currentIngrediente))
                        ingredientiSelezionatiArrayList.remove(currentIngrediente);
            }
        });

        return convertView;
    }

    public ArrayList<Ingrediente> getIngredientiArrayList() {
        return ingredientiArrayList;
    }

    public void setIngredientiArrayList(ArrayList<Ingrediente> ingredientiArrayList) {
        this.ingredientiArrayList = ingredientiArrayList;
    }

    public ArrayList<Ingrediente> getIngredientiSelezionatiArrayList() {
        return ingredientiSelezionatiArrayList;
    }

    public void setIngredientiSelezionatiArrayList(ArrayList<Ingrediente> ingredientiSelezionatiArrayList) {
        this.ingredientiSelezionatiArrayList = ingredientiSelezionatiArrayList;
    }

    /*****************************************************************************
     *****************************************************************************
     *****************************************************************************/

    public class IngredienteHolder{
        private TextView nomeIngrediente;
        private CheckBox checkBoxIngrediente;
    }



}
