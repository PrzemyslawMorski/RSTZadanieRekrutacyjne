package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
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
        Collections.sort(cards);
        extractImagesAndSendToVieW(cards);
        extractMessageAndSendToView(cards);
    }

    private void extractImagesAndSendToVieW(List<Card> cards) {
        List<Drawable> cardImages = new ArrayList<>();
        for(Card card : cards) {
            cardImages.add(card.image);
        }
        view.showCards(cardImages);
    }

    private void extractMessageAndSendToView(List<Card> cards) {
        String message = "";
        boolean[] kfb = saKoloryFiguryBlizniakiSchodki(cards);

        if(kfb[0]) {
            message += "Min 3 karty tego samego koloru\n";
        }

        if(kfb[3]) {
            message += "Sa schodki\n";
        }

        if(kfb[1]) {
            message += "Min 3 figury\n";
        }

        if(kfb[2]) {
            message += "Min 3 karty tej samej wartosci\n";
        }

        if(message.equals("")) {
            message = "Nie znaleziono charakterystycznych kombinacji";
        }
        view.showMessage(message);
    }

    private boolean[] saKoloryFiguryBlizniakiSchodki(List<Card> cards) { //
        boolean[] koloryFiguryBlizniakiSchodki = new boolean[4];
        int[] wystapieniaKolorow = new int[4];
        int iloscFigur = 0;
        int[] wystapieniaKart = new int[13];

        for(Card card : cards) {
            switch(card.value) {
                case "ACE":
                    wystapieniaKart[0] = wystapieniaKart[0]+1;
                    //Obecnie do figur zalicza się na ogół także asa (jedynkę), mimo że as pierwotnie zaliczał się do blotek.
                    iloscFigur += 1;
                    break;
                case "2":
                    wystapieniaKart[1] = wystapieniaKart[1]+1;
                    break;
                case "3":
                    wystapieniaKart[2] = wystapieniaKart[2]+1;
                    break;
                case "4":
                    wystapieniaKart[3] = wystapieniaKart[3]+1;
                    break;
                case "5":
                    wystapieniaKart[4] = wystapieniaKart[4]+1;
                    break;
                case "6":
                    wystapieniaKart[5] = wystapieniaKart[5]+1;
                    break;
                case "7":
                    wystapieniaKart[6] = wystapieniaKart[6]+1;
                    break;
                case "8":
                    wystapieniaKart[7] = wystapieniaKart[7]+1;
                    break;
                case "9":
                    wystapieniaKart[8] = wystapieniaKart[8]+1;
                    break;
                case "10":
                    wystapieniaKart[9] = wystapieniaKart[9]+1;
                    break;
                case "JACK":
                    wystapieniaKart[10] = wystapieniaKart[10]+1;
                    iloscFigur += 1;
                    break;
                case "QUEEN":
                    wystapieniaKart[11] = wystapieniaKart[11]+1;
                    iloscFigur += 1;
                    break;
                case "KING":
                    wystapieniaKart[12] = wystapieniaKart[12]+1;
                    iloscFigur += 1;
                    break;
            }

            switch(card.suit) {
                case "SPADES":
                    wystapieniaKolorow[0] = wystapieniaKolorow[0] + 1;
                    break;
                case "CLUBS":
                    wystapieniaKolorow[1] = wystapieniaKolorow[1] + 1;
                    break;
                case "DIAMONDS":
                    wystapieniaKolorow[2] = wystapieniaKolorow[2] + 1;
                    break;
                case "HEARTS":
                    wystapieniaKolorow[3] = wystapieniaKolorow[3] + 1;
                    break;
            }

            for(int i = 0; i<wystapieniaKart.length; i++) {
                if(i < 4) {
                    if(wystapieniaKolorow[i] >= 3) {
                        koloryFiguryBlizniakiSchodki[0] = true;
                    }
                }
                if(wystapieniaKart[i] >= 3) {
                    koloryFiguryBlizniakiSchodki[2] = true;
                }
                if(i < wystapieniaKart.length-3) {
                    if(wystapieniaKart[i]*wystapieniaKart[i+1]*wystapieniaKart[i+2] != 0) { // istnieja 3 kolejne karty
                        koloryFiguryBlizniakiSchodki[3] = true;
                    }
                }
            }

            if(iloscFigur >= 3) {
                koloryFiguryBlizniakiSchodki[1] = true;
            }
        }
        return koloryFiguryBlizniakiSchodki;
    }

}
