package com.example.ruben.ratingbaractivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by Ruben on 28/10/2017.
 */

public class SecondActivity extends Activity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        tv = (TextView) findViewById(R.id.tvMensaje);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //tv.setText("Su voto se ha registrado correctamente. \nLa puntaución ha sido de " + message);

        Spannable word = new SpannableString("Su voto se ha registrado correctamente. \nLa puntauacion ha sido de ");
        word.setSpan(new ForegroundColorSpan(Color.BLACK), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(word);

        Spannable wordMid = new SpannableString(message);
        wordMid.setSpan(new ForegroundColorSpan(Color.RED), 0, wordMid.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(wordMid);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
