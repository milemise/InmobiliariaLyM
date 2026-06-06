package com.luu.tpinmobiliaria.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.luu.tpinmobiliaria.models.Inmueble;
import com.luu.tpinmobiliaria.request.ApiClient;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Uri> mImagenUri;
    private MutableLiveData<Boolean> mGuardadoExitoso;

    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Uri> getMImagenUri() {
        if (mImagenUri == null) {
            mImagenUri = new MutableLiveData<>();
        }
        return mImagenUri;
    }

    public LiveData<Boolean> getMGuardadoExitoso() {
        if (mGuardadoExitoso == null) {
            mGuardadoExitoso = new MutableLiveData<>();
        }
        return mGuardadoExitoso;
    }

    public void procesarImagenSeleccionada(Uri uri) {
        if (uri != null) {
            mImagenUri.setValue(uri);
        }
    }

    public void guardarInmueble(EditText etDireccion, EditText etAmbientes, EditText etPrecio, EditText etUso, EditText etTipo, CheckBox cbDisponible) {
        String direccion = etDireccion.getText().toString().trim();
        String ambientesStr = etAmbientes.getText().toString().trim();
        String precioStr = etPrecio.getText().toString().trim();
        String uso = etUso.getText().toString().trim();
        String tipo = etTipo.getText().toString().trim();
        boolean disponible = cbDisponible.isChecked();

        if (direccion.isEmpty() || ambientesStr.isEmpty() || precioStr.isEmpty() || uso.isEmpty() || tipo.isEmpty()) {
            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion(direccion);
        inmueble.setAmbientes(Integer.parseInt(ambientesStr));
        inmueble.setPrecio(Double.parseDouble(precioStr));
        inmueble.setUso(uso);
        inmueble.setTipo(tipo);
        inmueble.setDisponible(disponible);

        Gson gson = new Gson();
        String json = gson.toJson(inmueble);
        RequestBody inmueblePart = RequestBody.create(MediaType.parse("application/json"), json);

        MultipartBody.Part imagenPart = null;
        if (mImagenUri.getValue() != null) {
            File file = new File(mImagenUri.getValue().getPath());
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
            imagenPart = MultipartBody.Part.createFormData("imagen", file.getName(), fileBody);
        }

        String token = ApiClient.obtenerToken(context);

        ApiClient.getServicio().cargarInmueble("Bearer " + token, imagenPart, inmueblePart)
                .enqueue(new Callback<Inmueble>() {
                    @Override
                    public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Inmueble guardado con éxito", Toast.LENGTH_SHORT).show();
                            mGuardadoExitoso.setValue(true);
                        } else {
                            Toast.makeText(context, "Error al guardar el inmueble", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Inmueble> call, Throwable t) {
                        Toast.makeText(context, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}