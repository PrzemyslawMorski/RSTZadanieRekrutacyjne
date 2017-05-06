package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DeckViewActivity extends AppCompatActivity implements IDeckView, View.OnClickListener {
    IDeckPresenter presenter;
    int numDecks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        IDeckRepository repo = new DeckRepository();
        presenter = new DeckPresenter(this, repo);
        numDecks = (int) getIntent().getExtras().get("numDecks");
        presenter.loadCards(numDecks);
    }

    @Override
    public void showCards(List<Drawable> cards) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DisplayDrawnCardsFragment fragment = DisplayDrawnCardsFragment.newInstance(3, cards);
        transaction.replace(R.id.placeholder_fragment, fragment);
        transaction.commit();
        setContentView(R.layout.activity_deck_view);
    }

    @Override
    public void showWaitingScreen() {
        setContentView(R.layout.layout_waiting);
    }

    @Override
    public void showMessage(String message) {
        TextView cardsInfo = (TextView) findViewById(R.id.cardsInfoText);
        cardsInfo.setText(message);
    }

    @Override
    public void onClick(View v) {
        presenter.loadCards(numDecks);
    }
}

