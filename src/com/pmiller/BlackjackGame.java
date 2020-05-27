package com.pmiller;

import java.util.List;
import java.util.Scanner;

public class BlackjackGame extends CardGame{

    //Properties

    private int numberOfDecks;
    private int numberOfPlayers;
    private Scanner input;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    //private Deck deck;






    public BlackjackGame(int numberOfDecks, Player currentPlayer){

        super(numberOfDecks,currentPlayer);
        this.input = new Scanner(System.in);
        System.out.println("Blackjack game starting...");
        startBlackJackGame();


        //printBlackjackDeck(); was printing blackjack deck to verify creation

    }


    public void printBlackjackDeck(){

        for(int i = 0; i < super.getDeck().size();i++){

            System.out.println(super.getDeck().printCard(i));
        }

        System.out.println("WE MADE A GAME FOR " + super.getPlayer().getPlayerName());
    }


    public void startBlackJackGame(){

        String userInput = "";
        //Deal first hand

        //while loop that handles the commands.  exits the program when the command is exit
        while(!"exit".equalsIgnoreCase(userInput)){

            userInput = input.nextLine();

            System.out.println("Your action of " + userInput);




        }



    }

    public void dealStartingHands(){

        playerHand.add(super.getDeck().draw(1)[0]);
        playerHand.get(0).turnOver();
        dealerHand.add(super.getDeck().draw(1)[0]);
        dealerHand.get(0).turnOver();
        playerHand.add(super.getDeck().draw(1)[0]);
        playerHand.get(1).turnOver();
        //last dealer card is face down
        dealerHand.add(super.getDeck().draw(1)[0]);



    }




    public void playerHit(){


    }


    public void dealerHit(){


    }


    public void stand(){



    }


    public void makeBet(){


    }

    public void evaluateWinner(){



    }

    public void clearHand(){


    }




}
