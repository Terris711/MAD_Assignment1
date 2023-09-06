package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class InGameActivity extends AppCompatActivity {
    BoardGameFragment gridFragment;
    GameStateViewModel liveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        liveData = new ViewModelProvider(this).get(GameStateViewModel.class);

        setupGridFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.grid_fragment, gridFragment).commit();
        setGameFinishListener();
        setAvailableMoveListener();
        setUndoListener();
        setGameStatsListener();
    }



    private void setUndoListener() {
        ImageView undoButton = findViewById(R.id.undo_button);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!liveData.moveStack.getValue().isEmpty()) {
                    gridFragment.undoMove();

                }
            }
        });
    }

    private void setAvailableMoveListener() {
        liveData.availableMove.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView availableMoveTV = findViewById(R.id.available_move);
                availableMoveTV.setText(Integer.toString(integer));
                int boardSize = liveData.liveBoard.getValue().length;
                int moveDone = boardSize * boardSize - liveData.availableMove.getValue();
                TextView tv = findViewById(R.id.player_one_count);
                TextView tv2 = findViewById(R.id.player_two_count);
                int p1Count = moveDone / 2;
                int p2Count = moveDone / 2;
                if (liveData.liveTurn.getValue() == Turn.O) {
                    p1Count += moveDone % 2;
                } else {
                    p2Count += moveDone % 2;
                }
                tv.setText(Integer.toString(p1Count));
                tv2.setText(Integer.toString(p2Count));

            }
        });
    }
    private void setGameStatsListener() {
        liveData.gameStats.observe(this, new Observer<GameStats>() {
            @Override
            public void onChanged(GameStats gameStats) {
                TextView winCount1 = findViewById(R.id.p1_win_count);
                TextView winCount2 = findViewById(R.id.p2_win_count);
                TextView drawCount = findViewById(R.id.draw_count);
                winCount1.setText(Integer.toString(gameStats.winCount1));
                winCount2.setText(Integer.toString(gameStats.winCount2));
                drawCount.setText(Integer.toString(gameStats.drawCount));
            }
        });
    }

    private void setGameFinishListener() {
        liveData.gameStatus.observe(this, new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Dialog dialog = new Dialog(InGameActivity.this);

                if (status == Status.DRAW) {
                    dialog.setContentView(R.layout.match_draw);
                    liveData.gameStats.getValue().drawCount++;
                } else if (status == Status.Finished) {
                    dialog.setContentView(R.layout.human_win);
                    if (liveData.liveTurn.getValue() == Turn.O) {
                        liveData.gameStats.getValue().winCount2++;
                    } else {
                        liveData.gameStats.getValue().winCount1++;
                    }
                } else {
                    return; // don't show dialog when the game status is ONGOING
                }
                liveData.gameStats.setValue(liveData.gameStats.getValue());

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Button quitButton = dialog.findViewById(R.id.quit_button);
                Button continueButton = dialog.findViewById(R.id.continue_button);
                continueButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        liveData.restartGame();
                        resetMoveCount();
                        dialog.cancel();
                    }
                });

                quitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), BoardChoosingAcitivity.class);
                        startActivity(intent);

                    }
                });



            }
        });
    }

    private void resetMoveCount() {
        TextView p1Count = findViewById(R.id.player_one_count);
        TextView p2Count = findViewById(R.id.player_two_count);
        p1Count.setText("0");
        p2Count.setText("0");

    }
    private void setupGridFragment() {
        BoardSize boardSize =  (BoardSize) getIntent().getExtras().get(BoardChoosingAcitivity.BOARD_SIZE_KEY);
        if (boardSize == BoardSize.ThreeXThree) {
            liveData.liveBoard.setValue(new Turn[3][3]);
        } else if (boardSize == BoardSize.FourXFour) {
            liveData.liveBoard.setValue(new Turn[4][4]);
        } else {
            liveData.liveBoard.setValue(new Turn[5][5]);
        }
        Turn turn = Turn.O; // fix later to assign turn to user choice
        liveData.liveTurn.setValue(turn);
        gridFragment = new BoardGameFragment();
    }




}