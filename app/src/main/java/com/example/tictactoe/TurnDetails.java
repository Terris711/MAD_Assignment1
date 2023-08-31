package com.example.tictactoe;

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
