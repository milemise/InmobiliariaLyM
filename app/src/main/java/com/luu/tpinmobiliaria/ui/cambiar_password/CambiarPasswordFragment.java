package com.luu.tpinmobiliaria.ui.cambiar_password;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.luu.tpinmobiliaria.databinding.FragmentCambiarPasswordBinding;

public class CambiarPasswordFragment extends Fragment {

    private CambiarPasswordViewModel vm;
    private FragmentCambiarPasswordBinding b;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentCambiarPasswordBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(CambiarPasswordViewModel.class);

        b.btnGuardarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.procesarCambioClave(
                        b.etClaveActual.getText().toString(),
                        b.etClaveNueva.getText().toString(),
                        b.etClaveRepetir.getText().toString()
                );
            }
        });

        return b.getRoot();
    }
}