package com.example.javafy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    ArrayList<Music> musicList;
    Context context;

    public MusicListAdapter(ArrayList<Music> musicList, Context context) {//contexto para saber onde mostar a recycler view
        this.musicList = musicList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//Método principal da recycler view
        Music musicData = musicList.get(position);
        holder.titleTextView.setText(musicData.getTitle() + " " + MusicPlayerActivity.convertMiliToSecounds(musicData.getDuration()));//nome


        if (MediaPlayerSingleton.currentIndex == position){//musica que esta tocando é a que esta mostrando
            holder.titleTextView.setTextColor(Color.parseColor("#76a6e8"));
        }else{
            holder.titleTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passar activity
                MediaPlayerSingleton.getInstance().reset();
                MediaPlayerSingleton.currentIndex = position;
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("LIST", musicList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.musicTitleView);
            imageView = itemView.findViewById(R.id.iconView);
        }
    }
}
