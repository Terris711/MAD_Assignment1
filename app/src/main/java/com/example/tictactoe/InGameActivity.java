package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageView;

public class InGameActivity extends AppCompatActivity {
    BoardGameFragment3x3 grid3x3 = new BoardGameFragment3x3();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);


        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.grid_fragment, grid3x3).commit();

    }
}