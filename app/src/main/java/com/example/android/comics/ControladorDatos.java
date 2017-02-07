package com.example.android.comics;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by INTEL on 06/02/2017.
 */

public class ControladorDatos {

    private Comic comic;
    private ArrayList<Comic> ListadaComics;
    private RequestQueue ColaPeticion;
    private Context Contexto;
    private String JsonURL="http://gateway.marvel.com/v1/public/comics?ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";
    static String KEY="ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";


    public ControladorDatos(Context contexto){

        Log.d("Tamaño de lista","texto");
        this.Contexto=contexto;
        ListadaComics=new ArrayList<>();
        ColaPeticion= Volley.newRequestQueue(contexto);
        Log.d("Tamaño de lista",String.valueOf( ListadaComics.size()));


    }

    public ArrayList<Comic> getListadaComics() {
        return ListadaComics;
    }




    }






