package com.example.android.comics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {


    private Button boton;
    private GoogleApiClient mGoogleApiClient;
    private RecyclerView recycler;
    private AdaptadorRecycler adaptador;
    private ControladorDatos datos;
    Toolbar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barra=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(barra);

        recycler = (RecyclerView) findViewById(R.id.listaComics);

        recycler.setClickable(true);

// Usar un administrador para LinearLayout
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
       // datos=new ControladorDatos(this);
        //ArrayList<Comic> lista=datos.getListadaComics();
        //for(int i=0;1<lista.size();i++){

          //  Log.d("titulo",lista.get(i).getTitulo());
           // Log.d("titulo",lista.get(i).getPrecio());
            //Log.d("titulo",lista.get(i).getUrlimagen());
        adaptador = new AdaptadorRecycler(this);
        recycler.setAdapter(adaptador);


        }

       /* boton=(Button)findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarPerfil();
            }
        });*/


        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        boolean conexion = mGoogleApiClient.isConnected();
      */
        //configuracion de boton de google
        // SignInButton signInButton = (SignInButton) findViewById(R.id.loginGoogle);
        //signInButton.setSize(SignInButton.SIZE_STANDARD);
        //signInButton.setScopes(new Scope[]{Plus.SCOPE_PLUS_LOGIN});
       // Log.d("CONExION",String.valueOf(conexion));

      /*  if ((AccessToken.getCurrentAccessToken() == null)){
            goLoginScreen();
        }*/



    private void goLoginScreen() {
        Intent intent = new Intent(this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    public  void llamarPerfil(){
        Intent intent = new Intent(this,PerfilFacebook.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);

        final MenuItem item = menu.findItem(R.id.action_refresh);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                adaptador.refrescar();
                return false;
            }
        });

        final MenuItem itemperfil = menu.findItem(R.id.action_perfil);
        itemperfil.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                llamarPerfil();
                return true;
            }
        });

        final MenuItem itemsesion = menu.findItem(R.id.action_sing_out);
        itemsesion.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                logout();
                return true;
            }
        });

        //final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);



        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint("Buscar");
        //maneja los eventos que suceden en la caja de busqueda , escucha los eventos de
        //modificacion de texto y el de envio de busqueda
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //cuando se envia el texto a buscar
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "buscando", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            //cuando se modifica el texto
            @Override
            public boolean onQueryTextChange(String consulta) {
                adaptador.getFilter().filter(consulta);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

}

