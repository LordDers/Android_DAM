package com.example.usuario6i.conversorkmmillas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// onClick --> XML y setOnClickListener
public class MainActivity extends AppCompatActivity {

// NO FUNCIONA
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*private EditText vKm, vMillas;
    private Double vConvertKmMillas, vConvertMillasKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(getApplicationContext(), "llego", Toast.LENGTH_LONG).show();

        Button v_btnKmMillas = (Button) findViewById(R.id.btnKmMillas);
        Button v_btnMillasKm = (Button) findViewById(R.id.btnMillasKm);

        v_btnKmMillas.setOnClickListener(this);
        v_btnMillasKm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnKmMillas:
                // Recogemos valor introducido
                vKm = (EditText)findViewById(R.id.textKm);

                // Comprobar si el valor está vacio para mostrar mensaje
                if (!vKm.getText().toString().isEmpty()) {
                    Double vKmString = Double.parseDouble(vKm.getText().toString());
                    vConvertKmMillas = (vKmString * 0.621371) / 1;

                    vMillas = (EditText) findViewById(R.id.textMillas);

                    String vFinalKmMillas= Double.toString(vConvertKmMillas);
                    vMillas.setText(vFinalKmMillas);

                    //vMillas.setText(vConvertKmMillas.toString());
                } else {
                    Toast mensaje =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce un valor1", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                break;
            case R.id.btnMillasKm:
                // Recogemos valor introducido
                vMillas = (EditText)findViewById(R.id.textMillas);

                // Comprobar si el valor está vacio para mostrar mensaje
                if (!vMillas.getText().toString().isEmpty()) {
                    Double vMillasString = Double.parseDouble(vMillas.getText().toString());
                    vConvertMillasKm = (vMillasString * 0.621371) / 1;

                    vKm = (EditText) findViewById(R.id.textKm);

                    String vFinalKmMillas= Double.toString(vConvertKmMillas);
                    vKm.setText(vFinalKmMillas);

                    //vKm.setText(vConvertMillasKm.toString());
                } else {
                    Toast mensaje =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce un valor22", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
                break;

            default:
                break;
        }
    }*/

    // Variables
    private EditText vKm, vMillas;
    private Button v_btnMillasKm, v_btnKmMillas;
    private Double vConvertKmMillas, vConvertMillasKm;

    // onClick --> XML
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recogemos valores introducidos
        //vKm = (EditText)findViewById(R.id.textKm);
        //vMillas = (EditText)findViewById(R.id.textMillas);

        v_btnKmMillas = (Button)findViewById(R.id.btnKmMillas);
        v_btnMillasKm = (Button)findViewById(R.id.btnMillasKm);
    }

    public void convertKmMillas(View v) {
        // Recogemos valor introducido
        vKm = (EditText)findViewById(R.id.textKm);

        // Comprobar si el valor está vacio para mostrar mensaje
        if (!vKm.getText().toString().isEmpty()) {
            Double vKmString = Double.parseDouble(vKm.getText().toString());
            vConvertKmMillas = (vKmString * 0.621371) / 1;

            vMillas = (EditText) findViewById(R.id.textMillas);

            String vFinalKmMillas= Double.toString(vConvertKmMillas);
            vMillas.setText(vFinalKmMillas);

            //vMillas.setText(vConvertKmMillas.toString());
        } else {
            Toast mensaje =
                    Toast.makeText(getApplicationContext(),
                            "Introduce un valor", Toast.LENGTH_SHORT);
            mensaje.show();
        }
    }

    public void convertMillasKm(View v) {

        // Recogemos valor introducido
        vMillas = (EditText)findViewById(R.id.textMillas);

        // Comprobar si el valor está vacio para mostrar mensaje
        if (!vMillas.getText().toString().isEmpty()) {
            Double vMillasString = Double.parseDouble(vMillas.getText().toString());
            vConvertMillasKm = (vMillasString * 0.621371) / 1;

            vKm = (EditText) findViewById(R.id.textKm);

            String vFinalKmMillas= Double.toString(vConvertKmMillas);
            vKm.setText(vFinalKmMillas);

            //vKm.setText(vConvertMillasKm.toString());
        } else {
            Toast mensaje =
                    Toast.makeText(getApplicationContext(),
                            "Introduce un valor", Toast.LENGTH_SHORT);
            mensaje.show();
        }
    }



    // setOnClickListener
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kmMillas = (Button)findViewById(R.id.btnKmMillas);
        Button millasKm = (Button)findViewById(R.id.btnMillasKm);

        kmMillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recogemos valor introducido
                EditText km = (EditText)findViewById(R.id.textKm);

                // Comprobar si el valor está vacio para mostrar mensaje
                if (!km.getText().toString().isEmpty()) {

                    Double numKm = Double.parseDouble(km.getText().toString());
                    Double numMillas = numKm / 1.609344;

                    EditText millas = (EditText) findViewById(R.id.textMillas);
                    //millas.setText(numMillas.toString());

                    // Mostrar únicamente dos decimales
                    String numMillasDecimal = String.format("%.2f", numMillas);
                    millas.setText(numMillasDecimal);
                } else {
                    Toast mensaje =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce un valor", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
            }

        });

        millasKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recogemos valor introducido
                EditText millas = (EditText)findViewById(R.id.textMillas);

                // Comprobar si el valor está vacio para mostrar mensaje
                if (!millas.getText().toString().isEmpty()) {
                    Double numMillas = Double.parseDouble(millas.getText().toString());
                    Double numKm = numMillas * 1.609344;

                    EditText km = (EditText) findViewById(R.id.textKm);
                    //km.setText(numKm.toString());

                    // Mostrar únicamente dos decimales
                    String numKmDecimal = String.format("%.2f", numKm);
                    km.setText(numKmDecimal);
                } else {
                    Toast.makeText(getApplicationContext(), "Introduce un valor", Toast.LENGTH_LONG).show();
                }
            }

        });

    }*/
}
