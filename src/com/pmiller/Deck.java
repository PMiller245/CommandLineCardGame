package com.pmiller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {


    //Private Variables
    private List<Card> cards;

    //Properties

    private int numberOfCardsRemaining;


    //Constructors

    public Deck() {

        this.cards = new ArrayList<>();


        for (int i = 2; i < 15; i++) {

            this.cards.add(new Card("Spades", i));
            this.cards.add(new Card("Clubs", i));
            this.cards.add(new Card("Hearts", i));
            this.cards.add(new Card("Diamonds", i));

            //System.out.println(this.cards);
        }

        shuffle();

    }

    public Deck(int numberOfDecks) {

        this.cards = new ArrayList<>();

        for (int i = 0; i < numberOfDecks; i++) {
            for (int j = 2; j < 15; j++) {

                this.cards.add(new Card("Spades", j));
                this.cards.add(new Card("Clubs", j));
                this.cards.add(new Card("Hearts", j));
                this.cards.add(new Card("Diamonds", j));

                //System.out.println(this.cards);
            }

            //shuffle();

        }
    }


    public Deck(int numberOfDecks, String testDeckType) {

        this.cards = new ArrayList<>();
        //creates a deck that is 75% 7s and 25% 6s for testing split functionality
        if(testDeckType.equalsIgnoreCase("split")) {


            for (int i = 0; i < numberOfDecks; i++) {
                for (int j = 2; j < 15; j++) {

                    this.cards.add(new Card("Spades", 7));
                    this.cards.add(new Card("Clubs", 7));
                    this.cards.add(new Card("Hearts", 7));
                    this.cards.add(new Card("Diamonds", 6));

                    //System.out.println(this.cards);
                }
            }
        }

        if(testDeckType.equalsIgnoreCase("blackjacks")) {


            for (int i = 0; i < numberOfDecks; i++) {
                for (int j = 2; j < 15; j++) {

                    this.cards.add(new Card("Spades", 11));
                    this.cards.add(new Card("Clubs", 11));
                    this.cards.add(new Card("Hearts", 14));
                    this.cards.add(new Card("Diamonds", 11));

                    //System.out.println(this.cards);
                }
            }
        }

        shuffle();


    }



    public void shuffle() {
        Collections.shuffle(this.cards);

    }

    public int size() {
        return this.cards.size();
    }


    //getters

    public int getNumberOfCardsRemaining() {
        return this.numberOfCardsRemaining;
    }


    //methods

    public Card[] draw(int numberOfCards) {

        Card[] cardArray = new Card[numberOfCards];


        for (int i = 0; i < numberOfCards; i++) {

            cardArray[i] = this.cards.remove(i);

        }

        this.numberOfCardsRemaining = this.numberOfCardsRemaining - numberOfCards;


        return cardArray;
    }

    public String printCard(int index) {
        System.out.println("printing card at" + index);


        return this.cards.get(index).getFaceValue();

    }

}
