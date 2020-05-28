package com.pmiller.Blackjack;

import com.pmiller.Player;

public interface BlackjackGameState {

    void makeBet(Player player, int bet);
    void hit();
    void stand();
}
