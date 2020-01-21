package com.vfguille.inventoryjetpack.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.ui.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {
    private static final long WAIT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /*
        Se ejecuta el método en un hilo diferente al de la UI.
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initLogin();
            }
        }, WAIT_TIME);
    }

    private void initLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}