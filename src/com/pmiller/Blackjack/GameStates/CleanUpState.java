package com.pmiller.Blackjack.GameStates;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Player;

public class CleanUpState implements BlackjackGameState {

    BlackjackGame blackjackGame;
    //State that prevents all actions while table and winnings are distributed

    public CleanUpState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;

    }

    @Override
    public void makeInitialBet(Player bettingPlayer, int bet) {

        System.out.println("You can't do that");


    }

    @Override
    public void hit() {

        System.out.println("You can't do that");

    }

    @Override
    public void stand() {

        System.out.println("You can't do that");

    }

    @Override
    public void deal() {


        System.out.println("You can't do that");


    }

    @Override
    public void makeMidHandBet() {
        System.out.println("You can't do that");
    }

    @Override
    public void split() {
        System.out.println("You can't do that");
    }

    @Override
    public void doubleDown() {
        System.out.println("You can't do that");
    }

}
