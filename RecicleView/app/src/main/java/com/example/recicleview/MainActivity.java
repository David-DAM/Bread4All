package com.example.recicleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Puntuacion> puntuaciones;

    private RecyclerView.LayoutManager llm;

    private RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv=(RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        llm= new LinearLayoutManager(this);
        //llm=new GridLayoutManager(this,2);

        rv.setLayoutManager(llm);

        inicializarDatosEjemplo();

        adapter=new RVAdapter(this,puntuaciones);

        rv.setAdapter(adapter);
    }

    private void inicializarDatosEjemplo(){
        puntuaciones = new ArrayList<>();

        puntuaciones.add(new Puntuacion("Ana García", 100, R.drawable.ic_adb_64));
        puntuaciones.add(new Puntuacion("Pedro Ruiz", 200, R.drawable.ic_alarm_add_64));
        puntuaciones.add(new Puntuacion("Martín Gutierrez", 300, R.drawable.ic_android_64));
        puntuaciones.add(new Puntuacion("Rocío Puertas", 400, R.drawable.ic_adb_64));
        puntuaciones.add(new Puntuacion("Andrea Pérez", 500, R.drawable.ic_alarm_add_64));
        puntuaciones.add(new Puntuacion("Luis Granados", 600, R.drawable.ic_android_64));
        puntuaciones.add(new Puntuacion("Diego Redondo", 700, R.drawable.ic_adb_64));
        puntuaciones.add(new Puntuacion("Alberto Negrero", 800, R.drawable.ic_alarm_add_64));
        puntuaciones.add(new Puntuacion("Sofía Martínez", 900, R.drawable.ic_android_64));
        puntuaciones.add(new Puntuacion("Petra Álvarez", 1000, R.drawable.ic_adb_64));
        puntuaciones.add(new Puntuacion("Domingo Redondo", 1100, R.drawable.ic_alarm_add_64));
        puntuaciones.add(new Puntuacion("Amparo Leal", 1200, R.drawable.ic_android_64));
    }

}