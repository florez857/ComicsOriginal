package com.example.android.comics;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.key;

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

        this.Contexto=contexto;
        ListadaComics=new ArrayList<>();

    }

    public ArrayList<Comic> getListadaComics() {
        return ListadaComics;
    }

    public void setListadaComics(ArrayList<Comic> listadaComics) {
        ListadaComics = listadaComics;
    }

    public void cargar(Context contexto){

        // se crea una cola de peticiones de volley
         ColaPeticion= Volley.newRequestQueue(contexto);

        //creamos una peticon de un objeto json  ,se define el metodo , GET , se pasa la URL para consultar jsonUrl
        //y se crea un oyente de la respuesta , donde se devuelve el resultado de la consulta ,se debe sobrescribir el metodo
        //on respose que tiene nuestro datos ,se lo pasa con un objeto respose

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

                              }

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
        // Adds the JSON object request "obreq" to the request queue
        ColaPeticion.add(obreq);
    }

    }






