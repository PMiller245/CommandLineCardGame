package com.pmiller;

public class Player {

    private int money;
    private boolean isDealer;
    private String playerName;


    public Player(String playerName, int startingMoney){

        this.playerName = playerName;
        this.money = startingMoney;
    }

    public Player(String playerName){

        this.playerName = playerName;

    }


    public Player(){

        this.playerName = "Player";
        this.money = 100;
    }

    public String getPlayerName() {
        return playerName;
    }
}
