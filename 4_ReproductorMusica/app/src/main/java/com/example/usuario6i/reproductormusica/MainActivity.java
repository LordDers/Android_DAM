package com.example.usuario6i.reproductormusica;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private ImageButton botonPlayPause;
    private ToggleButton tgLoop;

    private SeekBar seekBar;
    private Handler handler = new Handler();

    private TextView textViewTimer;
    private TextView textViewTimerTotal;
    private double startTime = 0;
    private double finalTime = 0;


    // https://www.tutorialspoint.com/android/android_mediaplayer.htm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonPlayPause = (ImageButton) findViewById(R.id.imgPlayPause);
        tgLoop = (ToggleButton) findViewById(R.id.toggleButtonLoop);

        seekBar = (SeekBar) findViewById(R.id.seekbar);

        textViewTimer = (TextView) findViewById(R.id.textTimer);
        textViewTimerTotal = (TextView) findViewById(R.id.textTimerTotal);

        // Activity en el que estamos, fichero que queremos reproducir
        mp = MediaPlayer.create(MainActivity.this, R.raw.aa);

        // Cuanto dura la canción
        finalTime = mp.getDuration();
        textViewTimerTotal.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );

        botonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    botonPlayPause.setImageResource(R.drawable.ic_media_play);
                } else {
                    mp.start();
                    botonPlayPause.setImageResource(R.drawable.ic_media_pause);

                    // Comprobar cuando ha finalizado la musica para poner nuevamente la imagen de Play
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                        // @Override
                        public void onCompletion(MediaPlayer arg0) {
                            // File has ended !!!
                            botonPlayPause.setImageResource(R.drawable.ic_media_play);
                            // seekbar al principio
                            mp.seekTo(0);
                        }
                    });
                }
            }
        });



        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mp != null) {
                    int posicionActual = mp.getCurrentPosition()/1000;
                    seekBar.setProgress(posicionActual);

                    // Obtener el tiempo actual de la cancion
                    startTime = mp.getCurrentPosition();
                    textViewTimer.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                            toMinutes((long) startTime)))
                    );
                }


                // Llama a este metodo, se para un segundo y sigue
                handler.postDelayed(this, 1000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    seekBar.setMax(mp.getDuration()/1000);
                    mp.seekTo(progress*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // Ya está en botonPlayPause.setOnClickListener
       /* mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.pause();
            }
        });*/

        tgLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tgLoop.isChecked()){
                    mp.setLooping(true);
                } else {
                    mp.setLooping(false);
                }
            }
        });
    }
}