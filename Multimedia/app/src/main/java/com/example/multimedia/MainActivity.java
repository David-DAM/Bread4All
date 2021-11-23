package com.example.multimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    MediaPlayer mediaPlayer=null;
    SurfaceView superficie=null;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarSuperficieReproductor();

        mediaPlayer=new MediaPlayer();

        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();

        if (mediaPlayer!=null){
            pos=mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (mediaPlayer!=null){
            mediaPlayer.seekTo(pos);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle estadoGuardado){
        super.onSaveInstanceState(estadoGuardado);

        if (mediaPlayer!=null){
            estadoGuardado.putInt("posicion",pos);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle estadoGuardado) {
        super.onRestoreInstanceState(estadoGuardado);

        if (estadoGuardado!=null && mediaPlayer!=null){
            pos=estadoGuardado.getInt("posicion");
        }

    }

    protected void inicializarSuperficieReproductor(){
        superficie=findViewById(R.id.superficie);

        SurfaceHolder holder= superficie.getHolder();

        holder.addCallback(this);
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        mediaPlayer.release();
    }

    public void surfaceChanged(SurfaceHolder holder,int format,int with,int height){

    }

    public void surfaceCreated(SurfaceHolder holder){
        mediaPlayer.setDisplay(holder);
    }
}