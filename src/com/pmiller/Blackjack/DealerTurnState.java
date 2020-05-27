package com.pmiller.Blackjack;

public class DealerTurnState implements BlackjackGameState{

    BlackjackGame blackjackGame;

    public DealerTurnState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;

    }

    @Override
    public void makeBet() {

        System.out.println("It is the dealers turn");

    }

    @Override
    public void hit() {

        System.out.println("It is the dealers turn");

    }

    @Override
    public void stand() {

        System.out.println("It is the dealers turn");

    }
}
