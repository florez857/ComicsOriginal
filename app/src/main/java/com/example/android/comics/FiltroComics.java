package com.example.android.comics;

/**
 * Created by INTEL on 06/02/2017.
 */

import android.widget.Filter;

import java.util.ArrayList;


public class FiltroComics extends Filter{
    AdaptadorRecycler adaptador;
    ArrayList<Comic> filterList;
    public FiltroComics (ArrayList<Comic> filterList,AdaptadorRecycler adapter)
    {
        this.adaptador=adapter;
        this.filterList=filterList;
    }
    //filtranso cursor
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //chequeando validez
        if(constraint != null && constraint.length() > 0)
        {
            //poner letras mayuscula
            constraint=constraint.toString().toUpperCase();
            //guardar nuestro filtro
            ArrayList<Comic> filtroComic=new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {
                //chekeo
                if(filterList.get(i).getTitulo().toUpperCase().contains(constraint))
                {
                    //añadiendo al filtro
                    filtroComic.add(filterList.get(i));
                }
            }
            results.count=filtroComic.size();
            results.values=filtroComic;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adaptador.clear();
        adaptador.addAll( (ArrayList<Comic>) results.values);
        //añadir y refrescar
    }
}