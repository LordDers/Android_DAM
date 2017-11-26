package com.example.usuario6i.movies;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Usuario6i on 21/11/2017.
 */

public class ParseJSON {

    // Parsear JSON de OMDB
    public String[] parseOMDB(String json) {
        String[] datos = new String[9];
        String message = "";
        try {
            JSONObject reader = new JSONObject(json);
            message = reader.getString("Response");

            if (message.equalsIgnoreCase("True")) {
                datos[0] = "True";
                datos[1] = reader.getString("Title");
                datos[2] = reader.getString("Year");
                datos[3] = reader.getString("Director");
                datos[4] = reader.getString("Actors");
                datos[5] = reader.getString("imdbRating");
                datos[6] = reader.getString("imdbVotes");
                datos[7] = reader.getString("Poster");
                datos[8] = reader.getString("imdbID");
            } else {
                datos[0] = "False";
            }
            return datos;
        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

    // Parsear JSON de IMDB
    public String[] parseIMDB(String json) {
        String[] datos = new String[2];

        try {
            JSONObject reader = new JSONObject(json);

            if (reader.getJSONArray("trailer").getJSONObject(0).has("videoUrl")) {
                datos[0] = "True";
                JSONObject trailer = reader.getJSONArray("trailer").getJSONObject(0);
                datos[1] = trailer.getString("videoUrl");
            } else {
                datos[0] = "False";
            }

            return datos;

        } catch (JSONException e) {
            /*e.printStackTrace();
            return null;*/
            datos[0] = "False";
            return datos;
        }
    }
}
