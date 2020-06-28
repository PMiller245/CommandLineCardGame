package com.pmiller.Blackjack;


import com.pmiller.Hand;
import com.pmiller.Player;

public class BlackjackPlayer extends Player {

    //private List<Hand> hands = new ArrayList<>();
    private boolean isSplit;



    private int splitBetAmount;

    private boolean hasBlackjack;

    private BlackjackHand hand;


    public BlackjackPlayer(String playerName, int startingMoney) {

        super(playerName, startingMoney);

    }

    public BlackjackPlayer(boolean isHouse) {

        super(isHouse);
    }

    public boolean getHasBlackjack() {
        return hasBlackjack;
    }

    public void setHasBlackjack(boolean hasBlackjack) {
        this.hasBlackjack = hasBlackjack;
    }

    public boolean getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(boolean isSplit){this.isSplit = isSplit;}

    public int getSplitBetAmount() {
        return splitBetAmount;
    }

    public void splitHand(Hand handToBeSplit, int currentHandIndex) {

        Hand firstSplitHand = new Hand();
        Hand secondSplitHand = new Hand();

        if(isSplit == false){
            firstSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(0));
            secondSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(1));

            splitBetAmount = super.getPlayerHand(0).getMoneyBetOnThisHand();
            super.setMoneyAtStake(0);
            super.addMoney(splitBetAmount); //undoing the initial bet, money will be reset by the placebet commands
            super.getPlayerHands().clear();
            super.getPlayerHands().add(firstSplitHand);
            super.getPlayerHands().add(secondSplitHand);
            super.placeBetOnHand(splitBetAmount,0);
            super.placeBetOnHand(splitBetAmount,1);

            firstSplitHand.outputHandToConsole();
            isSplit = true;


        } else {
            firstSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(0));
            secondSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(1));
            super.getPlayerHands().remove(currentHandIndex);
            int moneyAtStake = super.getMoneyAtStake();
            moneyAtStake = moneyAtStake - splitBetAmount;
            super.setMoneyAtStake(moneyAtStake);
            super.addMoney(splitBetAmount);
            super.getPlayerHands().add(currentHandIndex,firstSplitHand);
            super.getPlayerHands().add(currentHandIndex+1,secondSplitHand);
            super.placeBetOnHand(splitBetAmount,currentHandIndex);
            super.placeBetOnHand(splitBetAmount,currentHandIndex+1);



        }


    }

    public void addHandToPlayerHandList(Hand hand) {


    }










}
