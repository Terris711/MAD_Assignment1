package com.example.tictactoe;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameStateViewModel extends ViewModel {
    private MutableLiveData<BoardSize> boardSize;

    public GameStateViewModel() {
        this.boardSize = new MediatorLiveData<>();
        this.boardSize.setValue(BoardSize.ThreeXThree);
    }

    public BoardSize getBoardSize() {
        return boardSize.getValue();
    }

    public void setBoardSize(BoardSize boardSize) {
        this.boardSize.setValue(boardSize);
    }
}


enum BoardSize {
    ThreeXThree, FourXFour, FiveXFive;
}
