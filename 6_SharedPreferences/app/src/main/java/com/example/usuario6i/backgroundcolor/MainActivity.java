package com.example.usuario6i.backgroundcolor;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private RadioGroup rg;

    // https://stackoverflow.com/questions/26344833/android-change-background-colour-of-specific-activity-using-sharedpreferences
    // https://www.tutorialspoint.com/android/android_shared_preferences.htm
    public static final String MY_PREFS_NAME = "Ruben";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutParent);
        rg = (RadioGroup) findViewById(R.id.radioGroup);


        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String colour;
        colour = prefs.getString("colour", "WHITE");

        if(colour.equals("BLUE")){
            linearLayout.setBackgroundColor(Color.BLUE);
        }
        else if(colour.equals("GREEN")){
            linearLayout.setBackgroundColor(Color.GREEN);
        }
        else if(colour.equals("RED")){
            linearLayout.setBackgroundColor(Color.RED);
        }
        else{
            linearLayout.setBackgroundColor(Color.WHITE);
        }

        /*
        if (prefs.getString("colour", "BLUE").equals("BLUE")) {
            linearLayout.setBackgroundColor(Color.BLUE);
        } else if (prefs.getString("colour", "GREEN").equals("GREEN")) {
            linearLayout.setBackgroundColor(Color.GREEN);
        } else if (prefs.getString("colour", "RED").equals("RED")) {
            linearLayout.setBackgroundColor(Color.RED);
        }
         */


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                String colourSelected = "";
                switch (checkedId) {
                    case R.id.rbBlue :
                        colourSelected = "BLUE";
                        editor.putString("colour", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(Color.BLUE);
                        break;
                    case R.id.rbGreen :
                        colourSelected = "GREEN";
                        editor.putString("colour", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(Color.GREEN);
                        break;

                    case R.id.rbRed :
                        colourSelected = "RED";
                        editor.putString("colour", colourSelected);
                        editor.commit();
                        linearLayout.setBackgroundColor(Color.RED);
                        break;
                }
            }
        });

    }

    /*public void rbClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutParent);

        //SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        //editor.putString("name", "Elena");
        //editor.putInt("idName", 12);
        //editor.apply();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbBlue:
                if (checked) {
                    linearLayout.setBackgroundColor(Color.BLUE);
                    break;
                }
            case R.id.rbGreen:
                if (checked) {
                    linearLayout.setBackgroundColor(Color.GREEN);
                    break;
                }
            case R.id.rbRed:
                if (checked) {
                    linearLayout.setBackgroundColor(Color.RED);
                    break;
                }
        }
    }*/
}
