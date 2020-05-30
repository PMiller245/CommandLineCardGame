package com.pmiller.Blackjack;

import com.pmiller.Card;
import com.pmiller.CardGame;
import com.pmiller.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame extends CardGame {

    //Properties

//    private int numberOfDecks;
//    private int numberOfPlayers;
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

    //testing function to print rest of deck
    public void printBlackjackDeck(){

        for(int i = 0; i < super.getDeck().size();i++){

            System.out.println(super.getDeck().printCard(i));
        }

       // System.out.println("WE MADE A GAME FOR " + super.getPlayer().getPlayerName());
    }

    // where the magic happens.  holds while loop that receives commands from user to play the game
    public void startBlackJackGame(){

        String userInput = "";
        int betAmount = 0;
        //Deal first hand
        System.out.println("Type \"exit\" to exit at anytime");
        super.getDeck().shuffle();
        this.setState(this.bettingState);
        System.out.println("Please place your initial  bets by typing \"bet\"");
        //dealHand();
        //while loop that handles the commands.  exits the program when the command is exit
        while(!"exit".equalsIgnoreCase(userInput)){
            userInput = "";

            userInput = input.nextLine();

            //System.out.println("Your action of " + userInput);

            if(userInput.equalsIgnoreCase("deal")){
                blackjackDealer.discardHand();
                blackjackPlayer.discardHand();
                state.deal();
            }

            if(userInput.equalsIgnoreCase("hit")){
                state.hit();
            }

            if(userInput.equalsIgnoreCase("dd")){
                state.doubleDown();
            }

            if(userInput.equalsIgnoreCase("stand")){
                state.stand();
            }

            if(userInput.equalsIgnoreCase("bet")){
                System.out.println("How much would you like to bet?");
                betAmount = Integer.parseInt(input.nextLine());
                state.makeInitialBet(blackjackPlayer, betAmount);

            }

            if(userInput.equalsIgnoreCase("state")){
                System.out.println(this.state.toString());

            }

        }
    }

    //deals out hand, outputs to table, then calls checkBlackjacks function
    public void dealHand(){

        blackjackPlayer.addToHand(super.getDeck().draw(1)[0]);
        blackjackPlayer.getPlayerHand().get(0).turnOver();
        blackjackDealer.addToHand(super.getDeck().draw(1)[0]);
        blackjackDealer.getPlayerHand().get(0).turnOver();
        blackjackPlayer.addToHand(super.getDeck().draw(1)[0]);
        blackjackPlayer.getPlayerHand().get(1).turnOver();

        //last dealer card is face down
        blackjackDealer.addToHand(super.getDeck().draw(1)[0]);
        //blackjackDealer.getPlayerHand().get(1).turnOver(); //comment to hide dealers card


        outputHand(blackjackDealer);
        //System.out.println("The hand value is: " + calculateBlackjackHandValue(blackjackDealer)); //comment to hide dealers totalPet
        outputHand(blackjackPlayer);
        System.out.println(blackjackPlayer.getPlayerName() + "'s hand value is: " + calculateBlackjackHandValue(blackjackPlayer));


        //Always check for blackjacks after a hand
        checkBlackjacks();




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

    public void checkBlackjacks(){

        int dealerHandValue = calculateBlackjackHandValue(blackjackDealer);
        int playerHandValue = calculateBlackjackHandValue(blackjackPlayer);

        //check for blackjacks (hand values of 0 denote blackjack)
        if(playerHandValue == 0 && !(dealerHandValue == 0 )){
            //System.out.println("Blackjack! Player wins!");
            evaluateWinnings(blackjackPlayer, false, true, false);

        } else if (!(playerHandValue == 0) && dealerHandValue == 0){

           // System.out.println("Blackjack for the dealer!");
            evaluateWinnings(blackjackDealer, false, true, false);
        } else if (playerHandValue == 0 && dealerHandValue == 0){
           // System.out.println("Both players have Blackjack!  Push!!");

            evaluateWinnings(blackjackPlayer, true, true, false);
        }

    }


    //Calculates the winner then calls the evaluate winnings function, passing in the winning player.  Outputs a message if a winner is found

    public void evaluateHand(){

        //blackjacks have already been checked, they are checked within the dealHand function

        int dealerHandValue = calculateBlackjackHandValue(blackjackDealer);
        int playerHandValue = calculateBlackjackHandValue(blackjackPlayer);

        //check for busts

        if(playerHandValue == -1 && !(dealerHandValue == -1 )){
            System.out.println("Player Busts! Dealer Wins!");
            evaluateWinnings(blackjackPlayer, false, false, false);
            return;

        } else if (!(playerHandValue == -1) && dealerHandValue == -1){

            System.out.println("Dealer Busts, Player Wins");
            evaluateWinnings(blackjackPlayer, false, false, true);
            return;
        } else if (playerHandValue == -1 && dealerHandValue == -1){
           // System.out.println("Both players busted!  Push!!");

            evaluateWinnings(blackjackPlayer, true, false, false);

            return;
        }

        if(playerHandValue > dealerHandValue ){

            System.out.println("Player wins!");
            evaluateWinnings(blackjackPlayer, false, false, true);

        } else if (dealerHandValue > playerHandValue){
            System.out.println("Dealer wins!");
            evaluateWinnings(blackjackDealer, false, false, false);

        } else if (dealerHandValue == playerHandValue){

            System.out.println("Push! No winnings paid.");
            evaluateWinnings(blackjackPlayer, true, false, false);
        }



    }

    //takes in winning player and calls in appropriate payout functions
    public void evaluateWinnings(Player evaluatedPlayer, boolean isPush, boolean isBlackjack, boolean didWin){

        int winningPlayerMoneyWon = evaluatedPlayer.getMoneyAtStake();

        if(isBlackjack && !isPush){
            System.out.println(evaluatedPlayer.getPlayerName() + " has a blackjack!");
            //blackjack pay 2:1
            winningPlayerMoneyWon = 3*winningPlayerMoneyWon;
            evaluatedPlayer.distributeWinnings(winningPlayerMoneyWon);
            evaluatedPlayer.outputMoney();
            clearTable();
            return;

        } else if (isBlackjack && isPush){

            System.out.println("Dealer and " + evaluatedPlayer.getPlayerName() + " both have blackjacks!  Push!");
            evaluatedPlayer.distributeWinnings(winningPlayerMoneyWon);
            evaluatedPlayer.outputMoney();
            clearTable();
            return;

        }

        if(isPush){

            evaluatedPlayer.distributeWinnings(winningPlayerMoneyWon);
            evaluatedPlayer.outputMoney();
            clearTable();
            return;

        }


        evaluatedPlayer.distributeWinnings(2*winningPlayerMoneyWon);
        evaluatedPlayer.outputMoney();
        clearTable();

    }

    //clears the hands and sets the state for the next hand
    public void clearTable(){

        System.out.println("Clearing table! Type \"Bet\" to start the next hand");
        blackjackPlayer.discardHand();
        blackjackDealer.discardHand();
        setState(bettingState);

    }


    //after all players stands, goes through the dealer hit procedure then calls payout function
    public void dealerHit(){

        int dealerHandValue = calculateBlackjackHandValue(blackjackDealer);

        blackjackDealer.getPlayerHand().get(1).turnOver();
        outputHand(blackjackDealer);
        System.out.println("Dealer has " + dealerHandValue);

        //TimeUnit.SECONDS.sleep(1);

        if(dealerHandValue >= 17){
            evaluateHand();
            return;
        }

        while(calculateBlackjackHandValue(blackjackDealer) < 17){
            Card hitCard;
            System.out.println("Dealing one card to the dealer");
            hitCard = getDeck().draw(1)[0];
            hitCard.turnOver();
            blackjackDealer.addToHand(hitCard);
            System.out.println("Dealer was dealt a " + hitCard.getFaceValue());

            //check for bust by dealer
            if(calculateBlackjackHandValue(blackjackDealer) == -1){
                evaluateHand();
                return;

            }
            System.out.println("Dealer total is now: "  + calculateBlackjackHandValue(blackjackDealer));
        }

        evaluateHand();

    }





}
