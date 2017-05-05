package com.example.filu.deckofcards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

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
import java.util.Collections;
import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public class DeckRepository implements IDeckRepository{
    IDeckPresenter presenter;


    private final static String API_URL = "https://deckofcardsapi.com/api/deck/";

    @Override
    public void startGettingCards(int numDecks) {
        new GetCardsTask().execute(numDecks);
    }

    @Override
    public void setPresenter(IDeckPresenter deckPresenter) {
        presenter = deckPresenter;
    }

    private class GetCardsTask extends AsyncTask<Integer, Void, List<Card>> {
        String deckId = "";
        boolean needsReshuffling = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Card> cards) {
            super.onPostExecute(cards);
            presenter.receiveCards(cards);
        }

        @Override
        protected List<Card> doInBackground(Integer... params) {
            if(deckId.equals("")) {
                String newDeckId = getNewDeckId(params[0]); //params[0] is numDecks
                if(!newDeckId.equals("")) {
                    deckId = newDeckId;
                } else {
                    return new ArrayList<>();
                }
            }
            return getCards(deckId);
        }

        private String getNewDeckId(int numDecks) {
            String string_get_decks_url = API_URL + "new/shuffle/?deck_count=" + Integer.toString(numDecks);
            try {
                URL get_decks_url = new URL(string_get_decks_url);
                HttpURLConnection get_decks_connection = (HttpURLConnection) get_decks_url.openConnection();
                InputStream inputStream = get_decks_connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer stringJson = new StringBuffer(1024);
                String tmp = "";
                while((tmp= bufferedReader.readLine())!=null) {
                    stringJson.append(tmp).append("\n");
                }

                bufferedReader.close();

                JSONObject deck = new JSONObject(stringJson.toString());
                if(deck.getBoolean("success")) {
                    return deck.getString("deck_id");
                } else {
                    return "";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "";
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        private List<Card> getCards(String deckId) {
            if(!deckId.equals("")) {
                String string_get_cards_url = API_URL + deckId + "/draw/?count=5";
                if(needsReshuffling) {
                    reshuffleDeck(deckId);
                }
                try {
                    URL get_cards_url = new URL(string_get_cards_url);
                    HttpURLConnection get_cards_connection = (HttpURLConnection) get_cards_url.openConnection();
                    InputStream inputStream = get_cards_connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuffer response = new StringBuffer(1024);
                    String tmp = "";
                    while ((tmp = bufferedReader.readLine()) != null) {
                        response.append(tmp).append("\n");
                    }

                    bufferedReader.close();

                    JSONObject cardsJson = new JSONObject(response.toString());

                    if(cardsJson.getBoolean("success")) {
                        if(cardsJson.getInt("remaining") < 5) {
                            needsReshuffling = true;
                        }
                        List<Card> cards = new ArrayList<>();
                        JSONArray cardsArrayJson = cardsJson.getJSONArray("cards");
                        for(int i = 0; i < cardsArrayJson.length(); i++) {
                            JSONObject cardJson = cardsArrayJson.getJSONObject(i);
                            Drawable image = drawableFromUrl(cardJson.getString("image"));
                            String value = cardJson.getString("value");
                            String suit = cardJson.getString("suit");
                            String code = cardJson.getString("code");

                            Card card = new Card().setValue(value).setCode(code).setSuit(suit).setImage(image);
                            cards.add(card);
                        }
                        return cards;
                    } else {
                        return Collections.EMPTY_LIST;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return Collections.EMPTY_LIST;
                } catch (IOException e) {
                    e.printStackTrace();
                    return Collections.EMPTY_LIST;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return Collections.EMPTY_LIST;
                }
            } else {
                return Collections.EMPTY_LIST;
            }

        }

        private void reshuffleDeck(String deckId) {
            String string_reshuffle_deck_url = API_URL + deckId + "/shuffle/";
        }


        private Drawable drawableFromUrl(String url) throws IOException {
            Bitmap x;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();

            x = BitmapFactory.decodeStream(input);
            return new BitmapDrawable(x);
        }
    }
}
