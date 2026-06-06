package com.luu.tpinmobiliaria.ui.Inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.databinding.FragmentInmueblesBinding;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding binding;
    private InmueblesViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding.rvInmuebles.setLayoutManager(new LinearLayoutManager(getContext()));

        vm.getMListaInmuebles().observe(getViewLifecycleOwner(), lista -> {
            InmueblesAdapter adapter = new InmueblesAdapter(lista, getContext(), getLayoutInflater());
            binding.rvInmuebles.setAdapter(adapter);
        });

        binding.fabAgregarInmueble.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_inmuebles_to_nav_agregar_inmueble);
        });

        vm.cargarInmuebles();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}