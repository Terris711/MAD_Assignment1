package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class InGameActivity extends AppCompatActivity {
    Fragment gridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        setupGridFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.grid_fragment, gridFragment).commit();

    }

    private void setupGridFragment() {
        Object boardSize = getIntent().getExtras().get(BoardChoosingAcitivity.BOARD_SIZE_KEY);
        if (boardSize instanceof BoardSize) {
            BoardSize curBoard = (BoardSize) boardSize;
            if (boardSize == BoardSize.ThreeXThree) {
                gridFragment = new BoardGameFragment3x3();
            } else if (boardSize == BoardSize.FourXFour) {
                gridFragment = new BoardGameFragment4x4();
            }else {
                gridFragment = new BoardGameFragment5x5();
            }
        }
    }

}