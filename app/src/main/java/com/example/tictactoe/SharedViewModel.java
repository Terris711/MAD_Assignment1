package com.example.tictactoe;

import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private String player1Username = "";
    private String player2Username = "";

    public String getPlayer1Username() {
        return player1Username;
    }

    public void setPlayer1Username(String player1Username) {
        this.player1Username = player1Username;
    }

    public String getPlayer2Username() {
        return player2Username;
    }

    public void setPlayer2Username(String player2Username) {
        this.player2Username = player2Username;
    }
}
