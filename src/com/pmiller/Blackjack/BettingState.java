package com.pmiller.Blackjack;

import com.pmiller.Player;

public class BettingState implements BlackjackGameState{

    BlackjackGame blackjackGame;

    public BettingState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;

    }

    @Override
    public void makeInitialBet(Player bettingPlayer, int bet) {

        if(bettingPlayer.placeBet(bet) == true){
            System.out.println(bettingPlayer.getPlayerName() + "'s bet of " + bet + " was accepted.");
            bettingPlayer.outputMoney();
            bettingPlayer.outputMoneyAtStake();



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
        blackjackGame.dealHand();
        blackjackGame.setState(blackjackGame.getPlayerTurnState());

    }

    @Override
    public void makeMidHandBet() {

    }
}
