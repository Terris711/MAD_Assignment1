package com.example.tictactoe.choosing_avatar;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int avatarImage;

    public Player (String name, int avatarImage){
        this.name = name;
        this.avatarImage = avatarImage;
    }

    public boolean noAvatarImage(){
        return avatarImage == 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(int avatarImage) {
        this.avatarImage = avatarImage;
    }
}
