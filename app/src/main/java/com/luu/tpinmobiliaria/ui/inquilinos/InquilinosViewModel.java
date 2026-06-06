package com.luu.tpinmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
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

public class InquilinosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inmueble>> mListaInmuebles;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Inmueble>> getMListaInmuebles() {
        if (mListaInmuebles == null) {
            mListaInmuebles = new MutableLiveData<>();
        }
        return mListaInmuebles;
    }

    public void cargarInmueblesAlquilados() {
        String token = ApiClient.obtenerToken(context);

        if (token.isEmpty()) {
            Toast.makeText(context, "Token no válido. Inicie sesión nuevamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        String tokenBearer = "Bearer " + token;

        ApiClient.getServicio().obtenerInmueblesAlquilados(tokenBearer).enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListaInmuebles.setValue(response.body());
                } else {
                    Toast.makeText(context, "Error al obtener inmuebles con inquilinos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}