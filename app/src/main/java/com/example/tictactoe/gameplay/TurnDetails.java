package com.example.tictactoe.gameplay;

import com.example.tictactoe.gameplay.Status;
import com.example.tictactoe.gameplay.Turn;

public class TurnDetails {
    private Status gameStatus;
    private Turn turn;

    public TurnDetails(Status gameStatus, Turn turn) {
        this.gameStatus = gameStatus;
        this.turn = turn;
    }

    public Status getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Status gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
