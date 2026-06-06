package com.luu.tpinmobiliaria.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.luu.tpinmobiliaria.databinding.FragmentContratosBinding;

public class ContratosFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratosViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(ContratosViewModel.class);

        binding.rvContratos.setLayoutManager(new LinearLayoutManager(getContext()));

        vm.getMListaInmuebles().observe(getViewLifecycleOwner(), lista -> {
            ContratosAdapter adapter = new ContratosAdapter(lista, getContext(), getLayoutInflater());
            binding.rvContratos.setAdapter(adapter);
        });

        vm.cargarInmueblesConContrato();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}