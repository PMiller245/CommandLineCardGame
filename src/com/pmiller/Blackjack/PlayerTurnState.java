package com.pmiller.Blackjack;

import com.pmiller.Card;
import com.pmiller.Player;

public class PlayerTurnState implements BlackjackGameState{

    //Constructor

    BlackjackGame blackjackGame;


    public PlayerTurnState(BlackjackGame blackjackGame){

        this.blackjackGame = blackjackGame;

    }


    @Override
    public void makeInitialBet(Player player, int betAmount) {

        System.out.println("You are not able to make a bet");

    }

    @Override
    public void hit() {
        Card hitCard;
        System.out.println("Dealing one card");
        hitCard = blackjackGame.getDeck().draw(1)[0];
        hitCard.turnOver();
        blackjackGame.getBlackjackPlayer().addToHand(hitCard);
        System.out.println("You were dealt a " + hitCard.getFaceValue());
        //check busts
        if(blackjackGame.calculateBlackjackHandValue(blackjackGame.getBlackjackPlayer()) == -1){
            blackjackGame.evaluateHand();
            return;

        }

        System.out.println("Your total is now: "  + blackjackGame.calculateBlackjackHandValue(blackjackGame.getBlackjackPlayer()));



    }

    @Override
    public void stand() {

        //blackjackGame.evaluateHand();
        blackjackGame.setState(blackjackGame.getDealerTurnState());
        blackjackGame.dealerHit();


    }

    @Override
    public void deal() {
        System.out.println("Hand has already been dealt!");

    }

    @Override
    public void makeMidHandBet() {

    }
}
