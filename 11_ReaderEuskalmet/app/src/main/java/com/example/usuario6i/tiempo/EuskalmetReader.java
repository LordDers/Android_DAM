package com.example.usuario6i.tiempo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Usuario6i on 03/11/2017.
 */

public class EuskalmetReader extends AsyncTask<String[], Void, String[]> {


    ProgressDialog progressDialog;
    private String xml;
    private String finalResult;
    HttpParse httpParse = new HttpParse();
    XMLParser xmlParser = new XMLParser();
    private String HttpURL = "http://www.euskalmet.euskadi.eus/contenidos/prevision_tiempo/met_forecast/opendata/met_forecast.xml";
    private TextView tv;
    Activity ac = null;
    private String day;

    String finalResultArray[];

    public EuskalmetReader(Activity activity, String dia) {
        tv = (TextView) activity.findViewById(R.id.textView);
        ac = activity;
        day = dia;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(ac,"Loading Data",null,true,true);
    }

    @Override
    protected void onPostExecute(String[] descripcionTiempo) {
        super.onPostExecute(descripcionTiempo);
        progressDialog.dismiss();

        tv.setText(descripcionTiempo[0]);
    }

    @Override
    protected String[] doInBackground(String[]... params) {
        //xml = httpParse.getRequest(HttpURL);
        //finalResult = xmlParser.parseXMLTiempo(xml, day);
        //return finalResult;

        finalResultArray = httpParse.getRequestDescripcion(HttpURL, day);
        return finalResultArray;
    }
}
