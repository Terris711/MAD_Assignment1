package com.example.tictactoe.choosing_avatar;


import android.content.Context;
import android.widget.TextView;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainActivityData extends ViewModel {
    private MutableLiveData<String> clickedValue;
    private Player player1;
    private Player player2;

    private Player modifyingPlayer;
    private int totalPlayer;
    private int playerCount;
    private int winCond;
    private BoardSize boardSize;
    private int AIsymbol;

    public MainActivityData() {
        clickedValue = new MediatorLiveData<String>();
        clickedValue.setValue("player");
        this.player1 = new Player("",0);
        this.player2 = new Player("",0);
        this.modifyingPlayer = this.player1;
        this.totalPlayer = totalPlayer;
        this.playerCount = playerCount;
        this.AIsymbol = AIsymbol;
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

    public Player getModifyingPlayer() { return modifyingPlayer; }

    public void setModifyingPlayer(Player modifyingPlayer) { this.modifyingPlayer = modifyingPlayer; }

    public String getClickedValue(){
        return clickedValue.getValue();
    }
    public MutableLiveData<String> getClicked() {
        return clickedValue;
    }

    public int getAIsymbol() { return AIsymbol; }

    public void setAIsymbol(int AIsymbol) { this.AIsymbol = AIsymbol; }

    public void setClickedValue(String value){
        clickedValue.setValue(value);
        System.out.println("test" + clickedValue.getValue());
    }

    public void checkPlayerAvatar(Player player, String pageName, TextView avatarError){
        if (player.noAvatarImage()) {
            avatarError.setText("Please choose " + player.getName()+ "'s avatar");
        } else {
            setClickedValue(pageName);
        }
    }
    public void playerVSAIsymbol(Player player1, int sb1, int sb2 ){
        player1.setSymbol(sb1);
        setAIsymbol(sb2);
    }

    public void playerVSplayerSymbol(Player player1, Player player2, int sb1, int sb2){
        player1.setSymbol(sb1);
        player2.setSymbol(sb2);
    }

    public int getWinCond() {
        return winCond;
    }

    public void setWinCond(int winCond) {
        this.winCond = winCond;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

}
