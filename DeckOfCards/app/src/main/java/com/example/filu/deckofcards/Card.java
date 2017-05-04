package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;

/**
 * Created by filu on 02.05.17.
 */

public class Card {
    String symbol;
    Drawable image;

    public Card(String symbol, Drawable image) {
        this.symbol = symbol;
        this.image = image;
    }
}
