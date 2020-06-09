package com.pmiller;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Blackjack.BlackjackPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGameFactory {

    private List<String> listOfGames = new ArrayList<>();
    private Scanner input;
    private BlackjackPlayer currentPlayer;



    public CardGameFactory(BlackjackPlayer currentPlayer){


        this.currentPlayer = currentPlayer;
        //populates list of selectable games
        this.listOfGames.add("Blackjack");
        this.listOfGames.add("Baccarat");
        this.input = new Scanner(System.in);


        askForGame();

    }

    public void askForGame(){

        //function that prompts the user then calls the specific "create" method that will ask for the relevant inputs

        System.out.println("Please choose from these games: ");

        for(String game : this.listOfGames){
            System.out.println(game);
        }

        String userInput = input.nextLine();

        if("Blackjack".equalsIgnoreCase(userInput)){
            System.out.println("Creating blackjack game");
            createBlackjackGame();
        }


    }


    public void createBlackjackGame(){
        //asks how many deck and starting money to initiate game
        System.out.println("How many decks would you like?");
        int numberOfDecks = Integer.parseInt(this.input.nextLine());
        System.out.println("How much money would you like to gamble with? Whole dollar amounts only");
        currentPlayer.setMoney(Integer.parseInt(this.input.nextLine()));
        BlackjackGame createdGame = new BlackjackGame(numberOfDecks, currentPlayer);



    }




}
