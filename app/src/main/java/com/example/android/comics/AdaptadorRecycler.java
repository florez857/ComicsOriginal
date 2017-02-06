package com.example.android.comics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by INTEL on 06/02/2017.
 */

public class AdaptadorRecycler extends  RecyclerView.Adapter<AdaptadorRecycler.RevistaViewHolder> {

    private List<Lista> items;

    public static class RevistaViewHolder extends RecyclerView.ViewHolder {
        // Campos de la lista
        public ImageView imagen;
        public TextView titulo;
        public TextView votos;

        public RevistaViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            titulo = (TextView) v.findViewById(R.id.titulo);
            votos = (TextView) v.findViewById(R.id.votos);
        }
    }

    public AdapterRecycler(List<Lista> items) {
        this.items = items;
    }

    /*
    Añade una lista completa de items
     */
    public void addAll(List<Lista> lista){
        items.addAll(lista);
        notifyDataSetChanged();
    }

    /*
    Permite limpiar todos los elementos del recycler
     */
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RevistaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista, viewGroup, false);
        return new RevistaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RevistaViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getIdImagen());
        viewHolder.titulo.setText(items.get(i).getTitulo());
        viewHolder.votos.setText("Votos: "+String.valueOf(items.get(i).getVotos()));
    }
}
