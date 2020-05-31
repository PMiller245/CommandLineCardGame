package com.pmiller.Blackjack;

import com.pmiller.Card;
import com.pmiller.Player;

import java.util.List;

public class PlayerTurnState implements BlackjackGameState{

    //Constructor

    BlackjackGame blackjackGame;

    Player player = blackjackGame.getBlackjackPlayer();
    List<Card> playerHand = player.getPlayerHand();


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

    @Override
    public void split() {

        if(playerHand.size() == 2 && playerHand.get(0).getValue() == playerHand.get(1).getValue()){

            if((player.getMoney() >= player.getMoneyAtStake())){
                player.placeBet(player.getMoneyAtStake());
                hit();
                stand();
            } else{
                System.out.println("You can't split!");
            }




        }


        if(blackjackGame.getBlackjackPlayer().getPlayerHand())

        if((player.getMoney() >= player.getMoneyAtStake()) && (playerHand.size() == 2)){
            blackjackGame.getBlackjackPlayer().placeBet(blackjackGame.getBlackjackPlayer().getMoneyAtStake());
            hit();
            stand();
        } else{
            System.out.println("You can't split!");
        }

    }

    public  void doubleDown(){



        if((blackjackGame.getBlackjackPlayer().getMoney() >= blackjackGame.getBlackjackPlayer().getMoneyAtStake()) && (blackjackGame.getBlackjackPlayer().getPlayerHand().size() == 2)){
            blackjackGame.getBlackjackPlayer().placeBet(blackjackGame.getBlackjackPlayer().getMoneyAtStake());
            hit();
            stand();
        } else{
            System.out.println("You can't double down!");
        }





    }

}
