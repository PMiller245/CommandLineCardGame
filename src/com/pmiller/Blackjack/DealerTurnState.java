package com.pmiller.Blackjack;

import com.pmiller.Player;

public class DealerTurnState implements BlackjackGameState{

    BlackjackGame blackjackGame;

    public DealerTurnState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;

    }

    @Override
    public void makeInitialBet(Player player, int bet){
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

    @Override
    public void deal() {
        System.out.println("It is the dealers turn");
    }

    @Override
    public void makeMidHandBet() {
        System.out.println("It is the dealers turn");
    }
}
