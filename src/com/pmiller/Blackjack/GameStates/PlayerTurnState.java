package com.pmiller.Blackjack.GameStates;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Card;
import com.pmiller.Player;

import java.util.List;

public class PlayerTurnState implements BlackjackGameState {

    //Constructor

    private BlackjackGame blackjackGame;
    private Player player;
    private List<Card> playerHand;
    private int initialBet;


    public PlayerTurnState(BlackjackGame blackjackGame) {

        this.blackjackGame = blackjackGame;
        this.player = blackjackGame.getCardgamePlayer();
        this.playerHand = this.player.getPlayerHand().getCardsInHandAsList();
        this.initialBet = player.getMoneyAtStake();

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
        blackjackGame.getCardgamePlayer().addToHand(hitCard);
        System.out.println("You were dealt a " + hitCard.getFaceValue());
        //check busts
        if (blackjackGame.calculateBlackjackHandValue(blackjackGame.getCardgamePlayer()) == -1) {
            blackjackGame.evaluateHand();
            return;

        }

        System.out.println("Your total is now: " + blackjackGame.calculateBlackjackHandValue(blackjackGame.getCardgamePlayer()));


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

    @Override
    public void split() {

        if (playerHand.size() == 2 && playerHand.get(0).getValue() == playerHand.get(1).getValue()) {

            if ((player.getMoney() >= player.getMoneyAtStake())) {
                System.out.println("Cards have been split! Playing hand one...");
                player.placeBet(player.getMoneyAtStake());
            } else {
                System.out.println("You can't split!");
            }


        }


    }


    public void doubleDown() {


        if ((blackjackGame.getCardgamePlayer().getMoney() >= blackjackGame.getCardgamePlayer().getMoneyAtStake()) && (blackjackGame.getCardgamePlayer().getPlayerHand().getCardsInHandAsList().size() == 2)) {
            blackjackGame.getCardgamePlayer().placeBet(blackjackGame.getCardgamePlayer().getMoneyAtStake());
            hit();
            if (!blackjackGame.getBettingState().equals(blackjackGame.getState())) {
                stand();
            }


        } else {
            System.out.println("You can't double down!");
        }


    }

}
