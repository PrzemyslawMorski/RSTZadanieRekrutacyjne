package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by filu on 02.05.17.
 */

public class Card implements Comparable<Card> {
    String code;
    Drawable image;
    String value;
    String suit;

    public Card() { }

    Card setValue(String value) {
        this.value = value;
        return this;
    }

    Card setCode(String code) {
        this.code = code;
        return this;
    }
    Card setSuit(String suit) {
        this.suit = suit;
        return this;
    }
    Card setImage(Drawable image) {
        this.image = image;
        return this;
    }

    @Override
    public int compareTo(@NonNull Card o) {
        return code.compareTo(o.code);
    }
}
