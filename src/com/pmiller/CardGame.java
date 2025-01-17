package com.pmiller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    //Card game class constructs what game needs to be played and passes in appropriate information.
    //provides methods for getting specific players from the game


    //Properties
    private String gameName;
    private Deck deck;
    private Scanner input;
    private List<Player> players = new ArrayList<>();
    private Player player;
    //private int numberOfDecks;



    //Constructor


    public CardGame(){


        //this.player = new Player("Player1");
        this.deck = new Deck();


    }

    public CardGame(int numberOfDecks){


        //this.player = new Player("Player1");
        this.deck = new Deck(numberOfDecks, "split");


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


}
