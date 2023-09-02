package com.example.tictactoe;

public class Player {
    private String name;
    private int avatarImage;

    public Player (String name, int avatarImage){
        this.name = name;
        this.avatarImage = avatarImage;
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
