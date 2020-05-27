package com.pmiller;

public class Player {

    private int money;
    private boolean isDealer;
    private String playerName;
    private int moneyAtStake;


    public Player(String playerName, int startingMoney){

        this.playerName = playerName;
        this.money = startingMoney;
        this.moneyAtStake = 0;
    }

    public Player(String playerName){

        this.playerName = playerName;
        this.moneyAtStake = 0;

    }


    public Player(){

        this.playerName = "Player";
        this.money = 100;
        this.moneyAtStake = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setMoney(int money){
        this.money = money;
    }


    //Methods

    public boolean makeBid(int bidAmount){
        //returns false if bid cant be made.  Updates money if bid is made

        if(bidAmount > this.money){

            System.out.println("bid cant be made");
            return false;
        } else {
            this.money = this.money - bidAmount;
            return true;
        }

    }


}
