package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class DeckViewActivity extends AppCompatActivity implements IDeckView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        IDeckRepository repo = new DeckRepository();
        IDeckPresenter presenter = new DeckPresenter(this, repo);
        presenter.loadCards();
    }

    @Override
    public void showCards(List<Drawable> cards) {

    }
}

