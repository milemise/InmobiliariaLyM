package com.luu.tpinmobiliaria.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.SupportMapFragment;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this)
                .get(InicioViewModel.class);

        viewModel.getMutableMapaActual().observe(
                getViewLifecycleOwner(),
                new Observer<InicioViewModel.MapaActual>() {
                    @Override
                    public void onChanged(InicioViewModel.MapaActual mapaActual) {

                        SupportMapFragment mapFragment =
                                (SupportMapFragment) getChildFragmentManager()
                                        .findFragmentById(R.id.map);

                        if (mapFragment != null) {
                            mapFragment.getMapAsync(mapaActual);
                        }
                    }
                });

        viewModel.cargarMapa();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

//Elimine implements y OnMapReady() y onMapReadyCallback
//Solamente objerva el liveData y ejecuta