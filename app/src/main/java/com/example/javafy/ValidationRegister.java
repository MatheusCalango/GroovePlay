package com.example.javafy;
import android.util.Log;

import java.util.regex.Pattern;
public class ValidationRegister {
    protected boolean validationIsEmpty(String var) {
        String vazio = "";
        Log.d("vasioi","sem nada");
        return var == null || var.trim().isEmpty();
    }

    public boolean validationEmailRegister(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }



}
