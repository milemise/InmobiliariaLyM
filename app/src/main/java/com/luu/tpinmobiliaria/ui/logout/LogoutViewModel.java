package com.luu.tpinmobiliaria.ui.logout;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.luu.tpinmobiliaria.request.ApiClient;

public class LogoutViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> mCerrarSesion;

    public LogoutViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getMCerrarSesion() {
        if (mCerrarSesion == null) {
            mCerrarSesion = new MutableLiveData<>();
        }
        return mCerrarSesion;
    }

    public void cerrarSesion() {
        Context context = getApplication().getApplicationContext();
        ApiClient.recuperarToken(context, "");
        mCerrarSesion.setValue(true);
    }
}