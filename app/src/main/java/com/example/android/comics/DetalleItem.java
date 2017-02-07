package com.example.android.comics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.key;

public class DetalleItem extends AppCompatActivity {

    private RequestQueue ColaPeticion;
    private String JsonURL="http://gateway.marvel.com/v1/public/comics?limit=30&ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";
    static String KEY="ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";
    private String path ="http://gateway.marvel.com/v1/public/comics/";
    private TextView descripcion,titulo,fecha,paginas,listadoseries,listadocreadores,listadopersonajes;
    private ImageView imagen;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        descripcion=(TextView)findViewById(R.id.DescripcionComic);
        titulo=(TextView)findViewById(R.id.TituloCom);
        fecha=(TextView)findViewById(R.id.fecha);
        paginas=(TextView)findViewById(R.id.paginas);
        listadoseries=(TextView)findViewById(R.id.listado1);
        listadocreadores=(TextView)findViewById(R.id.listado2);
        listadopersonajes=(TextView)findViewById(R.id.listado3);
        imagen=(ImageView)findViewById(R.id.imagenCom);
        String id= (String) getIntent().getExtras().get("id");
        url=path+id+"?"+KEY;

        Log.d("id de imagen",id);
        ColaPeticion= Volley.newRequestQueue(this);
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET,url,

                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (response != null) {

                                JSONObject json = response.getJSONObject("data");

                                JSONArray ListadoComics = json.getJSONArray("results");


                                JSONObject Jsoncomic = (JSONObject) ListadoComics.get(0);
                                String Atributo = "";

                                //obtengo el titulo del comic

                                Atributo = Jsoncomic.getString("title");
                                titulo.setText(Atributo);
                                //Log.d("datos de objeto",obj1.toString());

                                descripcion.setText(Jsoncomic.getString("description"));
                                //obtengo el precio del comic
                                JSONObject imagen = Jsoncomic.getJSONObject("thumbnail");
                                //JSONObject objetoimagen = imagen.getJSONObject(0);

                                String path = imagen.getString("path");
                                String extension = imagen.getString("extension");
                                String UrlImagen = path + "." + extension + "?" + key;
                                //comic.setUrlimagen(UrlImagen);
                                //comic.setUrlimagen("imagen");
                                cargar(UrlImagen);


                                Atributo = Jsoncomic.getString("pageCount");
                                paginas.setText(Atributo);

                                JSONArray ArrayFecha = Jsoncomic.getJSONArray("dates");
                                JSONObject Objetofecha = (JSONObject) ArrayFecha.get(0);

                                //String typo = Objetofecha.getString("type");
                                String fechas = Objetofecha.getString("date");
                                fecha.setText(fechas);

                                JSONObject serie = Jsoncomic.getJSONObject("series");
                                //JSONObject objetoimagen = imagen.getJSONObject(0);

                                String ser = serie.getString("name");
                                listadoseries.setText("Series :" + ser);


                                JSONObject chara = Jsoncomic.getJSONObject("characters");
                                //JSONObject objetoimagen = imagen.getJSONObject(0);
                                JSONArray item = chara.getJSONArray("items");

                                String text = "Personajes :";
                                for (int i = 0; i < item.length(); i++) {
                                    JSONObject fech = (JSONObject) item.get(i);
                                String    tex = " " + fech.get("name") + " \n";
                                    text=text+tex;

                                    Log.d("tamaño de personajes",String.valueOf( item.length()));
                                    Log.d("Personajes",text);
                                }
                                listadopersonajes.setText(text);

                                JSONObject crea = Jsoncomic.getJSONObject("creators");
                                //JSONObject objetoimagen = imagen.getJSONObject(0);
                                JSONArray ite = crea.getJSONArray("items");

                                String cread = "Creadores :";
                                for (int i = 0; i < ite.length(); i++) {
                                    JSONObject cre = (JSONObject) ite.get(i);
                                   String creadores = "rol :" + cre.get("role") + "  nombre :" + cre.get("name") + " \n";
                                   cread=cread +creadores;
                                }
                                listadocreadores.setText(cread);


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

      public void cargar(String url){

          Glide.with(this)
                  .load(url)
                  //.centerCrop()
                  //.placeholder(R.drawable.com_facebook_profile_picture_blank_square)
                  //.diskCacheStrategy(DiskCacheStrategy.ALL)
                  .into(imagen);
      }

    }

