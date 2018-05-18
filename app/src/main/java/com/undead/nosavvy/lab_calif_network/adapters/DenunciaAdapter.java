package com.undead.nosavvy.lab_calif_network.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.undead.nosavvy.lab_calif_network.DashboardActivity;
import com.undead.nosavvy.lab_calif_network.R;
import com.undead.nosavvy.lab_calif_network.interfaces.ApiService;
import com.undead.nosavvy.lab_calif_network.interfaces.ApiServiceGenerator;
import com.undead.nosavvy.lab_calif_network.models.Denuncia;
import com.undead.nosavvy.lab_calif_network.network.ApiServiceImpl;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nosavvy on 5/15/18.
 */

public class DenunciaAdapter extends RecyclerView.Adapter<DenunciaAdapter.ViewHolder> {

    private final String TAG = Denuncia.class.getSimpleName();
    private List<Denuncia> denuncias;

    public DenunciaAdapter(){
        this.denuncias = new ArrayList<>();
    }

    public void setDenuncias(List<Denuncia> denuncias){
        this.denuncias = denuncias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView fotoImage;
        public TextView titulo;
        public TextView descripcion;
        public Button btneliminar;
        public TextView id;
        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto);
            titulo = itemView.findViewById(R.id.titulo);
            descripcion = itemView.findViewById(R.id.descripcion);
            btneliminar = itemView.findViewById(R.id.btneliminar);
        }
    }

    @Override
    public DenunciaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denuncia, parent, false);
        return new ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(final DenunciaAdapter.ViewHolder holder, final int position) {
        final Denuncia denuncia = this.denuncias.get(position);
        holder.titulo.setText(denuncia.getTitulo());
        holder.descripcion.setText(denuncia.getDescripcion());

        String url = ApiService.BASE_URL + "images/" + denuncia.getFoto();

        Log.e(TAG,url);

        Picasso.with(holder.itemView.getContext()).load(url).into(holder.fotoImage);

      //  holder.fotoImage.setImageResource(R.drawable.ic_launcher_background);
        holder.btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.e(TAG,"inside eliminar");
                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("¿Estás seguro de eliminar esta denuncia?")
                        .setMessage("Al eliminarlo de tu menú, pasará a ser archivado oficialmente")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                Log.e(TAG,"inside yes clicked");
                                ApiService service = ApiServiceGenerator.createService(ApiService.class);
                                Call<String> call = service.deleteDenuncia(String.valueOf(denuncia.getIddenuncia()));
                                call.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        try{
                                            if(response.isSuccessful()){
                                                Log.d(TAG,response.body().toString());
                                                /*DashboardActivity a = new DashboardActivity();
                                                a.refreshList();*/
                                                DashboardActivity a = new DashboardActivity();
                                                DenunciaAdapter x = (DenunciaAdapter) a.getRecyclerView().getAdapter();
                                                ApiServiceImpl api = new ApiServiceImpl();
                                                x.setDenuncias(api.getDenuncias(String.valueOf(denuncia.getCiudadano_idciudadano())));
                                                //x.setDenuncias();
                                                x.notifyDataSetChanged();
                                                Toast.makeText(v.getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                                            }
                                        }catch (Throwable t){
                                                Log.e(TAG,t.getMessage());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.e(TAG, t.getMessage());
                                        Toast.makeText(v.getContext(), "Error de conexión. Inténtelo más tarde",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.denuncias.size();
    }
}
