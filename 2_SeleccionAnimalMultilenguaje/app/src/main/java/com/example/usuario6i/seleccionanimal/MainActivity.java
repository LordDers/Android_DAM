package com.example.usuario6i.seleccionanimal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mostrar ListView array
        // https://www.tutorialspoint.com/android/android_list_view.htm
        /*String[] animales = { "Lobo", "Delfin", "Elefante", "Perro" };

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, animales);

        ListView listView = (ListView) findViewById(R.id.listViewAnimales);
        listView.setAdapter(adapter);*/


        // Mostrar ListView strings.xml
        // https://androiddesk.wordpress.com/tag/simple-listview-in-android-with-example/

        // Recoger valores de string.xml y guardar en string
        String[] listaAnimalesString = getResources().getStringArray(R.array.listaAnimales);

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_list_item_1, listaAnimalesString);

        ListView listView = (ListView) findViewById(R.id.listViewAnimales);
        listView.setAdapter(adapter);


        // Mostrar mensaje al pulsar elemento
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
