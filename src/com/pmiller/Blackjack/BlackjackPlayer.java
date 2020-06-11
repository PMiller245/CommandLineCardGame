package com.pmiller.Blackjack;


import com.pmiller.Card;
import com.pmiller.Hand;
import com.pmiller.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackPlayer extends Player {

    private List<Hand> hands = new ArrayList<>();
    private boolean isSplit;



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


    public void splitHand(Hand handToBeSplit) {

        Hand firstSplitHand = new Hand();
        Hand secondSplitHand = new Hand();

        firstSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(0));
        secondSplitHand.addToHand(handToBeSplit.getCardInSpecificPosition(1));


        hands.add(firstSplitHand);
        hands.add(secondSplitHand);

    }

    public void addHandToPlayerHandList(Hand hand) {


    }


    //cleanup to remove hand at first position








}
