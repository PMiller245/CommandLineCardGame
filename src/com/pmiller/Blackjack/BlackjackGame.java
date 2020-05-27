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
    private Player blackjackPlayer;
    private int blackjackHandValue;
    private List<String> listOfCommands = new ArrayList<>();
    private BlackjackGameState playerTurnState;
    private BlackjackGameState dealerTurnState;
    private BlackjackGameState bettingState;
    private BlackjackGameState state;


    //private Deck deck;






    public BlackjackGame(int numberOfDecks, Player currentPlayer){

        super(numberOfDecks,currentPlayer);
        this.blackjackPlayer = super.getPlayer();
        this.blackjackDealer = new Player(true);

        this.listOfCommands.add("exit");
        this.listOfCommands.add("deal");
        this.listOfCommands.add("new deal");
        this.listOfCommands.add("hit");
        this.listOfCommands.add("stand");

        this.playerTurnState = new PlayerTurnState(this);
        this.dealerTurnState = new DealerTurnState(this);
        this.bettingState = new BettingState(this);

        //game should be in betting state to begin with
        this.state = playerTurnState;




        this.input = new Scanner(System.in);
        System.out.println("Blackjack game starting...");
        startBlackJackGame();


        //printBlackjackDeck(); was printing blackjack deck to verify creation

    }

    //Getters

    public Player getBlackjackDealer() {
        return blackjackDealer;
    }

    public Player getBlackjackPlayer() {
        return blackjackPlayer;
    }

    public BlackjackGameState getPlayerTurnState() {
        return playerTurnState;
    }

    public BlackjackGameState getDealerTurnState() {
        return dealerTurnState;
    }

    public BlackjackGameState getBettingState() {
        return bettingState;
    }

    public BlackjackGameState getState() {
        return state;
    }


    //Setters


    public void setState(BlackjackGameState state) {
        this.state = state;
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
        super.getDeck().shuffle();
        dealHand();

        //while loop that handles the commands.  exits the program when the command is exit
        while(!"exit".equalsIgnoreCase(userInput)){
            userInput = "";

            userInput = input.nextLine();

            System.out.println("Your action of " + userInput);

            if(userInput.equalsIgnoreCase("deal")){
                blackjackDealer.discardHand();
                blackjackPlayer.discardHand();

                dealHand();

            }

            if(userInput.equalsIgnoreCase("hit"));{

                playerHit();

            }
        }
    }

    public void dealHand(){

        blackjackPlayer.addToHand(super.getDeck().draw(1)[0]);
        blackjackPlayer.getPlayerHand().get(0).turnOver();
        blackjackDealer.addToHand(super.getDeck().draw(1)[0]);
        blackjackDealer.getPlayerHand().get(0).turnOver();
        blackjackPlayer.addToHand(super.getDeck().draw(1)[0]);
        blackjackPlayer.getPlayerHand().get(1).turnOver();

        //last dealer card is face down
        blackjackDealer.addToHand(super.getDeck().draw(1)[0]);
        //blackjackDealer.getPlayerHand().get(1).turnOver(); comment that shows the dealer hand


        outputHand(blackjackDealer);
        //System.out.println("The hand value is: " + calculateBlackjackHandValue(blackjackDealer));
        outputHand(blackjackPlayer);
        System.out.println("The hand value is: " + calculateBlackjackHandValue(blackjackPlayer));




    }

    public int calculateBlackjackHandValue(Player player){
        //returns 0 for a blackjack and -1 for a bust, otherwise it returns the highest hand value

        blackjackHandValue = 0;
        int blackjackCardValue = 0;
        int aceCounter = 0;

        for (Card card : player.getPlayerHand()){
            //check for ace and set it to max value
            if(card.getValue() == 14){
                aceCounter++;
                blackjackCardValue = 11;
            }
            if(card.getValue() <= 10){
                blackjackCardValue =  card.getValue();
            }

            //normalize face cards
            if(card.getValue() > 10 && card.getValue() <= 13){
                blackjackCardValue = 10;
            }
            blackjackHandValue = blackjackHandValue + blackjackCardValue;
        }

        //check for blackjack
        if(blackjackHandValue == 21 && player.getPlayerHand().size() == 2){
            return 0;
        }

        //resize hand based on number of aces, loop does nothing if the hand is 21 or below
        while(aceCounter > 0){

            //subract value of 1 ace
            if(blackjackHandValue > 21){
                blackjackHandValue = blackjackHandValue - 10;

            }

            aceCounter--;
        }

        if (blackjackHandValue > 21){
            return -1;
        }

        return blackjackHandValue;


    }



    public void evaluateHand(){


        //check for blackjacks
        if(calculateBlackjackHandValue(blackjackPlayer) == 21 && blackjackPlayer.getPlayerHand().size() == 2){
            System.out.println("Blackjack!");

        }



    }


    public void playerHit(){

        System.out.println("Hit initiated");
        state.hit();


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
