package com.luu.tpinmobiliaria.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.Inmueble;

public class ContratoDetalleFragment extends Fragment {

    private TextView tvInquilino, tvFechaInicio, tvFechaFin, tvMonto;
    private Button btnVerPagos;
    private ContratoDetalleViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_contrato, container, false);

        tvInquilino = view.findViewById(R.id.tvInquilino);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFin = view.findViewById(R.id.tvFechaFin);
        tvMonto = view.findViewById(R.id.tvMonto);
        btnVerPagos = view.findViewById(R.id.btnVerPagos);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);

        Inmueble inmueble =
                (Inmueble) getArguments().getSerializable("inmueble");

        viewModel.getMContrato().observe(getViewLifecycleOwner(), contrato -> {

            tvInquilino.setText(
                    contrato.getInquilino().getNombre()
                            + " "
                            + contrato.getInquilino().getApellido());

            tvFechaInicio.setText(contrato.getFechaInicio());
            tvFechaFin.setText(contrato.getFechaFinalizacion());
            tvMonto.setText("$" + String.format("%.0f", contrato.getMontoAlquiler()));

            btnVerPagos.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("idContrato", contrato.getIdContrato());

                Navigation.findNavController(view)
                        .navigate(R.id.pagosFragment, bundle);
            });
        });

        if(inmueble != null){
            viewModel.cargarContrato(inmueble.getId());
        }
    }
}