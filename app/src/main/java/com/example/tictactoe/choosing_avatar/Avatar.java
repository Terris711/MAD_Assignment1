package com.example.tictactoe.choosing_avatar;

public class Avatar {
    private String name;
    private int image;

    public Avatar(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
