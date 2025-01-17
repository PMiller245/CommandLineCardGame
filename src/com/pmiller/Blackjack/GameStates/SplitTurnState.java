package com.pmiller.Blackjack.GameStates;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Blackjack.BlackjackPlayer;
import com.pmiller.Card;
import com.pmiller.Hand;
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
        if (blackjackGame.calculateBlackjackHandValue(blackjackGame.getBlackjackPlayer().getPlayerHand(handNumberBeingPlayed)) > 21) {
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
        if(handNumberBeingPlayed+1 == blackjackGame.getBlackjackPlayer().getPlayerHands().size()){
            blackjackGame.setState(blackjackGame.getDealerTurnState());
            handNumberBeingPlayed = 0;
            blackjackGame.dealerHit();

        } else {
            handNumberBeingPlayed++;
            hit();
            blackjackGame.getBlackjackPlayer().getPlayerHand(handNumberBeingPlayed).outputHandToConsole();
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

        List<Card> playerHand = player.getPlayerHand(handNumberBeingPlayed).getCardsInHandAsList();
        if (playerHand.size() == 2 && playerHand.get(0).getValue() == playerHand.get(1).getValue()) {

            if ((player.getMoney() >= player.getMoneyAtStake())) {
                System.out.println("Cards have been split! Playing hand one...");
                player.splitHand(player.getPlayerHand(handNumberBeingPlayed), handNumberBeingPlayed);
                blackjackGame.getState().hit();

            } else {
                System.out.println("You can't split!");
            }


        }




    }


    public void doubleDown() {


        if ((blackjackGame.getBlackjackPlayer().getMoney() >= blackjackGame.getBlackjackPlayer().getMoneyAtStake()) && (blackjackGame.getBlackjackPlayer().getPlayerHand(handNumberBeingPlayed).getCardsInHandAsList().size() == 2)) {

            blackjackGame.getBlackjackPlayer().placeBetOnHand(blackjackGame.getBlackjackPlayer().getSplitBetAmount(), handNumberBeingPlayed);
            hit();
            if (!blackjackGame.getBettingState().equals(blackjackGame.getState())) {
                stand();
            }


        } else {
            System.out.println("You can't double down!");
        }


    }

}



