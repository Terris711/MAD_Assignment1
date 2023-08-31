package com.example.tictactoe;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameStateViewModel extends ViewModel {
    final MutableLiveData<Turn[][]> liveBoard = new MediatorLiveData<>();
    final  MutableLiveData<Turn> liveTurn = new MediatorLiveData<>();
    final MutableLiveData<Boolean> isGameEnded = new MediatorLiveData<>();


    public GameStateViewModel() {
    }

    public TurnDetails play(int boxNumber) {
        Turn[][] board = liveBoard.getValue();
        Turn turn = liveTurn.getValue();
        int colLen = board[0].length;
        int row = (boxNumber - 1) / colLen;
        int col = (boxNumber - 1) % colLen;
        board[row][col] = turn;
        Status gameStatus = checkEnd(row,col) ? Status.Finished : Status.ONGOING;
        TurnDetails res = new TurnDetails(gameStatus, turn);
        turn = turn == Turn.X ? Turn.O : Turn.X;

        liveBoard.setValue(board);
        liveTurn.setValue(turn);
        return res;
    }

    private boolean checkEnd(int row, int col) {
        return
                checkVertical(row, col) ||
                        checkHorizontal(row,col);
    }

    private boolean checkDiagonal(int row, int col) {
        return false;
    }

    private boolean checkHorizontal(int row, int col) {
        Turn[][] board = liveBoard.getValue();

        Turn cur = board[row][col];
        if (col - 2 >= 0
                &&  cur == board[row][col - 1]
                && cur == board[row][col -2]) {
            return true;
        }
        if (col + 2 < board.length
                && cur == board[row][col + 1]
                && cur == board[row][col + 2]) {
            return true;
        }
        if (col > 0 && col < board.length - 1
                && cur == board[row][col - 1]
                && cur == board[row][col + 1]) {
            return true;
        }
        return false;
    }

    private boolean checkVertical(int row, int col) {
        Turn[][] board = liveBoard.getValue();

        Turn cur = board[row][col];
        if (row - 2 >= 0
                && cur == board[row - 1][col]
                && cur == board[row-2][col]) {
            return true;
        }
        if (row + 2 < board.length
                && cur == board[row + 1][col]
                && cur == board[row + 2][col]) {
            return true;
        }
        if (row > 0 && row < board.length - 1
                && cur == board[row - 1][col]
                && cur == board[row+1][col]) {
            return true;
        }
        return false;
    }



}




enum Turn {
    X, O;
}

enum Status {
    Finished, ONGOING
}




enum BoardSize {
    ThreeXThree, FourXFour, FiveXFive;
}
