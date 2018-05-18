package com.undead.nosavvy.lab_calif_network.interfaces;

/**
 * Created by nosavvy on 5/11/18.
 */

import com.undead.nosavvy.lab_calif_network.models.Ciudadano;
import com.undead.nosavvy.lab_calif_network.models.Denuncia;
import com.undead.nosavvy.lab_calif_network.models.LoginResponse;
import com.undead.nosavvy.lab_calif_network.models.ResponseMessage;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    String BASE_URL = "https://ide50-sicked-sunday.cs50.io:8080/";
    String API_BASE_URL = "https://ide50-sicked-sunday.cs50.io:8080/api/";
    @POST("login")
    Call<Ciudadano> login(@Body Ciudadano login);

    @GET("denuncias/{idciudadano}")
    Call<List<Denuncia>> getDenuncias(@Path("idciudadano") String idciudadano);

    @FormUrlEncoded
    @POST("denuncia/destroy")
    Call<String> deleteDenuncia(@Field("iddenuncia") String iddenuncia);

    @POST("denuncia/store")
    Call<String> createDenuncia(@Body Denuncia denuncia);

    @FormUrlEncoded
    @POST("denuncia/store")
    Call<ResponseMessage> createDenuncia(@Field("titulo") String titulo,
                                         @Field("descripcion") String descripcion,
                                         @Field("idciudadano") String idciudadano,
                                         @Field("ubicacion") String ubicacion);
    @Multipart
    @POST("denuncia/store")
    Call<ResponseMessage> createDenunciaWithPhoto(
            @Part("titulo") RequestBody titulo,
            @Part("descripcion") RequestBody descripcion,
            @Part("idciudadano") RequestBody idciudadano,
            @Part("ubicacion") RequestBody ubicacion,
            @Part MultipartBody.Part foto
    );
}
