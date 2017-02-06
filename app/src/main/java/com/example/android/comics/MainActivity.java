package com.example.android.comics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {


    private Button boton;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        if ((AccessToken.getCurrentAccessToken() == null)){
            goLoginScreen();
        }

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    public  void llamarPerfil(){
        Intent intent = new Intent(this,PerfilFacebook.class);
        startActivity(intent);
    }

}

