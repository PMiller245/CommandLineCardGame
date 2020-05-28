package com.pmiller.Blackjack;

import com.pmiller.Player;

public class BettingState implements BlackjackGameState{

    BlackjackGame blackjackGame;

    public BettingState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;

    }

    @Override
    public void makeBet(Player bettingPlayer, int bet) {



    }

    @Override
    public void hit() {

    }

    @Override
    public void stand() {

    }
}
