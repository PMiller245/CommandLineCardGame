package com.pmiller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    protected Hand hand = new Hand();
    private int money;
    private boolean isHouse;
    private String playerName;
    protected int moneyAtStake;
    private boolean isDealer;
    private Map<String, Hand> hands = new HashMap<>();


    public Player(boolean isHouse) {

        this.playerName = "House";
        this.isHouse = isHouse;
        this.money = 1000000;
        this.moneyAtStake = 0;
    }


    public Player(String playerName, int startingMoney) {

        this.playerName = playerName;
        this.money = startingMoney;
        this.moneyAtStake = 0;
        this.isDealer = false;
    }

    public Player(String playerName) {

        this.playerName = playerName;
        this.money = 100;
        this.moneyAtStake = 0;
        this.isDealer = false;

    }


    public Player() {

        this.playerName = "Player";
        this.money = 100;
        this.moneyAtStake = 0;
        this.isDealer = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setMoneyAtStake(int moneyAtStake) {
        this.moneyAtStake = moneyAtStake;
    }

    public int getMoneyAtStake() {
        return this.moneyAtStake;
    }

    public Hand getPlayerHand() {
        return hand;
    }

    public boolean getIsHouse() {
        return isHouse;
    }

    public int getMoney() {
        return this.money;
    }

    public void setPlayerHand(List<Card> playerHand) {
        hand.setCardsInHand(playerHand);
    }

    //Methods

    public boolean placeBet(int bidAmount) {
        //returns false if bid cant be made.  Updates money at stake and money if bid is made

        if (bidAmount > this.money) {

            System.out.println("bid cant be made");
            return false;
        } else {
            this.money = this.money - bidAmount;
            this.moneyAtStake = this.moneyAtStake + bidAmount;
            return true;
        }


    }

    public void receiveWinnings(int winnings) {

        this.moneyAtStake = 0;
        this.money = this.money + winnings;

    }

    public void outputMoney() {
        System.out.println(this.playerName + " has " + this.money + " in his bank account");

    }

    public void outputMoneyAtStake() {
        System.out.println(this.playerName + " has " + this.moneyAtStake + " currently at stake");
    }

    public void addHandToPlayer(String handName, Hand handToAdd) {

        this.hands.put(handName, handToAdd);

    }

    public void addToHand(Card card) {

        this.hand.addToHand(card);
    }

    //if they have more than one hand
    public void addToHand(Card card, String handName) {

        hands.get(handName).addToHand(card);
    }

    public void discardHand() {

        hand.discardHand();
    }

    //if they have more than one hand
    public void discardHand(String handName) {

        hands.get(handName).discardHand();
    }

    public void removeHand(String handName) {

        hands.remove(handName);

    }


}
