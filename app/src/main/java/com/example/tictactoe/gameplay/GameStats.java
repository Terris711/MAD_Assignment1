package com.example.tictactoe.gameplay;

public class GameStats {
    int winCount1 = 0;
    int winCount2 = 0;
    int drawCount = 0;

    double winPercentage1 = 0;
    double winPercentage2 = 0;

    public double getWinPercentage1() {
        return winPercentage1;
    }

    public void setWinPercentage1(double winPercentage1) {
        this.winPercentage1 = winPercentage1;
    }

    public double getWinPercentage2() {
        return winPercentage2;
    }

    public void setWinPercentage2(double winPercentage2) {
        this.winPercentage2 = winPercentage2;
    }

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
