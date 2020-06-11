package com.pmiller.Blackjack;

import com.pmiller.*;
import com.pmiller.Blackjack.GameStates.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame extends CardGame {


    //Properties

    //    private int numberOfDecks;
//    private int numberOfPlayers;
    private Scanner input;
    private BlackjackPlayer blackjackDealer;
    private BlackjackPlayer blackjackPlayer;
    private Deck deck;
    private int blackjackHandValue;
    private List<String> listOfCommands = new ArrayList<>();
    private BlackjackGameState playerTurnState;
    private BlackjackGameState dealerTurnState;
    private BlackjackGameState bettingState;
    private BlackjackGameState splitState;
    private BlackjackGameState cleanUpState;
    private BlackjackGameState state;


    //private Deck deck;


    public BlackjackGame(int numberOfDecks, BlackjackPlayer currentPlayer) {

        super(numberOfDecks);
        this.deck = new Deck(numberOfDecks);
        this.blackjackPlayer = currentPlayer;
        this.blackjackDealer = new BlackjackPlayer(true);
        this.listOfCommands.add("exit");
        this.listOfCommands.add("deal");
        this.listOfCommands.add("new deal");
        this.listOfCommands.add("hit");
        this.listOfCommands.add("stand");

        this.playerTurnState = new PlayerTurnState(this);
        this.dealerTurnState = new DealerTurnState(this);
        this.bettingState = new BettingState(this);
        this.splitState = new SplitTurnState(this);
        this.cleanUpState = new CleanUpState(this);


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

    public BlackjackPlayer getBlackjackPlayer() {
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

    public BlackjackGameState getSplitState() {
        return splitState;
    }

    public BlackjackGameState getState() {
        return state;
    }

    public void setState(BlackjackGameState state) {
        this.state = state;
    }


    //Setters

    @Override
    public Deck getDeck() {
        return this.deck;
    }

    //testing function to print rest of deck
    public void printBlackjackDeck() {

        for (int i = 0; i < deck.size(); i++) {

            System.out.println(deck.printCard(i));
        }

        // System.out.println("WE MADE A GAME FOR " + super.getPlayer().getPlayerName());
    }

    // where the magic happens.  holds while loop that receives commands from user to play the game
    public void startBlackJackGame() {

        String userInput = "";
        int betAmount = 0;
        //Deal first hand
        System.out.println("Type \"exit\" to exit at anytime");
        deck.shuffle();
        this.setState(this.bettingState);
        System.out.println("Please place your initial  bets by typing \"bet\"");
        //dealHand();
        //while loop that handles the commands.  exits the program when the command is exit
        while (!"exit".equalsIgnoreCase(userInput)) {
            userInput = "";

            userInput = input.nextLine();

            //System.out.println("Your action of " + userInput);

            if (userInput.equalsIgnoreCase("deal")) {
                blackjackDealer.discardHand();
                blackjackPlayer.discardHand();
                state.deal();
            }

            if (userInput.equalsIgnoreCase("hit")) {
                state.hit();
            }

            if (userInput.equalsIgnoreCase("dd")) {
                state.doubleDown();
            }

            if (userInput.equalsIgnoreCase("stand")) {
                state.stand();
            }

            if (userInput.equalsIgnoreCase("bet")) {
                System.out.println("How much would you like to bet?");
                betAmount = Integer.parseInt(input.nextLine());
                state.makeInitialBet(blackjackPlayer, betAmount);

            }

            if (userInput.equalsIgnoreCase("state")) {
                System.out.println(this.state.toString());

            }

            if (userInput.equalsIgnoreCase("money")) {
                System.out.println(blackjackPlayer.getMoney());

            }

        }
    }

    //deals out hand, outputs to table, then calls checkBlackjacks function
    public void dealHand() {


        blackjackPlayer.addToHand(deck.draw(1)[0]);
        blackjackPlayer.getPlayerHand().getCardInSpecificPosition(0).turnOver();
        blackjackDealer.addToHand(deck.draw(1)[0]);
        blackjackDealer.getPlayerHand().getCardInSpecificPosition(0).turnOver();
        blackjackPlayer.addToHand(deck.draw(1)[0]);
        blackjackPlayer.getPlayerHand().getCardInSpecificPosition(1).turnOver();

        //last dealer card is face down
        blackjackDealer.addToHand(deck.draw(1)[0]);
        //blackjackDealer.getPlayerHand().get(1).turnOver(); //comment to hide dealers card


        blackjackDealer.getPlayerHand().outputHandToConsole();
        //System.out.println("The hand value is: " + calculateBlackjackHandValue(blackjackDealer)); //comment to hide dealers totalPet
        blackjackPlayer.getPlayerHand().outputHandToConsole();
        System.out.println(blackjackPlayer.getPlayerName() + "'s hand value is: " + calculateBlackjackHandValue(blackjackPlayer.getPlayerHand()));


        //Always check for blackjacks after a fresh hand is dealt
        checkBlackjacks();


    }


    public int calculateBlackjackHandValue(Hand hand) {
        //returns 0 for a blackjack and -1 for a bust, otherwise it returns the highest hand value

        blackjackHandValue = 0;
        int blackjackCardValue = 0;
        int aceCounter = 0;

        for (Card card : hand.getCardsInHandAsList()) {
            //check for ace and set it to max value
            if (card.getValue() == 14) {
                aceCounter++;
                blackjackCardValue = 11;
            }
            if (card.getValue() <= 10) {
                blackjackCardValue = card.getValue();
            }

            //normalize face cards
            if (card.getValue() > 10 && card.getValue() <= 13) {
                blackjackCardValue = 10;
            }
            blackjackHandValue = blackjackHandValue + blackjackCardValue;
        }

        //check for blackjack
        if (blackjackHandValue == 21 && hand.getCardsInHandAsList().size() == 2) {
            return 0;
        }

        //resize hand based on number of aces, loop does nothing if the hand is 21 or below
        while (aceCounter > 0) {

            //subract value of 1 ace
            if (blackjackHandValue > 21) {
                blackjackHandValue = blackjackHandValue - 10;

            }

            aceCounter--;
        }

        if (blackjackHandValue > 21) {
            return -1;
        }

        return blackjackHandValue;


    }

    public void checkBlackjacks() {

        int dealerHandValue = calculateBlackjackHandValue(blackjackDealer.getPlayerHand());
        int playerHandValue = calculateBlackjackHandValue(blackjackPlayer.getPlayerHand());


        //check for blackjacks (hand values of 0 denote blackjack)
        if (playerHandValue == 0 && !(dealerHandValue == 0)) {
            System.out.println("Blackjack! Player wins!");
            blackjackPlayer.setHasBlackjack(true);
            startPayouts();
            //distributePayouts(blackjackPlayer, false, true, true);

        } else if (!(playerHandValue == 0) && dealerHandValue == 0) {

            System.out.println("Blackjack for the dealer!");
            blackjackDealer.setHasBlackjack(true);
            startPayouts();

            //distributePayouts(blackjackPlayer, false, false, false);
        } else if (playerHandValue == 0 && dealerHandValue == 0) {
            System.out.println("Both players have Blackjack!  Push!!");
            blackjackDealer.setHasBlackjack(true);
            blackjackPlayer.setHasBlackjack(true);
            startPayouts();
            //distributePayouts(blackjackPlayer, true, true, false);
        }

    }

    //TODO implement split


    //takes in a hand and returns the winning multiple (0 for loss, 1 for push, 2 for win)

    public int evaluateHandAndReturnPayoutMultiplier(Hand hand) {

        //blackjacks have already been checked, they are checked within the dealHand function

        int dealerHandValue = calculateBlackjackHandValue(blackjackDealer.getPlayerHand());
        int playerHandValue = calculateBlackjackHandValue(hand);

        if (blackjackDealer.getHasBlackjack() && blackjackPlayer.getHasBlackjack()) {
            return 1;
        } else if (blackjackPlayer.getHasBlackjack()) {
            return 3;
        } else if (blackjackDealer.getHasBlackjack()) {
            return 0;
        }

        //check for busts

        if (playerHandValue == -1 && !(dealerHandValue == -1)) {
            System.out.println("Player Busts! Dealer Wins!");
            //distributePayouts(blackjackPlayer, false, false, false);
            return 0;

        } else if (!(playerHandValue == -1) && dealerHandValue == -1) {

            System.out.println("Dealer Busts, Player Wins");
            //distributePayouts(blackjackPlayer, false, false, true);
            return 2;
        } else if (playerHandValue == -1 && dealerHandValue == -1) {
            // System.out.println("Both players busted!  Push!!");

            // distributePayouts(blackjackPlayer, true, false, false);

            return 1;
        }

        if (playerHandValue > dealerHandValue) {

            System.out.println("Player wins!");
            //distributePayouts(blackjackPlayer, false, false, true);
            return 2;

        } else if (dealerHandValue > playerHandValue) {
            System.out.println("Dealer wins!");
            // distributePayouts(blackjackPlayer, false, false, false);
            return 0;

        } else if (dealerHandValue == playerHandValue) {

            System.out.println("Push! No winnings paid.");
            // distributePayouts(blackjackPlayer, true, false, false);
            return 1;

        }

        return 0;

    }

    //takes in winning player and calls in appropriate payout functions
    public void distributePayouts(BlackjackPlayer evaluatedPlayer, boolean isPush, boolean isBlackjack, boolean didWin) {

        int winningPlayerMoneyWon = evaluatedPlayer.getMoneyAtStake();


        if (isBlackjack && !isPush && didWin) {
            System.out.println(evaluatedPlayer.getPlayerName() + " has a blackjack!");
            //blackjack pay 2:1
            winningPlayerMoneyWon = 3 * winningPlayerMoneyWon;
            evaluatedPlayer.receiveWinnings(winningPlayerMoneyWon, 0);
            evaluatedPlayer.outputMoney();

            clearTableAndSetToBetState();

            return;

        } else if (isBlackjack && isPush) {

            System.out.println("Dealer and " + evaluatedPlayer.getPlayerName() + " both have blackjacks!  Push!");
            evaluatedPlayer.receiveWinnings(winningPlayerMoneyWon, 0);
            evaluatedPlayer.outputMoney();
            clearTableAndSetToBetState();
            return;

        }

        if (isPush) {

            evaluatedPlayer.receiveWinnings(winningPlayerMoneyWon, 0);
            evaluatedPlayer.outputMoney();
            clearTableAndSetToBetState();
            return;

        }

        if (didWin) {
            evaluatedPlayer.receiveWinnings(2 * winningPlayerMoneyWon, 0);
            evaluatedPlayer.outputMoney();
        }

        //player would have lost by this point
        evaluatedPlayer.setMoneyAtStake(0);
        clearTableAndSetToBetState();

    }

    //clears the hands and sets the state for the next hand
    public void clearTableAndSetToBetState() {

        System.out.println("Clearing table! Type \"Bet\" to start the next hand");
        blackjackPlayer.discardHand();
        blackjackDealer.discardHand();
        setState(bettingState);

    }

    public void startPayouts() {

        this.state = cleanUpState;

        if (blackjackPlayer.getPlayerHands().size() >= 1) {

            for (int i = 0; i < blackjackPlayer.getPlayerHands().size(); i++) {


                int winnings = blackjackPlayer.getPlayerHand(i).getMoneyBetOnThisHand() * evaluateHandAndReturnPayoutMultiplier(blackjackPlayer.getPlayerHand(i));
                blackjackPlayer.receiveWinnings(winnings, 0);


            }

        }

        clearTableAndSetToBetState();


    }


    //after all players stands, goes through the dealer hit procedure then calls payout function
    public void dealerHit() {

        int dealerHandValue = calculateBlackjackHandValue(blackjackDealer.getPlayerHand());

        blackjackDealer.getPlayerHand().getCardInSpecificPosition(1).turnOver();
        blackjackDealer.getPlayerHand().outputHandToConsole();
        System.out.println("Dealer has " + dealerHandValue);

        //TimeUnit.SECONDS.sleep(1);

        if (dealerHandValue >= 17) {
            //evaluateHand(blackjackPlayer.getPlayerHand());
            System.out.println("Dealer is greater than 17");

        }

        while (calculateBlackjackHandValue(blackjackDealer.getPlayerHand()) < 17) {
            Card hitCard;
            System.out.println("Dealing one card to the dealer");
            hitCard = getDeck().draw(1)[0];
            hitCard.turnOver();
            blackjackDealer.addToHand(hitCard);
            System.out.println("Dealer was dealt a " + hitCard.getFaceValue());

            //check for bust by dealer
            if (calculateBlackjackHandValue(blackjackDealer.getPlayerHand()) == -1) {
                //evaluateHand(blackjackPlayer.getPlayerHand());
                System.out.println("Dealer bust in dealer hit function");
                break;

            }
            System.out.println("Dealer total is now: " + calculateBlackjackHandValue(blackjackDealer.getPlayerHand()));
        }

        startPayouts();

        //evaluateHand(blackjackPlayer.getPlayerHand());


    }


}
