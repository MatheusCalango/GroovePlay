package com.example.javafy;

import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class RegistrarLocalTemplateMethod extends RegistrarAbstractTemplateMethod {


    private final FirebaseFirestore dataBase = FirebaseFirestore.getInstance() ;
    @Override
    protected boolean usuarioJaRegistrado(String username) {
        return false;
    }
    public boolean registerDataBase(String username, String password, String email){
        AtomicBoolean success = new AtomicBoolean(false);
        Map<String, Object> userDatabase = Map.of(
                "nome", username,
                "senha", password,
                "email",email
        );
        dataBase.collection("Usuarios").document(username).set(userDatabase).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("dataBase", "Registrado Com Sucesso");
                        success.set(true);
                    }
        }).addOnFailureListener(e -> {
            success.set(false);
        });
        return success.get();
    }
}
