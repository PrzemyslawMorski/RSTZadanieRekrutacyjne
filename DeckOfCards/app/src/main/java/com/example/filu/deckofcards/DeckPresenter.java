package com.example.filu.deckofcards;

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
        repo.setPresenter(this);
    }



    @Override
    public void loadCards(final int numDecks) {
        view.showWaitingScreen();
        repo.startGettingCards(numDecks);
    }

    public void receiveCards(List<Card> cards) {
        view.showCards(null);
        //send the images to view
        //send the appropriate message to display to view
    }

}
