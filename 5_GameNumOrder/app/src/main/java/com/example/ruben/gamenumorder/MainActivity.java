package com.example.ruben.gamenumorder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnStartStop;

    private Chronometer crono;
    // Para seguir con el tiempo antes de parar
    long time = 0;

    private TextView aciertos;
    private int contadorAciertos = 0;
    private int contadorIntentos = 0;
    private int contAciertos = 0;
    private int contFallos = 0;

    int numeroAleatorio;

    // Lista de botones creados
    final List<Button> list = new ArrayList<Button>();
    // Lista de los numeros de los botones
    final ArrayList<Integer> numerosAsociados = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarActivity();
    }

    private void cargarActivity() {
        btnStartStop = (Button) findViewById(R.id.btnStartStop);
        crono = (Chronometer) findViewById(R.id.cronometro);
        aciertos = (TextView) findViewById(R.id.textViewContador);

        // Creamos los botones
        crearBotones();

        coloresContador();

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Al pulsar el boton "Iniciar"
                if (btnStartStop.getTag().toString().equals("Iniciar")) {

                    for(final Button btnList : list){
                        btnList.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                contadorIntentos++;

                                // Valor del boton
                                final String valor = btnList.getTag().toString();

                                // Ordenamos los numeros de menor a mayor
                                Collections.sort(numerosAsociados);

                                // Mientras se juega
                                if (btnStartStop.getTag().toString().equals("Pausar")) {
                                    // Comprobar si el valor del boton pulsado es el menor de los botones
                                    if (Integer.parseInt(valor) <= numerosAsociados.get(0)) {
                                        contadorAciertos++;
                                        contAciertos++;
                                        // Ocultar boton
                                        btnList.setVisibility(View.INVISIBLE);
                                        //aciertos.setText(String.valueOf(contadorAciertos));
                                        coloresContador();

                                        // Borramos del ArrayList el numero menor
                                        numerosAsociados.remove(0);

                                        // Ha terminado de pulsar todos los botones
                                        if (numerosAsociados.size() == 0) {
                                            crono.stop();
                                            crono.setTextColor(Color.BLUE);
                                            btnStartStop.setText("Volver a jugar");
                                            btnStartStop.setTag("Reiniciar");

                                            felicidades();
                                        }
                                        Collections.sort(numerosAsociados);
                                    } else {
                                        contadorAciertos--;
                                        contFallos++;
                                        //aciertos.setText(String.valueOf(contadorAciertos));
                                        coloresContador();

                                        // Al fallar, marcar en rojo el boton
                                        // Recoger el valor del background del boton, para luego asignarlo
                                        final Drawable d = btnList.getBackground();
                                        btnList.setBackgroundColor(Color.RED);
                                        btnList.postDelayed(new Runnable() {
                                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                            @Override
                                            public void run() {
                                                // Volver a poner el background como estaba
                                                btnList.setBackground(d);
                                            }
                                        }, 500);
                                    }

                                    // Segun aciertos, cambio de color
                                    /*if (contadorAciertos >= 0) {
                                        aciertos.setTextColor(Color.BLUE);
                                    } else {
                                        aciertos.setTextColor(Color.RED);
                                    }*/
                                }
                            }
                        });
                    }

                    // Para seguir con el tiempo antes de parar
                    crono.setBase(SystemClock.elapsedRealtime()+time);
                    //crono.setBase(SystemClock.elapsedRealtime());
                    crono.start();
                    btnStartStop.setText("Pausar");
                    btnStartStop.setTag("Pausar");
                // Al pulsar el boton "Pausar"
                } else if (btnStartStop.getTag().toString().equals("Pausar")) {
                    // Para seguir con el tiempo antes de parar
                    time = crono.getBase() - SystemClock.elapsedRealtime();
                    crono.stop();
                    btnStartStop.setText("Iniciar");
                    btnStartStop.setTag("Iniciar");
                // Al pulsar el boton "Volver a jugar"
                } else if (btnStartStop.getTag().toString().equals("Reiniciar")) {
                    btnStartStop.setText("Iniciar");
                    btnStartStop.setTag("Iniciar");
                    /*aciertos.setText("0");
                    aciertos.setTextColor(Color.BLACK);*/
                    contAciertos = 0;
                    contFallos = 0;
                    coloresContador();
                    crono.setText("00:00");
                    crono.setTextColor(Color.BLACK);

                    reiniciar();
                }
            }
        });
    }

    private void crearBotones() {

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setOrientation(GridLayout.VERTICAL);

        // Hasta que numero
        int nCartas = 100;

        for (int filas=0; filas < 5; filas++) {
            GridLayout row = new GridLayout(this);

            for (int columnas = 0; columnas < 3; columnas++) {

                final Button btn = new Button(this);
                //btn.setText("Button " + (columnas + 1 + (filas * 4)));
                numeroAleatorio = (int) Math.floor(Math.random() * nCartas );

                btn.setText(numeroAleatorio+"");
                btn.setTag(numeroAleatorio);
                //btn.setText(nums.get(numAle));
                btn.setId(columnas + 1 + (filas * 4));
                //final int index = btn.getId();
                final String valor = btn.getTag().toString();

                numerosAsociados.add(Integer.parseInt(valor));

                row.addView(btn);
                list.add(btn);
            }

            gridLayout.addView(row);
        }
    }

    private void felicidades() {
        // Borrar todos los botones
        gridLayout.removeAllViews();

        // Mostrar imagen Congratulations
        ImageView myImage = new ImageView(this);
        myImage.setImageResource(R.drawable.congratulations);
        gridLayout.addView(myImage);
    }

    private void reiniciar() {
        // Borrar todos los botones
        gridLayout.removeAllViews();
        contadorAciertos = 0;
        cargarActivity();
    }

    private void coloresContador() {
        Spannable word = new SpannableString(String.valueOf(contFallos));
        word.setSpan(new ForegroundColorSpan(Color.RED), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        aciertos.setText(word);

        Spannable wordMid = new SpannableString("/");
        word.setSpan(new ForegroundColorSpan(Color.BLACK), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        aciertos.append(wordMid);

        Spannable wordTwo = new SpannableString(String.valueOf(contAciertos));
        wordTwo.setSpan(new ForegroundColorSpan(Color.rgb(21, 140, 0)), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        aciertos.append(wordTwo);
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }
}
