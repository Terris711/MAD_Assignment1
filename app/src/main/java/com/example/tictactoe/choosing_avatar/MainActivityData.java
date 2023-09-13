package com.example.tictactoe.choosing_avatar;


import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainActivityData extends ViewModel {
    private MutableLiveData<String> clickedValue;
    private Player player1;
    private Player player2;
    private int totalPlayer;
    private int playerCount;
    private int winCond;
    private BoardSize boardSize;
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

    public void checkPlayerAvatar(Player player, Context context, String pageName, int num){
        if (player.noAvatarImage()) {
            Toast.makeText(context, "Please choose " + player.getName()+ "'s avatar",Toast.LENGTH_LONG).show();
        } else {
            setPlayerCount(num);
            setClickedValue(pageName);
        }
    }

    public void setClickedValue(MutableLiveData<String> clickedValue) {
        this.clickedValue = clickedValue;
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

    public void checkPlayerName(Player player, int num, Context context, EditText userName){
        String ErrorUserName = userName.getText().toString();
        if (getPlayerCount()==num) {
            if (ErrorUserName.equals("")) {
                Toast.makeText(context, "Please enter your name!", Toast.LENGTH_LONG).show();
            } else {
                player.setName(userName.getText().toString());
                setClickedValue("avatar");
            }
        }
    }
}
