package com.luu.tpinmobiliaria.ui.Pagos;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.luu.tpinmobiliaria.models.Pago;
import com.luu.tpinmobiliaria.request.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Pago>> mListaPagos;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getMListaPagos() {
        if (mListaPagos == null) {
            mListaPagos = new MutableLiveData<>();
        }
        return mListaPagos;
    }

    public void cargarPagos(int idContrato) {
        String token = ApiClient.obtenerToken(context);
        if (token.isEmpty()) {
            Toast.makeText(context, "Token inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        String tokenBearer = "Bearer " + token;

        ApiClient.getServicio().obtenerPagosPorContrato(tokenBearer, idContrato).enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListaPagos.setValue(response.body());
                } else {
                    Toast.makeText(context, "Sin pagos registrados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}