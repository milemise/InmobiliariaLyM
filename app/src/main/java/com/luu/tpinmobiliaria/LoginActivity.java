package com.luu.tpinmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.luu.tpinmobiliaria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding b;
    private LoginActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        b = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        vm.getMLoginExitoso().observe(this, exitoso -> {
            if (exitoso) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });

        b.btnLogin.setOnClickListener(v ->
                vm.loguear(
                        b.etEmail.getText().toString(),
                        b.etPassword.getText().toString()
                )
        );

        b.tvOlvidePassword.setOnClickListener(v ->
                vm.resetear(
                        b.etEmail.getText().toString()
                )
        );
    }
}