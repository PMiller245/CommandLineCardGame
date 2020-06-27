package com.pmiller;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cardsInHand;
    private int moneyBetOnThisHand;


    public Hand() {

        this.cardsInHand = new ArrayList<>();
        this.moneyBetOnThisHand = 0;
    }

    public Hand(List<Card> cardsInHand) {

        this.cardsInHand = cardsInHand;
    }

    public List<Card> getCardsInHandAsList() {
        return cardsInHand;
    }

    public void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public Card getCardInSpecificPosition(int position) {
        return this.cardsInHand.get(position);
    }


    public int getMoneyBetOnThisHand() {
        return moneyBetOnThisHand;
    }

    public void setMoneyBetOnThisHand(int moneyBetOnThisHand) {
        this.moneyBetOnThisHand = moneyBetOnThisHand;
    }

    public void addToMoneyBetOnThisHand(int moneyBetOnThisHand){
        this.moneyBetOnThisHand += moneyBetOnThisHand;

    }

    public void addToHand(Card card) {
        this.cardsInHand.add(card);

    }

    public void discardHand() {
        this.cardsInHand.clear();
        this.moneyBetOnThisHand = 0;

    }

//    public void outputHandToConsoleWithPlayerNames(Player player){
//
//        System.out.println(player.getPlayerName() + "'s  hand is " + player.getPlayerHand().size() + " cards.");
//
//        for (Card card : player.getPlayerHand()){
//
//            if(card.isFaceUp() == true){
//                System.out.println(card.getFaceValue());
//            } else {
//
//                System.out.println("One card face down");
//            }
//
//
//        }
//
//
//    }

    public void outputHandToConsole() {

        System.out.println("Outputting hand.");

        for (Card card : cardsInHand) {

            if (card.isFaceUp() == true) {
                System.out.println(card.getFaceValue());
            } else {

                System.out.println("One card face down");
            }


        }


    }


}