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

import java.util.Arrays;

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
    }

    private void setGameFinishListener() {
        liveData.isGameEnded.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Dialog dialog = new Dialog(InGameActivity.this);
                    dialog.setContentView(R.layout.game_finished_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setCanceledOnTouchOutside(false);

                    Button quitBtn = dialog.findViewById(R.id.offline_game_quit_btn);
                    Button continueBtn = dialog.findViewById(R.id.offline_game_continue_btn);

                    continueBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            liveData.isGameEnded.setValue(false);
                            dialog.hide();
                        }
                    });

                    quitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent boardChoosingIntent = new Intent(getApplicationContext(), BoardChoosingAcitivity.class);
                            startActivity(boardChoosingIntent);
                        }
                    });

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