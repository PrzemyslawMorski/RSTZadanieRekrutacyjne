package com.example.filu.deckofcards;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public class DeckRepository implements IDeckRepository{
    IDeckPresenter presenter;

    @Override
    public void startGettingCards(int numDecks) {
        new GetCardsTask().execute(numDecks);
    }

    @Override
    public void setPresenter(IDeckPresenter deckPresenter) {
        presenter = deckPresenter;
    }

    private class GetCardsTask extends AsyncTask<Integer, Void, List<Card>> {

        @Override
        protected void onPostExecute(List<Card> cards) {
            super.onPostExecute(cards);
            presenter.receiveCards(cards);
        }

        @Override
        protected List<Card> doInBackground(Integer... params) {
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(null, null));
            return cards;
        }
    }
}
