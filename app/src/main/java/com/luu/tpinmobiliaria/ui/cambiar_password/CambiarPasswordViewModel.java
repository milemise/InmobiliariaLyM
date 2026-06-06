package com.luu.tpinmobiliaria.ui.cambiar_password;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.luu.tpinmobiliaria.request.ApiClient;

import retrofit2.Call;

public class CambiarPasswordViewModel extends AndroidViewModel {

    public CambiarPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    public void procesarCambioClave(String actual,
                                    String nueva,
                                    String repetir) {

        if (actual.isEmpty() ||
                nueva.isEmpty() ||
                repetir.isEmpty()) {

            Toast.makeText(getApplication(),
                    "Todos los campos son obligatorios",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!nueva.equals(repetir)) {

            Toast.makeText(getApplication(),
                    "Las nuevas contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String token =
                "Bearer " + ApiClient.obtenerToken(getApplication());

        ApiClient.getServicio()
                .cambiarPassword(token, actual, nueva)
                .enqueue(new retrofit2.Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call,
                                           retrofit2.Response<Void> response) {

                        if (response.isSuccessful()) {

                            Toast.makeText(getApplication(),
                                    "Contraseña actualizada",
                                    Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplication(),
                                    "Contraseña incorrecta",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(getApplication(),
                                "Error de conexión",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}