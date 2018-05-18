package com.undead.nosavvy.lab_calif_network.network;

import android.util.Log;
import android.widget.Toast;

import com.undead.nosavvy.lab_calif_network.interfaces.ApiService;
import com.undead.nosavvy.lab_calif_network.interfaces.ApiServiceGenerator;
import com.undead.nosavvy.lab_calif_network.models.Denuncia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nosavvy on 5/16/18.
 */

public class ApiServiceImpl {

    ApiService service = ApiServiceGenerator.createService(ApiService.class);
    private final String TAG = this.getClass().getSimpleName();
    public List<Denuncia> getDenuncias(String idciudadano){
        List<Denuncia> denuncias = null;
        Call<List<Denuncia>> call = service.getDenuncias(idciudadano);
        call.enqueue(new Callback<List<Denuncia>>() {
            @Override
            public void onResponse(Call<List<Denuncia>> call, Response<List<Denuncia>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() > 0){
                        List<Denuncia> denuncias = response.body();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Denuncia>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return denuncias;
    }

}
