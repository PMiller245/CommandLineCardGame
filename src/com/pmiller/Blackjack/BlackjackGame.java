package com.pmiller.Blackjack;

import com.pmiller.Card;
import com.pmiller.CardGame;
import com.pmiller.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame extends CardGame {

    //Properties

    private int numberOfDecks;
    private int numberOfPlayers;
    private Scanner input;
    private Player blackjackDealer;



    private int playerHandValue;



    private List<Card> playerHand = new ArrayList<>();
    private List<Card> dealerHand = new ArrayList<>();
    //private Deck deck;






    public BlackjackGame(int numberOfDecks, Player currentPlayer){

        super(numberOfDecks,currentPlayer);
        this.blackjackDealer = new Player("Dealer", 1000000);
        this.input = new Scanner(System.in);
        System.out.println("Blackjack game starting...");
        startBlackJackGame();


        //printBlackjackDeck(); was printing blackjack deck to verify creation

    }


    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public int getPlayerHandValue() {
        return playerHandValue;
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
        System.out.println("Type \"exit\" to exit at anytime");
        dealHand();

        //while loop that handles the commands.  exits the program when the command is exit
        while(!"exit".equalsIgnoreCase(userInput)){

            userInput = input.nextLine();

            System.out.println("Your action of " + userInput);




        }



    }

    public void dealHand(){

        playerHand.add(super.getDeck().draw(1)[0]);
        playerHand.get(0).turnOver();
        dealerHand.add(super.getDeck().draw(1)[0]);
        dealerHand.get(0).turnOver();
        playerHand.add(super.getDeck().draw(1)[0]);
        playerHand.get(1).turnOver();
        //last dealer card is face down
        dealerHand.add(super.getDeck().draw(1)[0]);

        outputHand(dealerHand, "Dealer");
        outputHand(playerHand, super.getPlayer().getPlayerName());




    }

    public int updateHandTotal(Player player){

        int value = 0;

        for(Card card: playerHand){



        }

        return value;
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
