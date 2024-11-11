package com.example.javafy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class RegisterActivity extends AppCompatActivity {

    private final RegistrarAbstractTemplateMethod registrar = new RegistrarLocalTemplateMethod();


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText user = findViewById(R.id.registerNome);
        EditText  passwordUser = findViewById(R.id.registerSenha);
        EditText emailUser = findViewById(R.id.registerEmail);
        Button submitButton = findViewById(R.id.registerSubmitButton);

        submitButton.setOnClickListener(view -> {
            String username = user.getText().toString();
            String password = passwordUser.getText().toString();
            String email = emailUser.getText().toString();


            String result = registrar.mensengeRegister(username, password, email);
            Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();

            if (result.equals("Usuário registrado com sucesso!")) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
