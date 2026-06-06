package com.luu.tpinmobiliaria.ui.logout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.luu.tpinmobiliaria.LoginActivity;
import com.luu.tpinmobiliaria.R;

public class LogoutFragment extends Fragment {

    private LogoutViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        viewModel = new ViewModelProvider(this).get(LogoutViewModel.class);

        viewModel.getMCerrarSesion().observe(getViewLifecycleOwner(), debeCerrar -> {
            if (debeCerrar) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        new AlertDialog.Builder(getContext())
                .setTitle("Cierre de sesión")
                .setMessage("¿Está seguro de que desea cerrar la sesión?")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    viewModel.cerrarSesion();
                })
                .setNegativeButton("Cancelar", (dialog, id) -> {
                })
                .show();

        return root;
    }
}