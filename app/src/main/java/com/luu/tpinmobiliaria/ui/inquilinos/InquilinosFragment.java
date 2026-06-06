package com.luu.tpinmobiliaria.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.luu.tpinmobiliaria.databinding.FragmentInquilinosBinding;
import com.luu.tpinmobiliaria.models.Inmueble;
import java.util.List;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;
    private InquilinosViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding.rvInquilinos.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getMListaInmuebles().observe(getViewLifecycleOwner(), listaInmuebles -> {
            InquilinosAdapter adapter = new InquilinosAdapter(listaInmuebles, getContext(), inflater);
            binding.rvInquilinos.setAdapter(adapter);
        });

        viewModel.cargarInmueblesAlquilados();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}