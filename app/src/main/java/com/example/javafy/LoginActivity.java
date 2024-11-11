package com.example.javafy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private final Login loginUser = new Login();

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText nameUser = findViewById(R.id.nome);
        EditText password = findViewById(R.id.senha);
        TextView loginStatus = findViewById(R.id.login);
        Button loginButton = findViewById(R.id.button01);
        Button registerButton = findViewById(R.id.registerButton);



        loginButton.setOnClickListener(view -> {
            String name = nameUser.getText().toString();
            String pass = password.getText().toString();

          String  mensage = loginUser.LoginMensage(name,pass);
          loginStatus.setText(mensage);


            finish();
        });

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}