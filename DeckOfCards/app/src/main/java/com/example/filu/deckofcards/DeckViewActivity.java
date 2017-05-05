package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

public class DeckViewActivity extends AppCompatActivity implements IDeckView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        IDeckRepository repo = new DeckRepository();
        IDeckPresenter presenter = new DeckPresenter(this, repo);

        int ileTalii = (int) getIntent().getExtras().get("ileTalii");

        presenter.loadCards(ileTalii);
    }

    @Override
    public void showCards(List<Drawable> cards) {

    }

    @Override
    public void showWaitingScreen() {
        setContentView(R.layout.layout_waiting);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

