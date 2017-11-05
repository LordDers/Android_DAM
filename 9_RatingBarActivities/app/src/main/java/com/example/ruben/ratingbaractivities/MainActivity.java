package com.example.ruben.ratingbaractivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RatingBar rb;
    private Button btn;

    public static final String EXTRA_MESSAGE = "Ruben";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb = (RatingBar) findViewById(R.id.ratingBar);
        btn = (Button) findViewById(R.id.btnVotar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), String.valueOf(rb.getRating()), Toast.LENGTH_LONG).show();
                cargarActivity();
            }
        });
    }

    public void cargarActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(rb.getRating()));
        MainActivity.this.finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        //finish();
    }
}

