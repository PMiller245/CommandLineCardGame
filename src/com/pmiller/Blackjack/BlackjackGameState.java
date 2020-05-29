package com.pmiller.Blackjack;

import com.pmiller.Player;

public interface BlackjackGameState {

    void makeInitialBet(Player player, int bet);
    void hit();
    void stand();
    void deal();
    void makeMidHandBet();
}
