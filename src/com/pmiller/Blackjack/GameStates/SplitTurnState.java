package com.pmiller.Blackjack.GameStates;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Blackjack.BlackjackPlayer;
import com.pmiller.Card;
import com.pmiller.Player;

import java.util.List;

public class SplitTurnState implements BlackjackGameState {

    //Constructor

    private BlackjackGame blackjackGame;
    private BlackjackPlayer player;
    private List<Card> playerHand;
    private int initialBet;
    private int handNumberBeingPlayed;


    public SplitTurnState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;
        this.player = blackjackGame.getBlackjackPlayer();
        this.playerHand = this.player.getPlayerHand().getCardsInHandAsList();
        this.initialBet = player.getMoneyAtStake();
        this.handNumberBeingPlayed = 0;

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
        blackjackGame.getBlackjackPlayer().addToHand(hitCard,handNumberBeingPlayed);
        System.out.println("You were dealt a " + hitCard.getFaceValue());
        //check busts
        if (blackjackGame.calculateBlackjackHandValue(blackjackGame.getBlackjackPlayer().getPlayerHand(handNumberBeingPlayed)) == -1) {
            //mark hand as bust and move to next hand
            System.out.println("Hand bust, moving to next hand");
            handNumberBeingPlayed++;
            return;

        }

        System.out.println("Your total is now: " + blackjackGame.calculateBlackjackHandValue(blackjackGame.getBlackjackPlayer().getPlayerHand(handNumberBeingPlayed)));


    }

    @Override
    public void stand() {

        //blackjackGame.evaluateHand();
        if(handNumberBeingPlayed == blackjackGame.getBlackjackPlayer().getPlayerHands().size()){
            blackjackGame.setState(blackjackGame.getDealerTurnState());
            blackjackGame.dealerHit();

        } else {
            handNumberBeingPlayed++;
        }



    }

    @Override
    public void deal() {
        System.out.println("Hand has already been dealt!");

    }

    @Override
    public void makeMidHandBet() {

    }

    @Override
    public void split() {

        if (playerHand.size() == 2 && playerHand.get(0).getValue() == playerHand.get(1).getValue()) {

            if ((player.getMoney() >= player.getMoneyAtStake())) {
                System.out.println("Cards have been split! Playing hand one...");
                player.removeHand(handNumberBeingPlayed);
                player.placeBet(player.getMoneyAtStake(), 0);
                player.splitHand(player.getPlayerHand());

            } else {
                System.out.println("You can't split!");
            }


        }




    }


    public void doubleDown() {


        if ((blackjackGame.getBlackjackPlayer().getMoney() >= blackjackGame.getBlackjackPlayer().getMoneyAtStake()) && (blackjackGame.getBlackjackPlayer().getPlayerHand().getCardsInHandAsList().size() == 2)) {
            blackjackGame.getBlackjackPlayer().placeBet(blackjackGame.getBlackjackPlayer().getMoneyAtStake(), 0);
            hit();
            if (!blackjackGame.getBettingState().equals(blackjackGame.getState())) {
                stand();
            }


        } else {
            System.out.println("You can't double down!");
        }


    }

}



