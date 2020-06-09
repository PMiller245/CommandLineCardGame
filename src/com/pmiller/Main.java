package com.pmiller;

import com.pmiller.Blackjack.BlackjackGame;
import com.pmiller.Blackjack.BlackjackPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);

        BlackjackPlayer testPlayer = new BlackjackPlayer("Peter", 100);
        BlackjackGame createdGame = new BlackjackGame(2, testPlayer);

	System.out.println("Please enter your name: ");
    String userInput = input.nextLine();
        BlackjackPlayer currentPlayer = new BlackjackPlayer(userInput, 100);


        System.out.println("Hello " + currentPlayer.getPlayerName());
        System.out.println("Would you like to play a game? Y or N");
        userInput = input.nextLine();

        if("Y".equalsIgnoreCase(userInput)){
            System.out.println("Excellent!");
            //card game factory handles getting all of the required input from the user to create the games properly then creates the correct game object
            CardGameFactory cardGameInstance= new CardGameFactory(currentPlayer);

        } else {
            System.out.println("Fine.  Don't play a game");
        }






    }
}
