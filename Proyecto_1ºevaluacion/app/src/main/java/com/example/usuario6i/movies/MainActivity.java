package com.example.usuario6i.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText editTextPelicula;
    private Button btnBuscar;

    private String getTextPelicula;

    public static final String EXTRA_MESSAGE = "Ruben";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicio();
    }

    // Recoger pelicula o serie introducida
    private void inicio() {
        editTextPelicula = (EditText) findViewById(R.id.editText);
        btnBuscar = (Button) findViewById(R.id.btn);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextPelicula = editTextPelicula.getText().toString();

                String peliculaSerieBuscada = getTextPelicula.replaceAll(" ", "+").toLowerCase();

                cargarActivity(peliculaSerieBuscada);
            }
        });
    }

    // Llamar a la activity de mostrar datos
    // Finalizar activity
    public void cargarActivity(String peliculaSerie) {
        Intent intent = new Intent(this, ActivityPelicula.class);
        intent.putExtra(EXTRA_MESSAGE, peliculaSerie);
        MainActivity.this.finish();
        startActivity(intent);
    }
}
