package com.luu.tpinmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.luu.tpinmobiliaria.models.Propietario;
import com.luu.tpinmobiliaria.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        viewModel.getPropietario().observe(getViewLifecycleOwner(), propietario -> {
            binding.etId.setText(String.valueOf(propietario.getId()));
            binding.etDni.setText(String.valueOf(propietario.getDni()));
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etEmail.setText(propietario.getEmail());
            binding.etTelefono.setText(propietario.getTelefono());
            binding.etClave.setText(propietario.getClave());
        });

        viewModel.getEditable().observe(getViewLifecycleOwner(), aBoolean -> {
            binding.etNombre.setEnabled(aBoolean);
            binding.etApellido.setEnabled(aBoolean);
            binding.etEmail.setEnabled(aBoolean);
            binding.etTelefono.setEnabled(aBoolean);

            if (aBoolean) {
                binding.btnEditar.setText("GUARDAR");
            } else {
                binding.btnEditar.setText("EDITAR");
            }
        });

        viewModel.obtenerDatos();

        binding.btnEditar.setOnClickListener(v -> {
            String id = binding.etId.getText().toString();
            String dni = binding.etDni.getText().toString();
            String nombre = binding.etNombre.getText().toString();
            String apellido = binding.etApellido.getText().toString();
            String email = binding.etEmail.getText().toString();
            String telefono = binding.etTelefono.getText().toString();
            String clave = binding.etClave.getText().toString();

            viewModel.gestionarBotonEditar(id, dni, nombre, apellido, email, telefono, clave);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}