package com.example.javafy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {
    TextView titleTextView, currentTimeTextView, totalTimeTextView;
    SeekBar seekBar;
    ImageView pausePlay, next, previous, musicIcon;
    ArrayList<Music> musicList;
    Music actualMusic;
    int spinIcon;
    MediaPlayer mediaPlayer = MediaPlayerSingleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music_player);


        titleTextView = findViewById(R.id.song_title);
        currentTimeTextView = findViewById(R.id.current_time);
        totalTimeTextView = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        pausePlay = findViewById(R.id.pause_play);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        musicIcon = findViewById(R.id.music_icon_big);

        titleTextView.setSelected(true);

        musicList = (ArrayList<Music>) getIntent().getSerializableExtra("LIST");

        setActualMusicData();

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {//inicia uma thread especifíca para controlar o tempo de execeção da musica
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTimeTextView.setText(convertMiliToSecounds(mediaPlayer.getCurrentPosition()+""));

                    if(mediaPlayer.isPlaying()){
                        pausePlay.setImageResource(R.drawable.baseline_pause_circle_24);
                        musicIcon.setRotation(spinIcon++);
                    }else{
                        pausePlay.setImageResource(R.drawable.baseline_play_circle_filled_24);
                    }
                }
                new Handler().postDelayed(this, 100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    void setActualMusicData(){

        actualMusic = musicList.get(MediaPlayerSingleton.currentIndex);
        titleTextView.setText(actualMusic.getTitle());
        totalTimeTextView.setText(convertMiliToSecounds(actualMusic.getDuration()));

        pausePlay.setOnClickListener(v -> pausePlay());
        next.setOnClickListener(v -> playNextSong());
        previous.setOnClickListener(v -> playPreviousSong());


        playMusic();
    }

    private void playMusic(){
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(actualMusic.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void playNextSong(){//incrementa o index de musica, reseta o mediplayer e seta as informações do novo indice

        if(MediaPlayerSingleton.currentIndex == musicList.size()-1){//se chegar na última música nao toca
            return;
        }

        MediaPlayerSingleton.currentIndex += 1;
        mediaPlayer.reset();
        setActualMusicData();
    }

    private void playPreviousSong(){
        if(MediaPlayerSingleton.currentIndex == 0){
            return;
        }

        MediaPlayerSingleton.currentIndex -= 1;
        mediaPlayer.reset();
        setActualMusicData();
    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
        else {
            mediaPlayer.start();
        }
    }

    public static String convertMiliToSecounds(String duration){
        Long mili = Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(mili) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(mili) % TimeUnit.MINUTES.toSeconds(1));
    }

}