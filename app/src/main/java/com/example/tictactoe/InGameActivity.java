package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;



public class InGameActivity extends AppCompatActivity {
    Fragment gridFragment;
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
    }

    private void setAvailableMoveListener() {
        liveData.availableMove.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView availableMoveTV = findViewById(R.id.available_move);
                availableMoveTV.setText(Integer.toString(integer));

                int boardSize = liveData.liveBoard.getValue().length;
                if (integer == boardSize * boardSize) return;
                if (liveData.liveTurn.getValue() == Turn.O) {
                    TextView tv = findViewById(R.id.player_one_count);
                    tv.setText(Integer.toString(Integer.parseInt(tv.getText().toString()) + 1));
                } else {
                    TextView tv = findViewById(R.id.player_two_count);
                    tv.setText(Integer.toString(Integer.parseInt(tv.getText().toString()) + 1));
                }
            }
        });
    }

    private void setGameFinishListener() {
        liveData.isGameEnded.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Dialog dialog = new Dialog(InGameActivity.this);
                    dialog.setContentView(R.layout.human_win);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            }
        });
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