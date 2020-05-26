package com.pmiller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	String playerName;


	System.out.println("Please enter your name: ");
    String userInput = input.nextLine();
        playerName = userInput;


        System.out.println("Hello " + userInput);
        System.out.println("Would you like to play a game? Y or N");
        userInput = input.nextLine();

        if("Y".equalsIgnoreCase(userInput)){
            CardGame cardGameInstance = new CardGame(playerName);
            cardGameInstance.askForGame();
        } else {
            System.out.println("Fine.  Don't play a game");
        }






    }
}
