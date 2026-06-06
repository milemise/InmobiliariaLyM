package com.luu.tpinmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luu.tpinmobiliaria.models.Contrato;
import com.luu.tpinmobiliaria.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Contrato> mContrato;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Contrato> getMContrato() {
        if (mContrato == null) {
            mContrato = new MutableLiveData<>();
        }
        return mContrato;
    }

    public void cargarContrato(int idInmueble) {
        String token = ApiClient.obtenerToken(context);
        if (token.isEmpty()) {
            Toast.makeText(context, "Token inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        String tokenBearer = "Bearer " + token;

        ApiClient.getServicio().obtenerContrato(tokenBearer, idInmueble).enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mContrato.setValue(response.body());
                } else {
                    Toast.makeText(context, "No se pudo obtener el contrato", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}