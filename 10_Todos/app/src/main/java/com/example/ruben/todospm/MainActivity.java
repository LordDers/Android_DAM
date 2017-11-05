package com.example.ruben.todospm;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnConversor, btnMultilenguaje, btnCambioImagen, btnLayouts, btnReproductor, btnJuego, btnSharedPreferences, btnFiltroImagen, btnWeb, btnVotacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConversor = (Button) findViewById(R.id.btnConversor);
        btnMultilenguaje = (Button) findViewById(R.id.btnMultilenguaje);
        btnCambioImagen = (Button) findViewById(R.id.btnCambiarImagen);
        btnLayouts = (Button) findViewById(R.id.btnLayouts);
        btnReproductor = (Button) findViewById(R.id.btnReproductor);
        btnJuego = (Button) findViewById(R.id.btnJuego);
        btnSharedPreferences = (Button) findViewById(R.id.btnSharedPreferences);
        btnFiltroImagen = (Button) findViewById(R.id.btnFiltroImagen);
        btnWeb = (Button) findViewById(R.id.btnNumberPicker);
        btnVotacion = (Button) findViewById(R.id.btnActivities);

        btnConversor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.conversorkmmillas");
            }
        });

        btnMultilenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.seleccionanimal");
            }
        });

        btnCambioImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.cambiarimagenesboton");
            }
        });

        btnLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.layouts");
            }
        });

        btnReproductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.reproductormusica");
            }
        });

        btnJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.ruben.gamenumorder");
            }
        });

        btnSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.backgroundcolor");
            }
        });

        btnFiltroImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.imagencambiocolorseekbar");
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.usuario6i.numberpickerweb");
            }
        });

        btnVotacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp("com.example.ruben.ratingbaractivities");
            }
        });
    }

    protected void launchApp(String packageName) {
        Intent mIntent = getPackageManager().getLaunchIntentForPackage(
                packageName);
        if (mIntent != null) {
            try {
                startActivity(mIntent);
            } catch (ActivityNotFoundException err) {
                Toast.makeText(getApplicationContext(), "No se puede abrir " + packageName, Toast.LENGTH_LONG).show();
            }
        }
    }
}
