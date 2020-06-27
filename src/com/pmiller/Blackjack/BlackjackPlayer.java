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
            super.placeBet(splitBetAmount,0);
            super.placeBet(splitBetAmount,1);

            firstSplitHand.outputHandToConsole();
            isSplit = true;


        } else {
            firstSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(0));
            secondSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(1));
            firstSplitHand.setMoneyBetOnThisHand(splitBetAmount);
            secondSplitHand.setMoneyBetOnThisHand(splitBetAmount);
            super.getPlayerHands().remove(currentHandIndex);
            super.getPlayerHands().add(firstSplitHand);
            super.getPlayerHands().add(secondSplitHand);

        }


    }

    public void addHandToPlayerHandList(Hand hand) {


    }










}
