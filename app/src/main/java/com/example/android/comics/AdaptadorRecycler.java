package com.example.android.comics;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by INTEL on 06/02/2017.
 */

public class AdaptadorRecycler extends  RecyclerView.Adapter<AdaptadorRecycler.RevistaViewHolder> {

    private ArrayList<Comic> items;
    Context context;

    public static class RevistaViewHolder extends RecyclerView.ViewHolder {
        // Campos de la lista
        public ImageView imagen;
        public TextView titulo;
        public TextView precio;

        public RevistaViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagenComic);
            titulo = (TextView) v.findViewById(R.id.TituloComic);
            precio = (TextView) v.findViewById(R.id.Precio);
        }
    }

    public AdaptadorRecycler(ArrayList<Comic> items ,Context contexto) {
        this.items = items;
        this.context=contexto;
    }

    //Añade una lista completa de items
    public void addAll(ArrayList<Comic> lista){
        items.addAll(lista);
        notifyDataSetChanged();
    }

    //Permite limpiar todos los elementos del recycler
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
                .inflate(R.layout.activity_item_comic, viewGroup, false);
        return new RevistaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RevistaViewHolder viewHolder, int i) {
        Glide.with(context)
                .load(items.get(i).getUrlimagen())
                .centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagen);

        viewHolder.titulo.setText(items.get(i).getTitulo());
        viewHolder.precio.setText(items.get(i).getPrecio());
    }

}
