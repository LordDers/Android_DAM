package com.example.usuario6i.numberpickerweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    private NumberPicker np;
    private Button btn;
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        np = (NumberPicker) findViewById(R.id.numberPicker);
        btn = (Button) findViewById(R.id.btnMostrar);
        wv = (WebView) findViewById(R.id.webView);

        final String[] web = {"https://www.google.es"};

        final String[] webAddress = {"https://www.google.es", "http://www.marca.com/", "https://es.wikipedia.org", "http://www.plaiaundi.hezkuntza.net/web/guest/"};
        final String[] nombres = {"Google", "Marca", "Wikipedia", "Plaiaundi"};

        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setMinValue(0);
        np.setMaxValue(webAddress.length - 1);
        np.setDisplayedValues(nombres);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected value from picker
                web[0] = webAddress[newVal];
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //wv.loadUrl("https://www.google.es");
                wv.setWebViewClient(new WebViewClient());
                wv.loadUrl(web[0]);
            }
        });


    }
}
