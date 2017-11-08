package com.example.usuario6i.tiempo;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by Ruben on 01/11/2017.
 */

public class XMLParser {

    public String parseXMLTiempo(String xml, String dia) {
        String name = "";
        String result = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory
                    .newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("forecast")) {
                            System.out.println("forecast");
                            String day = parser.getAttributeValue(null,"forecastDay");
                            if (day.equalsIgnoreCase(dia)) {
                                eventType = parser.next();
                                name = parser.getName();
                                if (name.equalsIgnoreCase("imageMap")) {
                                    eventType = parser.next();
                                    eventType = parser.next();
                                    eventType = parser.next();
                                    name = parser.getName();
                                    if (name.equalsIgnoreCase("description")) {
                                        eventType = parser.next();
                                        name = parser.getName();
                                        if (name.equalsIgnoreCase("es")) {
                                            //System.out.println("es");
                                            eventType = parser.next();

                                            result = parser.getText();
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("imageMap")) {
                            /*Log.i("asd", "ruben2");
                            String aa = parser.nextText();
                            Log.i("dd", aa);*/
                            /*eventType = parser.next();
                            name = parser.getName();
                            System.out.println(name);*/
                        }
                        break;

                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return result;
    }

    // http://www.devexchanges.info/2015/04/getting-xml-data-by-asynctask-and.html
    // https://stackoverflow.com/questions/38951030/android-xml-parsing-asynctask
    public String[] parseXML(XmlPullParser parser, String dia) {

        int eventType;
        String name = null;
        String[] result = new String[1];

        try {
            eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("forecast")) {
                            System.out.println("forecast");
                            String day = parser.getAttributeValue(null,"forecastDay");
                            if (day.equalsIgnoreCase(dia)) {
                                eventType = parser.next();
                                name = parser.getName();
                                if (name.equalsIgnoreCase("imageMap")) {
                                    eventType = parser.next();
                                    eventType = parser.next();
                                    eventType = parser.next();
                                    name = parser.getName();
                                    if (name.equalsIgnoreCase("description")) {
                                        eventType = parser.next();
                                        name = parser.getName();
                                        if (name.equalsIgnoreCase("es")) {
                                            //System.out.println("es");
                                            eventType = parser.next();

                                            result[0] = parser.getText();
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("imageMap")) {
                            /*Log.i("asd", "ruben2");
                            String aa = parser.nextText();
                            Log.i("dd", aa);*/
                            /*eventType = parser.next();
                            name = parser.getName();
                            System.out.println(name);*/
                        }
                        break;

                }
                eventType = parser.next();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
