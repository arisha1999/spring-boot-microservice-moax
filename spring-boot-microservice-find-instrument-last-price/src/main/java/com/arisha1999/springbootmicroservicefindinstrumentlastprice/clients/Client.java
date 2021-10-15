package com.arisha1999.springbootmicroservicefindinstrumentlastprice.clients;

import com.arisha1999.springbootmicroservicefindinstrumentlastprice.models.Instrument;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.EmptyStackException;

public class Client {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getRoute(String ticker) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl(String.format("http://iss.moex.com/iss/securities.json?q=%s", ticker));
        JSONObject securities = (JSONObject) json.get("securities");
        JSONArray data = (JSONArray) securities.get("data");
        for (int i = 0; i < data.length(); i++) {
            if (((JSONArray) data.get(i)).get(1).equals(ticker)) {
                return String.format("http://iss.moex.com/iss/history/engines/stock/markets/index/boards/%s/securities/%s", ((JSONArray) data.get(i)).get(14), ticker);
            }
        }
        throw new EmptyStackException();
    }

    public static String findLastDate(String ticker) throws IOException {
        try{
            JSONObject json = readJsonFromUrl(getRoute(ticker)+"/dates.json");
            JSONObject dates = (JSONObject) json.get("dates");
            JSONArray data = (JSONArray) dates.get("data");
            String lastDate = ((JSONArray) data.get(0)).get(1).toString();
            return lastDate;
        }
        catch (JSONException e){
            return e.getMessage();
        }
        catch (Error e){
            return e.getLocalizedMessage();
        }
    }

    public static Instrument findInstrument (String ticker, String date) throws JSONException, IOException {
        JSONObject json = readJsonFromUrl(getRoute(ticker)+String.format(".json?from=%s",date));
        JSONObject history = (JSONObject) json.get("history");
        JSONArray data = (JSONArray) history.get("data");
        Double price = (Double) ((JSONArray) data.get(0)).get(5);
        Instrument instrument = new Instrument(ticker,price, LocalDate.parse(date));
        return instrument;
    }
}
