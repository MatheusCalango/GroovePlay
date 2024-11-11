package com.example.javafy;

import android.util.Log;

public class Login {
    private final ValidationLogin loginUser = new ValidationLogin();

    public String LoginMensage(String nameUser, String password) {
        if(loginUser.validationIsEmpty(nameUser) || loginUser.validationIsEmpty(password))
            return "Preencha todos os campo";
        loginUser.validationDataPassword(nameUser,password);
        return nameUser;
    }
}
