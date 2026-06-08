package com.luu.tpinmobiliaria.ui.inicio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioViewModel extends AndroidViewModel {

    private MutableLiveData<MapaActual> mutableMapaActual;

    public InicioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MapaActual> getMutableMapaActual() {
        if (mutableMapaActual == null) {
            mutableMapaActual = new MutableLiveData<>();
        }
        return mutableMapaActual;
    }

    public void cargarMapa() {
        MapaActual mapaActual = new MapaActual();
        mutableMapaActual.setValue(mapaActual);
    }

    public class MapaActual implements OnMapReadyCallback {

        LatLng ULP = new LatLng(-33.150783, -66.320339);

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            googleMap.clear();

            googleMap.setBuildingsEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            googleMap.addMarker(
                    new MarkerOptions()
                            .position(ULP)
                            .title("Universidad de La Punta")
            );

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(ULP)
                    .zoom(18)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate cameraUpdate =
                    CameraUpdateFactory.newCameraPosition(cameraPosition);

            googleMap.animateCamera(cameraUpdate);
        }
    }
}

//Cree la clase interna MapaActual