package com.luu.tpinmobiliaria.ui.perfil;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.luu.tpinmobiliaria.models.Propietario;
import com.luu.tpinmobiliaria.request.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<Boolean> mEditable;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getPropietario() {
        if (mPropietario == null) {
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
    }

    public LiveData<Boolean> getEditable() {
        if (mEditable == null) {
            mEditable = new MutableLiveData<>();
        }
        return mEditable;
    }

    public void obtenerDatos() {
        String token = "Bearer " + ApiClient.obtenerToken(getApplication());

        ApiClient.getServicio().getPropietario(token).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mPropietario.setValue(response.body());
                } else {
                    Toast.makeText(getApplication(), "Error al obtener perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
        if (mEditable == null) {
            mEditable = new MutableLiveData<>();
        }
        mEditable.setValue(false);
    }

    public void cambiarEstadoEditable(boolean estado) {
        if (mEditable == null) {
            mEditable = new MutableLiveData<>();
        }
        mEditable.setValue(estado);
    }

    public void gestionarBotonEditar(String id, String dni, String nombre, String apellido, String email, String telefono, String clave) {
        if (mEditable != null && mEditable.getValue() != null && mEditable.getValue()) {
            Propietario p = new Propietario();
            p.setId(Integer.parseInt(id));
            p.setDni(dni);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setEmail(email);
            p.setTelefono(telefono);
            p.setClave(clave);

            actualizarDatos(p);
        } else {
            cambiarEstadoEditable(true);
        }
    }

    public void actualizarDatos(Propietario propietario) {
        if (propietario.getNombre().isEmpty() || propietario.getApellido().isEmpty() ||
                propietario.getEmail().isEmpty() || propietario.getTelefono().isEmpty()) {
            Toast.makeText(getApplication(), "No puede dejar campos vacíos", Toast.LENGTH_SHORT).show();
            return;
        }

        String token = "Bearer " + ApiClient.obtenerToken(getApplication());

        ApiClient.getServicio().actualizarPerfil(token, propietario).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    mPropietario.setValue(propietario);
                    mEditable.setValue(false);
                    Toast.makeText(getApplication(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(getApplication(), error, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}