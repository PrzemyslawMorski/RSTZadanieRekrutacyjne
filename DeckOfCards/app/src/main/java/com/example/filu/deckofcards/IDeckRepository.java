package com.example.filu.deckofcards;

import java.util.List;

/**
 * Created by filu on 02.05.17.
 */

public interface IDeckRepository {

    List<Card> getFiveCards(int numDecks);
}
