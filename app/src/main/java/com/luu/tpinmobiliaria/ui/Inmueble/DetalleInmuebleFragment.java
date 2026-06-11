package com.luu.tpinmobiliaria.ui.Inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.luu.tpinmobiliaria.databinding.FragmentDetalleInmuebleBinding;
import com.luu.tpinmobiliaria.models.Inmueble;

public class DetalleInmuebleFragment extends Fragment {

    private FragmentDetalleInmuebleBinding binding;
    private DetalleInmuebleViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);

        vm.getMInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            binding.tvCodigo.setText(String.valueOf(inmueble.getId()));
            binding.tvDireccionDetalle.setText(inmueble.getDireccion());
            binding.tvTipo.setText(inmueble.getTipo());
            binding.tvUso.setText(inmueble.getUso());
            binding.tvAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
            binding.tvPrecioDetalle.setText("$ " + inmueble.getPrecio());
        });

        vm.getMUrlImagen().observe(getViewLifecycleOwner(), url -> {
            if (!url.isEmpty()) {
                Glide.with(requireContext()).load(url).into(binding.ivFotoDetalle);
            }
        });

        //CORRECCIONES DE DISPONIBILIDAD

        vm.getMDisponible().observe(getViewLifecycleOwner(), estado -> {

            binding.cbDisponible.setOnCheckedChangeListener(null);

            binding.cbDisponible.setText(estado);

            binding.cbDisponible.setChecked(
                    estado.equals("Disponible")
            );

            binding.cbDisponible.setOnCheckedChangeListener((buttonView, isChecked) -> {
                vm.cambiarDisponibilidad(isChecked);
            });
        });

        if (getArguments() != null) {
            vm.recuperarDatos(getArguments());
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}