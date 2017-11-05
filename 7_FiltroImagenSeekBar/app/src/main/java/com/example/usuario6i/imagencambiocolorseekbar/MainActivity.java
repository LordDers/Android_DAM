package com.example.usuario6i.imagencambiocolorseekbar;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarA;
    private ImageView paisajeGrande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarA = (SeekBar) findViewById(R.id.seekBar);
        paisajeGrande = (ImageView) findViewById(R.id.imgPaisajeGrande);

        seekBarA.setMax(255);

        seekBarA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                //paisajeGrande.setColorFilter(Color.rgb(0, seekBarA.getProgress(), 0));

                Random rnd = new Random();
                paisajeGrande.setColorFilter(Color.rgb(rnd.nextInt(256), seekBarA.getProgress(), rnd.nextInt(256)), PorterDuff.Mode.OVERLAY);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(getApplicationContext(), "onProgress", Toast.LENGTH_LONG).show();
            }
        });

    }
}

// NO
// http://www.programering.com/a/MDMycDMwATQ.html
