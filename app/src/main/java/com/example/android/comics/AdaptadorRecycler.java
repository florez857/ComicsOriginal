package com.example.android.comics;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.attr.key;

/**
 * Created by INTEL on 06/02/2017.
 */

public class AdaptadorRecycler extends  RecyclerView.Adapter<AdaptadorRecycler.RevistaViewHolder>implements Filterable {

    private ArrayList<Comic> items;
    Context context;
    private Comic comic;
    FiltroComics filter;
    private ArrayList<Comic> ListadaComics ,Listafiltro;
    private RequestQueue ColaPeticion;
    private String JsonURL="http://gateway.marvel.com/v1/public/comics?limit=30&ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";
    static String KEY="ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";
    private String path ="http://gateway.marvel.com/v1/public/comics/";
    private int id;



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

    public AdaptadorRecycler(Context contexto) {

        this.context=contexto;
        Listafiltro=new ArrayList<Comic>();
        ListadaComics=new ArrayList<Comic>();
        items=new ArrayList<Comic>();
        ColaPeticion= Volley.newRequestQueue(contexto);
        Log.d("Tamaño de lista",String.valueOf( ListadaComics.size()));
        cargar();


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
    public void onBindViewHolder(RevistaViewHolder viewHolder, final int i) {
        id=i;
        Glide.with(context)
                .load(items.get(i).getUrlimagen())
                //.centerCrop()
                .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
               .into(viewHolder.imagen);
        Log.d("adaptador","recycler    ");
        viewHolder.titulo.setText(items.get(i).getTitulo());
        viewHolder.precio.setText(items.get(i).getPrecio());
        viewHolder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(),DetalleItem.class);
                Log.d("id que se pasa a intent",ListadaComics.get(i).getId());
                intent.putExtra("id",ListadaComics.get(i).getId());
                context.startActivity(intent);
                Log.d("llamada a intent","llama");
            }
        });
    }

    public void cargar(){

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,

                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        Comic comic;
                        try {

                            if (response!=null) {

                                JSONObject json = response.getJSONObject("data");

                                JSONArray ListadoComics = json.getJSONArray("results");

                                for (int i = 0; i < ListadoComics.length(); i++) {

                                    comic = new Comic();
                                    JSONObject Jsoncomic = (JSONObject) ListadoComics.get(i);
                                    String Atributo = "";

                                    //obtengo el titulo del comic
                                    Atributo = Jsoncomic.getString("title");
                                    comic.setTitulo(Atributo);
                                    comic.setId(Jsoncomic.getString("id"));

                                    //Log.d("datos de objeto",obj1.toString());

                                    //obtengo el precio del comic
                                    JSONObject imagen = Jsoncomic.getJSONObject("thumbnail");
                                    //JSONObject objetoimagen = imagen.getJSONObject(0);

                                    String path = imagen.getString("path");
                                    String extension = imagen.getString("extension");
                                    String UrlImagen = path + "." + extension +"?"+ key;
                                    comic.setUrlimagen(UrlImagen);
                                    //comic.setUrlimagen("imagen");

                                    JSONArray ArrayPrecio = Jsoncomic.getJSONArray("prices");
                                    JSONObject ObjetoPrecio =(JSONObject) ArrayPrecio.get(0);

                                    String typo = ObjetoPrecio.getString("type");
                                    String precio = ObjetoPrecio.getString("price");
                                    if (precio == "0.00") {
                                        precio = "Agotado";
                                    }
                                    comic.setPrecio(precio);

                                    Log.d("titulo" , Atributo);
                                    Log.d("precio" , precio);
                                    Log.d("url" , UrlImagen);
                                    ListadaComics.add(comic);
                                    Log.d("tamaño de la lista",String.valueOf(i));
                                }

                                Listafiltro=ListadaComics;
                                addAll(ListadaComics);
                            }
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley",error.toString() );
                        error.printStackTrace();

                    }
                }
        );
        ColaPeticion.add(obreq);
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new FiltroComics(Listafiltro,this);
        }
        return filter;
    }

    public void refrescar(){

        Collections.shuffle((List<Comic>) ListadaComics);
        clear();
        addAll(ListadaComics);
    }
}
