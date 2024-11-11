package com.example.javafy;

public class UserSessionSingleton {
    private static UserSessionSingleton instance;
    private String username;


    private UserSessionSingleton() {}


    public static UserSessionSingleton getInstance() {
        if (instance == null) {
            instance = new UserSessionSingleton();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clearSession() {
        username = null;
    }
}
