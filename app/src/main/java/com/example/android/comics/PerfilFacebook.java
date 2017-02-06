package com.example.android.comics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilFacebook extends AppCompatActivity {


    TextView nombre, apellido ,email,cumple,ubicacion,genero;
    ImageView foto;
    Button sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_facebook);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil de Facebook");

        nombre=(TextView)findViewById(R.id.nombre);
        email=(TextView)findViewById(R.id.email);
        genero=(TextView)findViewById(R.id.genero);
        cumple=(TextView)findViewById(R.id.cumple);
        //ubicacion=(TextView)findViewById(R.id.ubicacion);
        foto=(ImageView) findViewById(R.id.foto);
        sesion=(Button)findViewById(R.id.sesion);
        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar();
            }
        });

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }

        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            Uri photoUrl = profile.getProfilePictureUri(400,400);
            nombre.setText(profile.getFirstName()+" "+profile.getLastName());

            Glide.with(this).load(photoUrl).into(foto);

            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        email.setText( "E-mail : "+ object.getString("email"));
                        Log.d("sexo",object.getString("gender"));
                        if(object.getString("gender")!="female"){
                           genero.setText("Sexo : Masculino");
                        }else {
                            genero.setText("Sexo : Femenino");
                        }

                        cumple.setText( "Nacimiento : "+object.getString("birthday"));
                       // ubicacion.setText( object.getString("location"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // ya tenemos el email aquí
                }
            });
            Bundle parameters = new Bundle();
// Solicitamos el email y otro datos
            parameters.putString("fields", "email, gender,birthday,location");
            request.setParameters(parameters);
            request.executeAsync();

        } else {
            ProfileTracker profileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                    stopTracking();
                    Profile.setCurrentProfile(currentProfile);
                    // Desde aquí ya se puede obtener la información como en el caso de arriba
                    Profile profile = Profile.getCurrentProfile();
                    Uri photoUrl = profile.getProfilePictureUri(400,400);
                    nombre.setText(profile.getFirstName());
                    apellido.setText(profile.getLastName());

                }
            };
            profileTracker.startTracking();
        }

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void cerrar(){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }


    }


