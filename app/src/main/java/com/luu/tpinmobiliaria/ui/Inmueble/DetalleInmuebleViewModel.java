package com.luu.tpinmobiliaria.ui.Inmueble;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luu.tpinmobiliaria.models.EstadoDisponibilidad;
import com.luu.tpinmobiliaria.models.Inmueble;
import com.luu.tpinmobiliaria.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<String> mUrlImagen;
    private MutableLiveData<EstadoDisponibilidad> mDisponible;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getMInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public LiveData<String> getMUrlImagen() {
        if (mUrlImagen == null) {
            mUrlImagen = new MutableLiveData<>();
        }
        return mUrlImagen;
    }

    public LiveData<EstadoDisponibilidad> getMDisponible() {
        if (mDisponible == null) {
            mDisponible = new MutableLiveData<>();
        }
        return mDisponible;
    }

    public void recuperarDatos(Bundle bundle) {
        if (bundle != null) {
            Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            if (inmueble != null) {
                mInmueble.setValue(inmueble);
                setDisponible(inmueble.getDisponible());
                mUrlImagen.setValue(ApiClient.BASE_URL + inmueble.getImagenUrl());
            }
        }
    }

    public void setDisponible(boolean disponible) {
        EstadoDisponibilidad ed;
        if (disponible) {
            ed = new EstadoDisponibilidad(
                    "Disponible",
                    android.R.drawable.checkbox_on_background,
                    android.R.color.holo_green_light,
                    true
            );
        } else {
            ed = new EstadoDisponibilidad(
                    "No Disponible",
                    android.R.drawable.checkbox_off_background,
                    android.R.color.holo_red_light,
                    false
            );
        }
        mDisponible.setValue(ed);
    }

    public void cambiarDisponibilidad(boolean disponible) {
        Toast.makeText(
                getApplication(),
                "Disponible: " + disponible,
                Toast.LENGTH_SHORT
        ).show();

        Inmueble i = mInmueble.getValue();

        if (i == null) return;

        i.setDisponible(disponible);

        String token = "Bearer " + ApiClient.obtenerToken(getApplication());

        ApiClient.getServicio()
                .actualizarInmueble(token, i)
                .enqueue(new Callback<Inmueble>() {

                    @Override
                    public void onResponse(Call<Inmueble> call,
                                           Response<Inmueble> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            mInmueble.setValue(response.body());
                            setDisponible(response.body().getDisponible());

                        } else {

                            Toast.makeText(
                                    getApplication(),
                                    "Código: " + response.code(),
                                    Toast.LENGTH_LONG
                            ).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Inmueble> call, Throwable t) {
                        Toast.makeText(
                                getApplication(),
                                "Error de red: " + t.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}