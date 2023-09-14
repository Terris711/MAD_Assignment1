package com.example.tictactoe.choosing_avatar;


import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
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
    private String AIsymbol;

    public MainActivityData() {
        clickedValue = new MediatorLiveData<String>();
        clickedValue.setValue("player");
        this.player1 = new Player("",0);
        this.player2 = new Player("",0);
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
    public String getClickedValue(){
        return clickedValue.getValue();
    }
    public MutableLiveData<String> getClicked() {
        return clickedValue;
    }

    public String getAIsymbol() { return AIsymbol; }

    public void setAIsymbol(String AIsymbol) { this.AIsymbol = AIsymbol; }

    public void setClickedValue(String value){
        clickedValue.setValue(value);
        System.out.println("test" + clickedValue.getValue());
    }

    public void checkPlayerAvatar(Player player, String pageName, int num, TextView avatarError){
        if (player.noAvatarImage()) {
            avatarError.setText("Please choose " + player.getName()+ "'s avatar");
        } else {
            setPlayerCount(num);
            setClickedValue(pageName);
        }
    }
    public void playerVSAIsymbol(Player player, Context context,String sb1, String sb2 ){
        player.setSymbol(sb1);
        //set AI symbol
        setAIsymbol(sb2);
        Toast.makeText(context,"AI's symbol is " + getAIsymbol(), Toast.LENGTH_SHORT).show();
    }

    public void playerVSplayerSymbol(Player player1, Player player2, Context context, String sb1, String sb2){
        player1.setSymbol(sb1);
        //set AI symbol
        player2.setSymbol(sb2);
        Toast.makeText(context,player2.getName() + "'s symbol is " + player2.getSymbol(),Toast.LENGTH_SHORT).show();
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
