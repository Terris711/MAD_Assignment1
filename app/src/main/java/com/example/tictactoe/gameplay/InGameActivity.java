package com.example.tictactoe.gameplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoe.choosing_avatar.BoardSize;
import com.example.tictactoe.R;
import com.example.tictactoe.choosing_avatar.Player;
import com.example.tictactoe.choosing_avatar.SelectedAvatarFrag;
import com.example.tictactoe.starting_page.StartingActivity;


public class InGameActivity extends AppCompatActivity {
    BoardGameFragment gridFragment;
    GameStateViewModel liveData;
    private CountDownTimer currentTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        liveData = new ViewModelProvider(this).get(GameStateViewModel.class);

        setupGridFragment();
        setUpUserDetails();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.grid_fragment, gridFragment).commit();
        setGameFinishListener();
        setAvailableMoveListener();
        setUndoListener();
        setRestartGameListener();
        setGameStatsListener();
        setTimerListener();
        setTurnColorListener();
        setSettingButtonHandler();

    }

    private void setSettingButtonHandler() {
        ImageView imageView = findViewById(R.id.seting_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpUserDetails() {
        Player player1 = (Player) getIntent().getSerializableExtra(SelectedAvatarFrag.PLAYER_1);
        Player player2 = (Player) getIntent().getSerializableExtra(SelectedAvatarFrag.PLAYER_2);
        ImageView player2Image = findViewById(R.id.player_two_img);
        ImageView player1Image = findViewById(R.id.player_one_img);
        TextView player1Name = findViewById(R.id.player_one_name_txt);
        TextView player2Name = findViewById(R.id.player_two_name_txt);
        player2Image.setImageResource(player2.getAvatarImage());
        player2Name.setText(player2.getName());
        player1Image.setImageResource(player1.getAvatarImage());
        player1Name.setText(player1.getName());

        // decide to enable AI
        liveData.isAI.setValue(player2.getName().equals("AI"));
    }

    private void setTurnColorListener() {
        ImageView profileButton = findViewById(R.id.player_one_img);
        ImageView profileButton2 = findViewById(R.id.player_two_img); // Assuming you have a player_two_img for the second player

        liveData.liveTurn.observe(this, new Observer<Turn>() {
            @Override
            public void onChanged(Turn turn) {
                if (turn == Turn.O) {
                    profileButton.setAlpha(1f);  // Fully visible
                    profileButton2.setAlpha(0.5f);  // Half transparent
                } else {
                    profileButton.setAlpha(0.5f);  // Half transparent
                    profileButton2.setAlpha(1f);  // Fully visible
                }
            }
        });
    }


    private void setRestartGameListener() {
        ImageView restartButton = findViewById(R.id.reset_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liveData.restartGame();
            }
        });
    }

    private void setTimerListener() {
        liveData.liveTurn.observe(this, new Observer<Turn>() {
            @Override
            public void onChanged(Turn turn) {
                ViewGroup linearO = findViewById(R.id.timer_o);
                ViewGroup linearX = findViewById(R.id.timer_x);

                TextView tvO = findViewById(R.id.count_down_number_o);
                TextView tvX = findViewById(R.id.count_down_number_x);

                // Cancel the current timer if it's already running
                if (currentTimer != null) {
                    currentTimer.cancel();
                }

                if (turn == Turn.O) {
                    linearX.setVisibility(View.INVISIBLE);
                    linearO.setVisibility(View.VISIBLE);
                    currentTimer = new CountDownTimer(5000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            tvO.setText(Integer.toString((int) millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            liveData.swapTurn();
                        }
                    }.start();
                } else {
                    linearO.setVisibility(View.INVISIBLE);
                    linearX.setVisibility(View.VISIBLE);
                    currentTimer = new CountDownTimer(5000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            tvX.setText(Integer.toString((int) millisUntilFinished / 1000));
                        }

                        public void onFinish() {
                            liveData.swapTurn();
                        }
                    }.start();
                }
            }
        });
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
                TextView winpercentage1 = findViewById(R.id.p1_win_percent);
                TextView winpercentage2 = findViewById(R.id.p2_win_percent);

                winCount1.setText(Integer.toString(gameStats.winCount1));
                winCount2.setText(Integer.toString(gameStats.winCount2));
                drawCount.setText(Integer.toString(gameStats.drawCount));
                winpercentage1.setText(String.format("%.2f %%", gameStats.getWinPercentage1()));
                winpercentage2.setText(String.format("%.2f %%", gameStats.getWinPercentage2()));

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
                        Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
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
        BoardSize boardSize =  (BoardSize) getIntent().getExtras().get(SelectedAvatarFrag.BOARD_SIZE_KEY);
        if (boardSize == BoardSize.ThreeXThree) {
            liveData.liveBoard.setValue(new Turn[3][3]);
        } else if (boardSize == BoardSize.FourXFour) {
            liveData.liveBoard.setValue(new Turn[4][4]);
        } else {
            liveData.liveBoard.setValue(new Turn[5][5]);
        }
        liveData.gameStatus.setValue(Status.ONGOING);
        Turn turn = Turn.O; // fix later to assign turn to user choice
        liveData.winCond = getIntent().getExtras().getInt(SelectedAvatarFrag.WIN_CONDITION);
        liveData.liveTurn.setValue(turn);
        gridFragment = new BoardGameFragment();
    }

}