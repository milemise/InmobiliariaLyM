package com.luu.tpinmobiliaria.ui.contratos;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.luu.tpinmobiliaria.models.Inmueble;
import com.luu.tpinmobiliaria.request.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> mListaInmuebles;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getMListaInmuebles() {
        if (mListaInmuebles == null) {
            mListaInmuebles = new MutableLiveData<>();
        }
        return mListaInmuebles;
    }

    public void cargarInmueblesConContrato() {
        String token = ApiClient.obtenerToken(getApplication());
        if (token == null || token.isEmpty()) {
            Toast.makeText(getApplication(), "Token no válido", Toast.LENGTH_LONG).show();
            return;
        }

        String tokenBearer = "Bearer " + token;

        ApiClient.getServicio().obtenerInmueblesAlquilados(tokenBearer).enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListaInmuebles.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "Error al obtener inmuebles alquilados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}