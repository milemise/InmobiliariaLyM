package com.luu.tpinmobiliaria.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.Inmueble;

public class InquilinoDetalleFragment extends Fragment {

    private TextView tvNombre, tvDni, tvTelefono, tvLugarTrabajo, tvNombreGarante, tvDniGarante;
    private InquilinoDetalleViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);

        tvNombre = view.findViewById(R.id.tvNombreInquilino);
        tvDni = view.findViewById(R.id.tvDniInquilino);
        tvTelefono = view.findViewById(R.id.tvTelefonoInquilino);
        tvNombreGarante = view.findViewById(R.id.tvNombreGarante);
        tvDniGarante = view.findViewById(R.id.tvDniGarante);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);

        Inmueble inmueble =
                (Inmueble) getArguments().getSerializable("inmueble");

        viewModel.getMContrato().observe(getViewLifecycleOwner(), contrato -> {

            if (contrato.getInquilino() != null) {

                tvNombre.setText(
                        contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());

                tvDni.setText(
                        String.valueOf(contrato.getInquilino().getDni()));

                tvTelefono.setText(
                        contrato.getInquilino().getTelefono());
            }
        });

        if(inmueble != null){
            viewModel.cargarInquilino(inmueble.getId());
        }
    }


}