package com.pmiller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    //Card game class constructs what game needs to be played and passes in appropriate information


    //Properties
    private String gameName;
    private Deck deck;
    private Scanner input;
    private Player player;
    //private int numberOfDecks;



    //Constructor


    public CardGame(){


        this.player = new Player("Player1");
        this.deck = new Deck();


    }

    public CardGame(int numberOfDecks, Player currentPlayer){


        this.player = currentPlayer;
        this.deck = new Deck(numberOfDecks);
        System.out.println("In card game constructor checking deck" + this.deck.printCard(2));
        //populate available games
        //this.games.add("Blackjack");
        //this.games.add("Baccarat");
        //this.input = new Scanner(System.in);
        //this.numberOfDecks = numberOfDecks;

    }


    public String getGameName() {
        return gameName;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Deck getDeck(){
        return this.deck;
    }

    //Methods

    public void outputHand(List<Card> hand, String playerName){

        System.out.println(playerName + "'s  hand is " + hand.size() + " cards.");

        for (Card card : hand){

            if(card.isFaceUp() == true){
                System.out.println(card.getFaceValue());
            } else {

                System.out.println("One card face down");
            }


        }


    }





}
