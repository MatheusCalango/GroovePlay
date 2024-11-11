package com.example.javafy;

import android.media.MediaPlayer;

public class MediaPlayerSingleton {
    static MediaPlayer instance;

    public static MediaPlayer getInstance(){
        if(instance == null){
            instance = new MediaPlayer();
        }
        return instance;
    }

    public static int currentIndex = -1;
}