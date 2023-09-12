package com.example.tictactoe.gameplay;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Stack;

public class GameStateViewModel extends ViewModel {
    final MutableLiveData<Turn[][]> liveBoard = new MediatorLiveData<>();
    final  MutableLiveData<Turn> liveTurn = new MediatorLiveData<>();
    final MutableLiveData<Status> gameStatus = new MediatorLiveData<>();
    final MutableLiveData<Integer> availableMove = new MediatorLiveData<>();

    final MutableLiveData<Stack<TurnHistory>> moveStack = new MediatorLiveData<>();

    final MutableLiveData<GameStats> gameStats = new MediatorLiveData<>();
    final MutableLiveData<Boolean> isAI = new MediatorLiveData<>();


    public GameStateViewModel() {
        moveStack.setValue(new Stack<>());
        gameStats.setValue(new GameStats());

    }

    public TurnDetails play(int boxNumber) {
        Turn[][] board = liveBoard.getValue();
        Turn turn = liveTurn.getValue();
        int colLen = board[0].length;
        int row = (boxNumber - 1) / colLen;
        int col = (boxNumber - 1) % colLen;
        board[row][col] = turn;
        Status gameStatus = checkEnd(row,col) ? Status.Finished : Status.ONGOING; // Status finished if game end else status ongoing
        if (gameStatus == Status.ONGOING && availableMove.getValue() == 0) {
            gameStatus = Status.DRAW;

        }
        TurnDetails res = new TurnDetails(gameStatus, turn);
        swapTurn();
        liveBoard.setValue(board);
        moveStack.getValue().add(new TurnHistory(row, col));
        return res;
    }

    public void swapTurn() {
        Turn turn = liveTurn.getValue();
        turn = (turn == Turn.X) ? Turn.O : Turn.X;
        liveTurn.setValue(turn);
    }

    private boolean checkEnd(int row, int col) {
        return
                checkVertical(row, col) ||
                        checkHorizontal(row,col)
                || checkDiagonal(row, col);
    }

    private boolean checkDiagonal(int row, int col) {
        Turn[][] board = liveBoard.getValue();
        Turn cur = board[row][col];

        // check main diagonal
        if (row == col) {
            if (row - 2 >= 0
                    && cur == board[row - 1][col - 1]
                    && cur == board[row - 2][col - 2]) {
                return true;
            }
            if (row + 2 < board.length
                    && cur == board[row + 1][col + 1]
                    && cur == board[row + 2][col + 2]) {
                return true;
            }
            if (row > 0 && row < board.length - 1
                    && cur == board[row - 1][col - 1]
                    && cur == board[row + 1][col + 1]) {
                return true;
            }
        }

        // check anti-diagonal
        if (row + col == board.length - 1) {
            if (row - 2 >= 0
                    && cur == board[row - 1][col + 1]
                    && cur == board[row - 2][col + 2]) {
                return true;
            }
            if (row + 2 < board.length
                    && cur == board[row + 1][col - 1]
                    && cur == board[row + 2][col - 2]) {
                return true;
            }
            if (row > 0 && row < board.length - 1
                    && cur == board[row - 1][col + 1]
                    && cur == board[row + 1][col - 1]) {
                return true;
            }
        }

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


    public void decreaseAvailableMove() {
        int cur = availableMove.getValue();
        availableMove.setValue(cur - 1);
    }

    public void restartGame() {
        int size = liveBoard.getValue().length;
        liveBoard.setValue(new Turn[size][size]);
        availableMove.setValue(size * size);
        gameStatus.setValue(Status.ONGOING);
    }

    public TurnHistory undo() {
        TurnHistory turnHistory = moveStack.getValue().pop();
        liveBoard.getValue()[turnHistory.x][turnHistory.y] = null;
        swapTurn();
        availableMove.setValue(availableMove.getValue() + 1);
        return turnHistory;
    }
    public int calculateBoxNumber(int row, int col) {
        int size = liveBoard.getValue().length;
        return row * size + col + 1;
    }
}




enum Turn {
    X, O;
}

enum Status {
    Finished, ONGOING, DRAW;
}

enum BoardSize {
    ThreeXThree, FourXFour, FiveXFive;
}
