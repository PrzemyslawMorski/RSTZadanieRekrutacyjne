package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public class DeckRepository implements IDeckRepository{
    @Override
    public List<Card> getFiveCards(int numDecks) {
        List<Card> drawnCardsResponse = new ArrayList<>();
        try {
            JSONObject deckResponse = getDeck(numDecks);
            String deckId = deckResponse.getString("deck_id");

            drawnCardsResponse = getCards(deckId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return drawnCardsResponse;
    }

    private static JSONObject getDeck(int numDecks) {
        JSONObject response = null;
        try {
            String string_url = "https://deckofcardsapi.com/api/deck/new/";
            URL url = new URL(string_url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();



            InputStream inputStream = connection.getInputStream();
            InputStreamReader in = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(in);

            StringBuffer json = new StringBuffer(1024);

            String tmp="";
            while((tmp=reader.readLine())!=null) {
                json.append(tmp).append("\n");
            }

            reader.close();

            response = new JSONObject(json.toString());

            if(!response.getBoolean("success")){
                return null;
            }
        } catch (MalformedURLException e) { //during creating url
            e.printStackTrace();
        } catch (IOException e) { //during url opening connection
            e.printStackTrace();
        } catch (JSONException e) { // during JSONObject creation
            e.printStackTrace();
        } catch (Exception e) { // during JSONObject creation
            e.printStackTrace();
        }
        return response;
    }

    private static List<Card> getCards(String deckId) {
        JSONObject response = null;
        List<Card> cardsFromResponse = new ArrayList<>();
        try {
            String string_url = "https://deckofcardsapi.com/api/deck/" + deckId + "/draw/?count=5";
            URL url = new URL(string_url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader in = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(in);

            StringBuffer json = new StringBuffer(1024);

            String tmp="";
            while((tmp=reader.readLine())!=null) {
                json.append(tmp).append("\n");
            }

            reader.close();

            response = new JSONObject(json.toString());

            if(!response.getBoolean("success")){
                return null;
            }

            JSONArray cards = response.getJSONArray("cards");
            for(int i = 0; i < cards.length(); i++) {
                try {
                    JSONObject card = cards.getJSONObject(i);
                    String imgSrc = card.getString("image");
                    InputStream is = (InputStream) new URL(imgSrc).getContent();
                    Drawable d = Drawable.createFromStream(is, "");
                    String symbol = card.getString("code");
                    cardsFromResponse.add(new Card(symbol, d));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (MalformedURLException e) { //during creating url
            e.printStackTrace();
        } catch (IOException e) { //during url opening connection
            e.printStackTrace();
        } catch (JSONException e) { // during JSONObject creation
            e.printStackTrace();
        }
        return cardsFromResponse;
    }

}
