package com.undead.nosavvy.lab_calif_network;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.undead.nosavvy.lab_calif_network.interfaces.ApiService;
import com.undead.nosavvy.lab_calif_network.interfaces.ApiServiceGenerator;
import com.undead.nosavvy.lab_calif_network.models.Ciudadano;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText user;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                ApiService service = ApiServiceGenerator.createService(ApiService.class);
                final Ciudadano login = new Ciudadano(username, password);
                Call<Ciudadano> call = service.login(login);

                call.enqueue(new Callback<Ciudadano>() {
                    @Override
                    public void onResponse(Call<Ciudadano> call, Response<Ciudadano> response) {
                        try {

                            int statusCode = response.code();
                            Log.d(TAG, "HTTP status code: " + statusCode);

                            if (response.isSuccessful()) {

                                Ciudadano ciudadano = response.body();
                                Log.d(TAG, "ciudadano: " + ciudadano.toString());
                                Log.d(TAG, ciudadano.getMaterno());
                                Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                                i.putExtra("idciudadano",String.valueOf(ciudadano.getIdciudadano()));
                                startActivity(i);

                            } else {
                                // error controlado por parte de servicio cualquier response distinto a 200
                                Log.e(TAG, "onError: " + response.errorBody().string());
                                throw new Exception("Usuario o Contraseña incorrectos");
                            }

                        } catch (Throwable t) {
                            try {
                                //error de programación
                                Log.e(TAG, "onThrowable: " + t.toString(), t);
                                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }catch (Throwable x){}
                        }
                    }

                    @Override
                    public void onFailure(Call<Ciudadano> call, Throwable t) {
                        // cuando el server responde cualquier cosa menos lo requerido (500 o formato json inválido)
                        Log.e(TAG, "onFailure: " + t.toString());
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



            }
        });



    }
}
