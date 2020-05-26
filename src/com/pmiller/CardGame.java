package com.pmiller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {

    //Card game class constructs what game needs to be played and passes in appropriate information


    //Properties
    private String gameName;
    private Deck deck;
    private List<String> games = new ArrayList<>();
    private Scanner input;
    private Player playerName;



    //Constructor


    public CardGame(){


        this.playerName = new Player("Player");
        this.deck = new Deck();
        //populate available games
        this.games.add("Blackjack");
        this.games.add("Baccarat");
        this.input = new Scanner(System.in);

    }

    public CardGame(String playerName){


        this.playerName = new Player(playerName);
        this.deck = new Deck();
        //populate available games
        this.games.add("Blackjack");
        this.games.add("Baccarat");
        this.input = new Scanner(System.in);

    }


    public String getGameName() {
        return gameName;
    }

    //Methods


    public void askForGame(){

        System.out.println("Please choose from these games: ");

        for(String game : this.games){
            System.out.println(game);
        }

        String userInput = input.nextLine();

        if("Blackjack".equalsIgnoreCase(userInput)){
            System.out.println("Creating blackjack game");
            createBlackjackGame();
        }




    }


    public void createBlackjackGame(){
        System.out.println("How many decks would you like?");
        int numberOfDecks = Integer.parseInt(this.input.nextLine());
        BlackjackGame createdGame = new BlackjackGame(numberOfDecks, this.playerName);
        System.out.println("Blackjack game starting...");


    }


}
