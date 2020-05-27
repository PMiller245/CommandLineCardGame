package com.pmiller.Blackjack;

import com.pmiller.Card;

public class PlayerTurnState implements BlackjackGameState{

    //Constructor

    BlackjackGame blackjackGame;

    public PlayerTurnState(BlackjackGame blackjackGame){

        this.blackjackGame = blackjackGame;

    }


    @Override
    public void makeBet() {

        System.out.println("You are not able to make a bet");

    }

    @Override
    public void hit() {
        Card hitCard;
        System.out.println("Dealing one card");
        hitCard = blackjackGame.getDeck().draw(1)[1];
        hitCard.turnOver();
        blackjackGame.getPlayerHand().add(hitCard);
        System.out.println("You were dealt a " + hitCard.getFaceValue());
        System.out.println("Your total is now: " );


    }

    @Override
    public void stand() {

    }
}
