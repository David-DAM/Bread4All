package com.example.bread4all;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogearActivity extends AppCompatActivity {

    String CHANNEL_ID="notificaciones_channel_id_1";
    int notificacionId=123;
    Button salir,notificacion;
    String pregunta,si,no,salida,pregnoti,recordatorio,noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logear);

        //Creamos el canal de notificaciones
        createNotificacionChannel();

        salir=findViewById(R.id.buttonSalir);
        notificacion=findViewById(R.id.buttonNotificacion);

        pregunta=getString(R.string.pregunta);
        si=getString(R.string.si);
        no=getString(R.string.no);
        salida=getString(R.string.Salida);
        pregnoti=getString(R.string.pregNoti);
        recordatorio=getString(R.string.recordatorio);
        noti=getString(R.string.notificacion);

        //Creamos interfaz para asegurarnos si desea salir de la aplicacion
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(LogearActivity.this);
                alerta.setMessage(pregunta)
                        .setCancelable(false)
                        .setPositiveButton(si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton(no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle(salida);
                titulo.show();
            }
        });
        //Creamos interfaz para asegurarnos si desea crear la notificacion
        notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(LogearActivity.this);
                alerta.setMessage(pregnoti)
                        .setCancelable(false)
                        .setPositiveButton(si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                emitirNotificacionConAccesoApp();
                            }
                        })
                        .setNegativeButton(no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle(salida);
                titulo.show();
            }
        });


    }
    //Metodo para abrir la Activity de registro
    public void LanzarRegistrarse(View view){
        Intent i=new Intent(this,RegistrarseActivity.class);
        startActivity(i);
    }
    //Metodo para abrir la Activity de inicio
    public void LanzarInicio(View view){
        Intent i=new Intent(this,InicioActivity.class);
        startActivity(i);
    }

    //Metodo para crear el canal de notificaciones
    private void createNotificacionChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name="Notificaciones_channel";
            String descripcion="Canal para las notificaciones";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,name,importance);

            channel.setDescription(descripcion);
            channel.enableLights(true);
            channel.enableVibration(true);

            channel.setLightColor(Color.WHITE);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            NotificationManager notificationManager= getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    //Emite la notificacion
    public void emitirNotificacionConAccesoApp(){
        Intent intent=new Intent(this,LogearActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(recordatorio)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(noti))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(this);
        notificationManager.notify(notificacionId,builder.build());
    }




}