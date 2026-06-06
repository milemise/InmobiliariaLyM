package com.luu.tpinmobiliaria;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.luu.tpinmobiliaria.databinding.ActivityLoginBinding;
import com.luu.tpinmobiliaria.ui.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {

    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;

    private SensorManager sensorManager;
    private Sensor acelerometro;
    private float aceleracionUltima;
    private float aceleracionActual;
    private float agite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        aceleracionUltima = SensorManager.GRAVITY_EARTH;
        aceleracionActual = SensorManager.GRAVITY_EARTH;
        agite = 0.00f;

        vm.getMLoginExitoso().observe(this, exitoso -> {
            if (exitoso) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vm.getMIntentLlamada().observe(this, intentLlamada -> {
            if (intentLlamada != null) {
                startActivity(intentLlamada);
            }
        });

        binding.btnLogin.setOnClickListener(view -> {
            vm.loguear(binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
        });

        binding.tvOlvidePassword.setOnClickListener(view -> {
            vm.resetear(binding.etEmail.getText().toString());
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        aceleracionUltima = aceleracionActual;
        aceleracionActual = (float) Math.sqrt((double) (x * x + y * y + z * z));

        vm.verificarAgite(x, y, z, aceleracionUltima, aceleracionActual, agite);

        float delta = aceleracionActual - aceleracionUltima;
        agite = agite * 0.9f + delta;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null && acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}