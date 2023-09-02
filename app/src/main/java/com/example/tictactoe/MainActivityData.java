package com.example.tictactoe;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData extends ViewModel {
    private MutableLiveData<String> clickedValue;
    private Player player1;
    private Player player2;
    private int totalPlayer;
    private int playerCount;
    public MainActivityData() {
        clickedValue = new MediatorLiveData<String>();
        clickedValue.setValue("player");
        this.player1 = new Player("",0);
        this.player2 = new Player("",0);
        this.totalPlayer = totalPlayer;
        this.playerCount = playerCount;
    }

    public int getTotalPlayer() {return totalPlayer;}
    public void setTotalPlayer(int totalPlayer) { this.totalPlayer = totalPlayer; }
    public int getPlayerCount() { return playerCount; }
    public void setPlayerCount(int playerCount) { this.playerCount = playerCount;}
    public Player getPlayer1() { return player1; }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public Player getPlayer2() { return player2; }
    public void setPlayer2(Player player2) {this.player2 = player2; }
    public String getClickedValue(){
        return clickedValue.getValue();
    }
    public MutableLiveData<String> getClicked() {
        return clickedValue;
    }
    public void setClickedValue(String value){
        clickedValue.setValue(value);
        System.out.println("test" + clickedValue.getValue());
    }
}
