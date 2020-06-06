package com.pmiller;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    List<Card> playerHand = new ArrayList<Card>();

    public Hand() {
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void addToHand(Card card) {
        this.playerHand.add(card);

    }

    public void discardHand() {
        this.playerHand.clear();

    }
}