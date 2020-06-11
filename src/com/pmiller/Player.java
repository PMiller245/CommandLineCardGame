package com.pmiller;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private Hand hand = new Hand();
    private int money;
    private boolean isHouse;
    private String playerName;
    private int moneyAtStake;
    private boolean isDealer;
    private List<Hand> hands = new ArrayList<>();



    public Player(boolean isHouse) {

        this.playerName = "House";
        this.isHouse = isHouse;
        this.money = 1000000;
        this.moneyAtStake = 0;
        this.hands.add(this.hand);
    }


    public Player(String playerName, int startingMoney) {

        this.playerName = playerName;
        this.money = startingMoney;
        this.moneyAtStake = 0;
        this.isDealer = false;
        this.hands.add(this.hand);
    }

    public Player(String playerName) {

        this.playerName = playerName;
        this.money = 100;
        this.moneyAtStake = 0;
        this.isDealer = false;
        this.hands.add(this.hand);

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
        return hands.get(0);
    }

    public boolean getIsHouse() {
        return isHouse;
    }

    public int getMoney() {
        return this.money;
    }


    public void setPlayerHand(List<Card> playerHand) {
        hands.get(0).setCardsInHand(playerHand);
    }

    //Methods

    public boolean placeBet(int bidAmount, int handBetOn) {
        //returns false if bid cant be made.  Updates money at stake and money if bid is made

        hands.get(handBetOn).addToMoneyBetOnThisHand(bidAmount);

        if (bidAmount > this.money) {

            System.out.println("bid cant be made");
            return false;
        } else {
            this.money = this.money - bidAmount;
            this.moneyAtStake = this.moneyAtStake + bidAmount;
            return true;
        }


    }

    public void receiveWinnings(int winnings, int handBetOn) {

        this.moneyAtStake -= hands.get(handBetOn).getMoneyBetOnThisHand();;
        hands.get(handBetOn).setMoneyBetOnThisHand(0);
        this.money = this.money + winnings;

    }

    public void outputMoney() {
        System.out.println(this.playerName + " has " + this.money + " in his bank account");

    }

    public void outputMoneyAtStake() {
        System.out.println(this.playerName + " has " + this.moneyAtStake + " currently at stake");
    }


    //When there is only one hand, default to index 0
    public void addToHand(Card card) {

        this.hands.get(0).addToHand(card);
    }



    public void discardHand() {

        hands.get(0).discardHand();
    }

    public Hand getPlayerHand(int index) {

        return hands.get(index);


    }

    public List<Hand> getPlayerHands() {

        return hands;


    }

    public void removeHand(int index) {
        if (hands.size() > 0) {

            hands.remove(index);
        }

    }


    public void addToHand(Card card, int handIndex) {

        hands.get(handIndex).addToHand(card);

    }




}
