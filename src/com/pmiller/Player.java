package com.pmiller;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int money;
    private boolean isDealer;
    private String playerName;
    private int moneyAtStake;
    private List<Card> playerHand = new ArrayList<>();



    public Player(boolean isDealer){

        this.playerName = "Dealer";
        this.isDealer = isDealer;
        this.money = 1000000;
        this.moneyAtStake = 0;
    }



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

    //public void setMoneyAtStake(int moneyAtStake) { this.moneyAtStake = moneyAtStake;}
    public int getMoneyAtStake(){return this.moneyAtStake;}

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public boolean getIsDealer(){return isDealer;}

    public int getMoney(){return this.money;}

    //Methods

    public boolean placeBet(int bidAmount){
        //returns false if bid cant be made.  Updates money at stake and money if bid is made

        if(bidAmount > this.money){

            System.out.println("bid cant be made");
            return false;
        } else {
            this.money = this.money - bidAmount;
            this.moneyAtStake = this.moneyAtStake + bidAmount;
            return true;
        }


    }


    //passed in winnings calculated at the blackjack table with this players money at stake property
    public void distributeWinnings(int winnings){

        this.moneyAtStake = 0;
        this.money = this.money + winnings;

    }

    public void outputMoney(){
        System.out.println(this.playerName + " has "+ this.money + " in his bank account" );

    }

    public void outputMoneyAtStake(){
        System.out.println(this.playerName + " has "+ this.moneyAtStake + " currently at stake" );
    }

    public void addToHand(Card card){
        this.playerHand.add(card);

    }

    public void discardHand(){
        this.playerHand.clear();

    }


}
