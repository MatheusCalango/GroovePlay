package com.example.javafy;

public abstract class RegistrarAbstractTemplateMethod {
private final ValidationRegister vl = new ValidationRegister();

    public final String mensengeRegister(String username, String password,String email) {

        if (vl.validationIsEmpty(password) && !vl.validationEmailRegister(email) && vl.validationIsEmpty(username)){
            return "Dados inválidos.";
        }

        if(!registerDataBase(username,password,email)){
            return "Usuário registrado com sucesso!";

        }else{
            return "Falha ao registrar o Usuário";
        }

    }

    protected boolean registerDataBase(String username, String password, String email) {
        return false;
    }
    protected abstract boolean usuarioJaRegistrado(String username);


}
