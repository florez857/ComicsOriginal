package com.example.android.comics;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.List;

import static android.R.attr.key;

/**
 * Created by INTEL on 06/02/2017.
 */

public class ControladorDatos {

    private Comic comic;
    private List<Comic> ListadoComics;
    private RequestQueue ColaPeticion;
    private Context Contexto;
    private String JsonURL="http://gateway.marvel.com/v1/public/comics?ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";
    static String KEY="ts=1&apikey=0edf0dea162612b738bd9da490aa4a18&hash=8df4c36e0ab8a326763ee2fecd804b39";


    public ControladorDatos(Context contexto){

        this.Contexto=contexto;

    }



      public void cargar(Context contexto){

// se crea una cola de peticiones de volley
         ColaPeticion= Volley.newRequestQueue(contexto);

        // Casts results into the TextView found within the main layout XML with id jsonData
        //results = (TextView) findViewById(R.id.texto);
        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        //creamos una peticon de un objeto json  ,se define el metodo , GET , se pasa la URL para consultar jsonUrl
        //y se crea un oyente de la respuesta , donde se devuelve el resultado de la consulta ,se debe sobrescribir el metodo
        //on respose que tiene nuestro datos ,se lo pasa con un objeto respose

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        String data="",id="";
                        Comic comic;


                        try {

                            // Retrieves the string labeled "colorName" and "description" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            //JSONArray   array=  obj.getJSONArray("results");
                            //array.get(1).toString();
                          if (response!=null){

                             /*
                              ListadoComics=new ArrayList<>();

                              JSONObject json = response.getJSONObject("data");

                              JSONArray ListadoComics=json.getJSONArray("results");

                              for(int i=0;i<ListadoComics.length();i++){

                                    comic=new Comic();
                                    JSONObject Jsoncomic= (JSONObject)ListadoComics.get(i);
                                    String Atributo="";

                                    //obtengo el titulo del comic
                                    Atributo=Jsoncomic.getString("title");
                                    comic.setTitulo(Atributo);
                                    //Log.d("datos de objeto",obj1.toString());

                                    //obtengo el precio del comic
                                    JSONArray imagen=Jsoncomic.getJSONArray("images");
                                    String path=imagen.getString("path");
                                    String extension=imagen.getString("extension");
                                    String UrlImagen=path+"."+extension+key;








                                JSONArray img =obj1.getJSONArray("images");
                                JSONObject thum=(JSONObject) img.get(0);
                                String path= thum.getString("path");
                                String extension= thum.getString("extension");
                                String url=path+"."+extension+"?"+KEY;

                            }


*/
    /*
                            Log.d("datos de jsno", obj.toString());

                            String color = obj.getString("limit");
                            String desc = obj.getString("total");
                            String count=obj.getString("offset");



                            // Adds strings from object to the "data" string
                            data += "id Name: " + color +
                                    "title : " + desc+ "offset "+count+"id "+id + "tamaño de array"+array.length() ;  ; //"tamaño de array"+array.length()+"datos"+array.get(1).toString();;

                            // Adds the data string to the TextView "results"
                            results.setText(data);
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
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
    }

    }


    }

*/


}
