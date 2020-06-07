package com.pmiller.Blackjack.GameStates;

import com.pmiller.Player;

public interface BlackjackGameState {

    void makeInitialBet(Player player, int bet);

    void hit();

    void stand();

    void deal();

    void makeMidHandBet();

    void split();

    void doubleDown();
}
