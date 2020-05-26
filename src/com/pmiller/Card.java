package com.pmiller;

public class Card {

    //Properties

    private String suit;
    private int value;
    private boolean isFaceUp;
    private String color;
    private String faceValue;


    //Constructor

    public Card(String suit, int value) {

        this.suit = suit;
        this.value = value;

        if(this.suit.equals("Spades") || this.suit.equals("Clubs")) {

            this.color = "black";

        } else {

            this.color = "red";
        }

        if (value <= 10 && value >= 2) {

            this.faceValue = Integer.toString(value) + " of " + this.suit;

        } else if (value == 11) {
            this.faceValue = "Jack of " + this.suit;

        } else if (value == 12) {
            this.faceValue = "Queen of " + this.suit;
        } else if (value == 13) {
            this.faceValue = "King of "+ this.suit;

        } else if (value == 14) {
            this.faceValue = "Ace of "+ this.suit;
        }

        this.isFaceUp = false;

    }


    //getters

    public String getSuit() {
        return suit;
    }
    public int getValue() {
        return value;
    }
    public boolean isFaceUp() {
        return isFaceUp;
    }
    public String getColor() {
        return color;
    }

    public String getFaceValue() {
        return this.faceValue;
    }

    //Methods

    public boolean turnOver() {

        this.isFaceUp = !this.isFaceUp;

        return this.isFaceUp;

    }


}
