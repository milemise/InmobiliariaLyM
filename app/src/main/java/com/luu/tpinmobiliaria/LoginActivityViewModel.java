package com.luu.tpinmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.luu.tpinmobiliaria.MainActivity;
import com.luu.tpinmobiliaria.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Boolean> mLoginExitoso;
    private MutableLiveData<Intent> mIntentLlamada;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

        mLoginExitoso = new MutableLiveData<>();
        mIntentLlamada = new MutableLiveData<>();
    }

    public LiveData<Boolean> getMLoginExitoso() {
        if (mLoginExitoso == null) {
            mLoginExitoso = new MutableLiveData<>();
        }
        return mLoginExitoso;
    }

    public LiveData<Intent> getMIntentLlamada() {
        if (mIntentLlamada == null) {
            mIntentLlamada = new MutableLiveData<>();
        }
        return mIntentLlamada;
    }

    public void loguear(String email, String password) {
        String usuario = email.trim();
        String clave = password.trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            Toast.makeText(context, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiClient.getServicio().loginForm(usuario, clave).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body();
                    ApiClient.recuperarToken(context, token);
                    Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show();
                    mLoginExitoso.setValue(true);
                } else {
                    Toast.makeText(context, "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void resetear(String email) {
        String correo = email.trim();
        if (correo.isEmpty()) {
            Toast.makeText(context, "Por favor, ingrese su correo electrónico para restablecer la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(context, "Se ha enviado un correo de recuperación a: " + correo, Toast.LENGTH_LONG).show();
    }

    public void verificarAgite(float x, float y, float z, float aceleracionUltima, float aceleracionActual, float agite) {
        float delta = aceleracionActual - aceleracionUltima;
        float nuevoAgite = agite * 0.9f + delta;

        if (nuevoAgite > 12) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:2657000000"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mIntentLlamada.setValue(intent);
        }
    }


}