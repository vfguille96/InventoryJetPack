package com.vfguille.inventoryjetpack.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.utils.CommonUtils;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private Button btSignUp;
    TextInputEditText tietUser;
    TextInputEditText tietPassword;
    TextInputEditText tietEmail;
    TextInputLayout tilUser;
    TextInputLayout tilEmail;
    TextInputLayout tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeComponents();
        displaySoftKeyboard(tietUser);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void initializeComponents() {
        btSignUp = findViewById(R.id.btRegister);

        tietUser = findViewById(R.id.tiedUser);
        tietEmail = findViewById(R.id.tiedEmail);
        tietPassword = findViewById(R.id.tiedPass);

        tilUser = findViewById(R.id.tilUser);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);

        tietUser.addTextChangedListener(new SignUpWatcher(tietUser));
        tietEmail.addTextChangedListener(new SignUpWatcher(tietEmail));
        tietPassword.addTextChangedListener(new SignUpWatcher(tietPassword));
    }

    /**
     * Método que comprueba la validez de los campos del TIL
     */
    private void validate() {
        //1. Se guarda el usuario en la BD
        //2. Envío correo al usuario (Firebase)
        //3. Se pasa a la ventana Login
        if (validateUser(tietUser.getText().toString()) && validateEmail(tietEmail.getText().toString()) && validatePassword(tietPassword.getText().toString())) {
            Toast.makeText(this, "SignUp successfull", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * Valida email
     *
     * @return
     */
    private boolean validateEmail(String email) {
        if (!email.isEmpty()) {
            tilEmail.setError(null);
            return true;
        } else {
            tilEmail.setError(getString(R.string.errEmail));
            displaySoftKeyboard(tietEmail);
            return false;
        }
    }

    /**
     * Valida Pass
     *
     * @return
     */
    private boolean validatePassword(String password) {
        if (CommonUtils.checkPasswordLength(password) && CommonUtils.regexpPasswordsValidation(password)) {
            tilPassword.setError(null);
            return true;
        } else {
            tilPassword.setError(getString(R.string.errPassword));
            displaySoftKeyboard(tietPassword);
            return false;
        }
    }

    /**
     * Valida usuario.
     *
     * @return
     */
    private boolean validateUser(String user) {
        if (user.length() < 4) {
            tilUser.setError(getString(R.string.errUserEmpty));
            displaySoftKeyboard(tietUser);
            return false;
        } else {
            tilUser.setError(null);
            return true;
        }
    }

    /**
     * Este método habilita el teclado y el foco en la vista
     *
     * @param view
     */
    private void displaySoftKeyboard(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE))).showSoftInput(view, 0);
        }
    }

    private class SignUpWatcher implements TextWatcher {

        private View view;

        public SignUpWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tiedUser:
                    validateUser(((TextInputEditText) view).getText().toString());
                    break;
                case R.id.tiedPass:
                    validatePassword(((TextInputEditText) view).getText().toString());
                    break;
                case R.id.tiedEmail:
                    validateEmail(((TextInputEditText) view).getText().toString());
                    break;
            }
        }
    }
}