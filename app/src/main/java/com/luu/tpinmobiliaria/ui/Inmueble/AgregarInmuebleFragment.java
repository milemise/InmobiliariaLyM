package com.luu.tpinmobiliaria.ui.Inmueble;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.luu.tpinmobiliaria.databinding.FragmentAgregarInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private FragmentAgregarInmuebleBinding b;
    private AgregarInmuebleViewModel vm;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        vm.procesarImagenSeleccionada(uri);
                    }
                }
        );

        vm.getMImagenUri().observe(getViewLifecycleOwner(), uri -> {
            b.ivFotoAgregar.setImageURI(uri);
        });

        vm.getMGuardadoExitoso().observe(getViewLifecycleOwner(), guardado -> {
            if (guardado) {
                Navigation.findNavController(requireView()).popBackStack();
            }
        });

        b.ivFotoAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        b.btnGuardarInmueble.setOnClickListener(v -> {
            vm.guardarInmueble(b.etDireccion, b.etAmbientes, b.etPrecio, b.etUso, b.etTipo, b.cbDisponibleForm);
        });

        return b.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        b = null;
    }
}