package com.luu.tpinmobiliaria.ui.inicio;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.maps.model.LatLng;

public class InicioViewModel extends AndroidViewModel {

    private MutableLiveData<LatLng> mUbicacion;

    public InicioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<LatLng> getMUbicacion() {
        if (mUbicacion == null) {
            mUbicacion = new MutableLiveData<>();
        }
        return mUbicacion;
    }

    public void cargarUbicacion() {
        LatLng ulp = new LatLng(-33.150783, -66.320339);
        mUbicacion.setValue(ulp);
    }
}