package com.pmiller.Blackjack;

import com.pmiller.Card;
import com.pmiller.Hand;
import com.pmiller.Player;

public class BlackjackHand extends Hand {


    private int blackjackHandValue;

    public BlackjackHand(Hand hand){

        this.setCardsInHand(hand.getCardsInHandAsList());


    }


    public int getBlackjackHandValue(){
        calculateBlackjackHandValue();
        return blackjackHandValue;

    }

    public int calculateBlackjackHandValue() {
        //returns 0 for a blackjack and -1 for a bust, otherwise it returns the highest hand value

        blackjackHandValue = 0;
        int blackjackCardValue = 0;
        int aceCounter = 0;

        for (Card card : cardsInHand) {
            //check for ace and set it to max value
            if (card.getValue() == 14) {
                aceCounter++;
                blackjackCardValue = 11;
            }
            if (card.getValue() <= 10) {
                blackjackCardValue = card.getValue();
            }

            //normalize face cards
            if (card.getValue() > 10 && card.getValue() <= 13) {
                blackjackCardValue = 10;
            }
            blackjackHandValue = blackjackHandValue + blackjackCardValue;
        }

        //check for blackjack
        if (blackjackHandValue == 21 && super.getCardsInHandAsList().size() == 2) {
            return 0;
        }

        //resize hand based on number of aces, loop does nothing if the hand is 21 or below
        while (aceCounter > 0) {

            //subract value of 1 ace
            if (blackjackHandValue > 21) {
                blackjackHandValue = blackjackHandValue - 10;

            }

            aceCounter--;
        }

        if (blackjackHandValue > 21) {
            return -1;
        }
        System.out.println("Called the BJ Calc method in BJ Hand class and its " + blackjackHandValue);
        return blackjackHandValue;


    }
}
