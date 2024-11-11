package com.example.javafy;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class ValidationLogin {
    private final FirebaseFirestore dataBase = FirebaseFirestore.getInstance();


    public void validationDataName(String user, String password) {
        dataBase.collection("Usuarios")
                .whereEqualTo("nome", user)  // Compara o campo "nome" com o nome de usuário passado
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.w("Firestore", "Erro ao ouvir as alterações.", error);
                            return;
                        }

                        // Verificar se encontrou algum documento com o nome de usuário
                        if (value != null && !value.isEmpty()) {
                            // Documento encontrado, nome de usuário existe no banco de dados
                            Log.d("Firestore", "Usuário encontrado!");
                        } else {
                            // Nenhum documento encontrado, nome de usuário não existe no banco
                            Log.d("Firestore", "Usuário não encontrado.");
                        }
                    }
                });
    }

    public void validationDataPassword(String user, String password) {
        dataBase.collection("Usuarios")
                .whereEqualTo("nome", user)
                .whereEqualTo("senha",password)// Compara o campo "nome" com o nome de usuário passado
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.w("Firestore", "Erro ao ouvir as alterações.", error);
                            return;
                        }

                        // Verificar se encontrou algum documento com o nome de usuário
                        if (value != null && !value.isEmpty()) {
                            // Documento encontrado, nome de usuário existe no banco de dados
                            Log.d("Firestore", "Usuário encontrado!");
                        } else {
                            // Nenhum documento encontrado, nome de usuário não existe no banco
                            Log.d("Firestore", "Usuário não encontrado.");
                        }
                    }
                });
    }

    public boolean validationIsEmpty(String var) {
        return var == null || var.isEmpty();
    }
}
