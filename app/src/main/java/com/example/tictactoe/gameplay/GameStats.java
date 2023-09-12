package com.example.tictactoe.gameplay;

public class GameStats {
    int winCount1 = 0;
    int winCount2 = 0;
    int drawCount = 0;


    public int getWinCount1() {
        return winCount1;
    }

    public void setWinCount1(int winCount1) {
        this.winCount1 = winCount1;
    }

    public int getWinCount2() {
        return winCount2;
    }

    public void setWinCount2(int winCount2) {
        this.winCount2 = winCount2;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }
}
