package com.example.usuario6i.movies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

/**
 * Created by Ruben on 22/11/2017.
 */

public class ActivityPelicula extends Activity {

    private LinearLayout layoutDatos, layoutError, layoutTrailer;
    private TextView tvTitulo, tvAnyo, tvDirector, tvActores, tvPuntuacion, tvVotos, tvError;
    private ImageView imagen, imgTrailer;
    private WebView webViewTrailer;

    private final String BASE_URL_OMDB ="http://www.omdbapi.com/";
    private final String API_KEY = "YOUR API HERE";
    private final String BASE_URL_IMDB = "http://www.theimdbapi.org/api/";

    ParseJSON classParseJSON = new ParseJSON();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        inicio();
    }

    // Inicializar y recoger valor del Intent
    private void inicio() {

        layoutDatos = (LinearLayout) findViewById(R.id.linearLayoutDatos);
        layoutError = (LinearLayout) findViewById(R.id.linearLayoutError);
        layoutTrailer = (LinearLayout) findViewById(R.id.subLayoutTrailer);

        tvError = (TextView) findViewById(R.id.textViewError);
        tvTitulo = (TextView) findViewById(R.id.textViewTitulo);
        tvAnyo = (TextView) findViewById(R.id.textViewAnyo);
        tvDirector = (TextView) findViewById(R.id.textViewDirector);
        tvActores = (TextView) findViewById(R.id.textViewActores);
        tvPuntuacion = (TextView) findViewById(R.id.textViewPuntuacion);
        tvVotos = (TextView) findViewById(R.id.textViewVotos);

        imagen = (ImageView) findViewById(R.id.imageView);
        imgTrailer = (ImageView) findViewById(R.id.imageTrailer);

        // WEBVIEW
        webViewTrailer = (WebView)findViewById(R.id.webView);

        layoutDatos.setVisibility(LinearLayout.GONE);
        layoutError.setVisibility(LinearLayout.GONE);

        Intent intent = getIntent();
        String peliculaSerie = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //final String finalURL = BASE_URL_OMDB + "?i=" + API_KEY + "&t=" + peliculaSerie;
        String finalURL = String.format(BASE_URL_OMDB + "?apikey=%1$s" + "&t=%2$s", API_KEY, peliculaSerie);

        getOMDB(finalURL);
    }

    // Metodo para recoger los datos de la API OMDB
    public void getOMDB(String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getOMDB_JSON(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                layoutDatos.setVisibility(LinearLayout.GONE);
                layoutTrailer.setVisibility(LinearLayout.GONE);
                layoutError.setVisibility(LinearLayout.VISIBLE);
                tvError.setText("Error de conexión con el servidor");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // Metodo para recoger los datos parseados
    public void getOMDB_JSON(String json) {
        String datos[] = classParseJSON.parseOMDB(json);

        if (datos[0].equalsIgnoreCase("True")) {

            layoutError.setVisibility(LinearLayout.GONE);
            layoutTrailer.setVisibility(LinearLayout.GONE);
            layoutDatos.setVisibility(LinearLayout.VISIBLE);

            tvTitulo.setText(datos[1]);
            tvAnyo.setText("Año: " + datos[2]);
            tvDirector.setText("Director: " + datos[3]);
            tvActores.setText("Actores: " + datos[4]);
            tvPuntuacion.setText("PUNTUACION: " + datos[5]);
            tvVotos.setText("Votos: " + datos[6]);

            if (datos[7] != null && !datos[7].equalsIgnoreCase("N/A")) {
                Glide.with(this).load(datos[7]).into(imagen);
            } else {
                Glide.with(this).load(R.drawable.noimage).into(imagen);
            }

            getIMDB(datos[8]);

        } else {
            layoutDatos.setVisibility(LinearLayout.GONE);
            layoutTrailer.setVisibility(LinearLayout.GONE);
            layoutError.setVisibility(LinearLayout.VISIBLE);
            tvError.setText("Serie / Película no encontrada");
        }
    }

    // Metodo para recoger los datos de la API IMDB (Trailer)
    public void getIMDB(String idMovie) {
        //String final_URL_IMDB = BASE_URL_IMDB + "movie?movie_id=" + idMovie;
        String final_URL_IMDB = String.format(BASE_URL_IMDB + "movie?movie_id=%1$s", idMovie);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, final_URL_IMDB,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //parseJSON_IMDB(response);
                        getIMDB_JSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                layoutDatos.setVisibility(LinearLayout.GONE);
                layoutTrailer.setVisibility(LinearLayout.GONE);
                layoutError.setVisibility(LinearLayout.VISIBLE);
                tvError.setText("Error de conexión con el servidor");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // Metodo para recoger los datos parseados (Trailer)
    public void getIMDB_JSON(String json) {
        String datos[] = classParseJSON.parseIMDB(json);

        layoutTrailer.setVisibility(LinearLayout.VISIBLE);

        if (datos[0] == "True") {
            imgTrailer.setVisibility(View.GONE);
            webViewTrailer.setVisibility(View.VISIBLE);

            // WEBVIEW
            webViewTrailer.getSettings().setLoadsImagesAutomatically(true);
            webViewTrailer.getSettings().setJavaScriptEnabled(true);
            webViewTrailer.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webViewTrailer.loadUrl(datos[1]);
        } else {
            webViewTrailer.setVisibility(View.GONE);
            imgTrailer.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.notrailer).into(imgTrailer);
        }
    }

    // Cuando pulsas el boton de ir atras, ir al MainActivity y finalizar este activity
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, MainActivity.class);
        ActivityPelicula.this.finish();
        startActivity(intent);
    }

}
