package com.vfguille.inventoryjetpack.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.ui.dash.DashBoardActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnSignIn;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, DashBoardActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
    }

    private void initializeComponents() {
        btnSignIn = findViewById(R.id.btSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}
