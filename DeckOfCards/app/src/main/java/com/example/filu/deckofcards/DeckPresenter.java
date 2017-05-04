package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public class DeckPresenter implements IDeckPresenter{
    IDeckView view;
    IDeckRepository repo;

    DeckPresenter(IDeckView view, IDeckRepository repo) {
        this.view = view;
        this.repo = repo;
    }



    @Override
    public void loadCards() {
        List<Card> cards = repo.getFiveCards(1);
        List<Drawable> imgs = new ArrayList<>();
        for(Card card : cards) {
            imgs.add(card.image);
        }
        view.showCards(imgs);
    }
}
