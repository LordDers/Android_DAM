package com.example.usuario6i.cambiarimagenesboton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton imgBtnTlf = (ImageButton)findViewById(R.id.btnImgTlf);

        imgBtnTlf.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ImageView img = (ImageView)findViewById(R.id.imgCara);

                if (imgBtnTlf.getTag().toString() == "1") {
                    img.setTag("alegre");
                    img.setImageResource(R.drawable.alegre);

                    imgBtnTlf.setTag("2");
                    imgBtnTlf.setImageResource(R.drawable.phone2);
                } else {
                    img.setTag("triste");
                    img.setImageResource(R.drawable.triste);

                    imgBtnTlf.setTag("1");
                    imgBtnTlf.setImageResource(R.drawable.phone1);
                }
            }

        });
    }
}
