package com.undead.nosavvy.lab_calif_network;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.undead.nosavvy.lab_calif_network.adapters.DenunciaAdapter;
import com.undead.nosavvy.lab_calif_network.interfaces.ApiService;
import com.undead.nosavvy.lab_calif_network.interfaces.ApiServiceGenerator;
import com.undead.nosavvy.lab_calif_network.models.Denuncia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = DashboardActivity.class.getSimpleName();

    private String idciudadano;
    private RecyclerView recyclerView;
    ApiService service = ApiServiceGenerator.createService(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        idciudadano = String.valueOf(getIntent().getExtras().getString("idciudadano"));
        setRecyclerView((RecyclerView) findViewById(R.id.recyclerView));
        getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        getRecyclerView().setAdapter(new DenunciaAdapter());
        getDenuncias(idciudadano);
        findViewById(R.id.crearDenuncia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new  Intent(DashboardActivity.this,RegisterDenunciaActivity.class);
                i.putExtra("idciudadano",idciudadano);
                startActivity(i);
            }
        });
    }


    public void getDenuncias(String idciudadano){
        Call<List<Denuncia>> call = service.getDenuncias(idciudadano);
        call.enqueue(new Callback<List<Denuncia>>() {
            @Override
            public void onResponse(Call<List<Denuncia>> call, Response<List<Denuncia>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);


                    if (response.isSuccessful()) {

                        List<Denuncia> denuncia = response.body();
                        Log.d(TAG, "denuncia: " + denuncia.size());

                        Log.d(TAG, "denuncia: " + denuncia.toString());
                        if(denuncia.size() == 0){
                            Toast.makeText(DashboardActivity.this,"No tiene denuncias", Toast.LENGTH_SHORT).show();
                        }else {
                            DenunciaAdapter adapter = (DenunciaAdapter) getRecyclerView().getAdapter();
                            adapter.setDenuncias(denuncia);
                            adapter.notifyDataSetChanged();
                        }

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Usuario o Contraseña incorrectos");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Denuncia>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void refreshList(){
        getRecyclerView().removeAllViews();
        getDenuncias(idciudadano);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
