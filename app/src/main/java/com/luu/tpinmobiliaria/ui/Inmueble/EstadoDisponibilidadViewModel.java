package com.luu.tpinmobiliaria.ui.Inmueble;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.EstadoDisponibilidad;

public class EstadoDisponibilidadViewModel {

    private MutableLiveData<EstadoDisponibilidad> mDisponible;

    public LiveData<EstadoDisponibilidad> getDisponible() {
        if (mDisponible == null) {
            mDisponible = new MutableLiveData<>();
        }
        return mDisponible;
    }

    public void setDisponible(boolean disponible) {

        EstadoDisponibilidad estado;

        if (disponible) {
            estado = new EstadoDisponibilidad(
                    "Disponible",
                    R.drawable.outline_check_circle_24,
                    R.drawable.bg_disponible,
                    true
            );
        } else {
            estado = new EstadoDisponibilidad(
                    "No Disponible",
                    R.drawable.outline_cancel_24,
                    R.drawable.bg_no_disponible,
                    false
            );
        }

        mDisponible.setValue(estado);
    }
}
