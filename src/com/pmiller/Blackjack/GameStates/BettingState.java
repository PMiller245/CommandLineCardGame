package com.pmiller.Blackjack.GameStates;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Player;

public class BettingState implements BlackjackGameState {

    BlackjackGame blackjackGame;
    //variable to prevent deal from happening before the bet is placed
    private boolean betPlaced;

    public BettingState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;

    }

    @Override
    public void makeInitialBet(Player bettingPlayer, int bet) {

        betPlaced = false;

        if (bettingPlayer.placeBet(bet, 0) == true) {
            System.out.println(bettingPlayer.getPlayerName() + "'s bet of " + bet + " was accepted.");
            bettingPlayer.outputMoney();
            bettingPlayer.outputMoneyAtStake();
            betPlaced = true;


        } else {
            System.out.println("You don't have enough money to make that bet!");
        }


    }

    @Override
    public void hit() {

        System.out.println("We are in the betting state!");

    }

    @Override
    public void stand() {

        System.out.println("We are in the betting state!");

    }

    @Override
    public void deal() {


        if (betPlaced) {
            blackjackGame.setState(blackjackGame.getPlayerTurnState());
            blackjackGame.dealHand();
            betPlaced = false;
        } else {
            System.out.println("No bet was placed!");
        }


    }

    @Override
    public void makeMidHandBet() {

    }

    @Override
    public void split() {

    }

    @Override
    public void doubleDown() {

    }
}
