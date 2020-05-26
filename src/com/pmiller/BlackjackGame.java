package com.pmiller;

public class BlackjackGame{

    //Properties

    private int numberOfDecks;
    private int numberOfPlayers;
    private Deck deck;



    public BlackjackGame(int numberOfDecks){





    }


    public BlackjackGame(int numberOfDecks, Player currentPlayer){

        this.numberOfDecks = numberOfDecks;
        this.deck = new Deck(this.numberOfDecks);
        deck.shuffle();

        for(int i = 0; i < this.deck.size();i++){



            System.out.println(deck.printCard(i));
        }


        System.out.println("WE MADE A GAME FOR " + currentPlayer.getPlayerName());



    }




}
