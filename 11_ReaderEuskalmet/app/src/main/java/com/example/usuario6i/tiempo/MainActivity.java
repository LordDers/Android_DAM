package com.example.usuario6i.tiempo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button btnHoy, btnManyana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHoy = (Button) findViewById(R.id.btnTiempoHoy);
        btnManyana = (Button) findViewById(R.id.btnTiempoManyana);

        btnHoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarAsyncTask("today");
            }
        });

        btnManyana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarAsyncTask("tomorrow");
            }
        });
    }

    public void llamarAsyncTask(String dia) {
        if (isOnline(getApplicationContext())) {
            new EuskalmetReader(this, dia).execute();
        } else {
            Toast.makeText(getApplicationContext(), "Compruebe su conexión a Internet", Toast.LENGTH_LONG).show();
        }
    }

    // Comprobar si tiene Internet
    // https://es.stackoverflow.com/questions/41864/c%C3%B3mo-comprobar-la-conexi%C3%B3n-a-internet
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}
