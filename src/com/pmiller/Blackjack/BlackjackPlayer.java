package com.pmiller.Blackjack;


import com.pmiller.Player;

public class BlackjackPlayer extends Player {

    private BlackjackHand hand;


    public BlackjackPlayer(Player player){

        this.hand = new BlackjackHand(player.getPlayerHand());

    }







}
