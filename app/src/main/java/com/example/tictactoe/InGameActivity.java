package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public abstract class InGameActivity extends AppCompatActivity implements View.OnClickListener {


    BoardGameFragment3x3 grid3x3 = new BoardGameFragment3x3();
    BoardGameFragment4x4 grid4x4 = new BoardGameFragment4x4();
    private TextView playerOneName, playerTwoName;
    private TextView playerOneStep, playerTwoStep;

    // Initialize containers board
    private ImageView box1, box2, box3, box4, box5, box6, box7, box8, box9;
    private ImageView playerOneImg, playerTwoImg;

    private String playerOne;
    private String playerTwo;

    int playerOneStepCount, playerTwoStepCount


    // Set value or player X (1) and player O (1)
    int player_X = 1;
    int player_O = 0;

    int recordActivePlayer;
    int ActivePlayer;

    // Set win condition - When no player win the game isGameContinue is true and vice versa
    boolean isGameContinue = true;

    // Initialize all values in game board as:
    // -1: When no one choose that position yet
    //  1: for playerX
    //  0: for playerO
    int[] gameboard = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.grid_fragment, grid3x3).commit();

        setContentView(R.layout.activity_in_game);

        // Match box and gameboard grid
        box1 = (ImageView) findViewById(R.id.img_1);
        box2 = (ImageView) findViewById(R.id.img_2);
        box3 = (ImageView) findViewById(R.id.img_3);
        box4 = (ImageView) findViewById(R.id.img_4);
        box5 = (ImageView) findViewById(R.id.img_5);
        box6 = (ImageView) findViewById(R.id.img_6);
        box7 = (ImageView) findViewById(R.id.img_7);
        box8 = (ImageView) findViewById(R.id.img_8);
        box9 = (ImageView) findViewById(R.id.img_9);

        playerOneImg = (ImageView)  findViewById(R.id.player_one_img);
        playerTwoImg = (ImageView)  findViewById(R.id.player_two_img);

        playerOneName = (TextView) findViewById(R.id.player_one_name_txt);
        playerTwoName = (TextView) findViewById(R.id.player_two_name_txt);


        // User click on the box
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        box4.setOnClickListener(this);
        box5.setOnClickListener(this);
        box6.setOnClickListener(this);
        box7.setOnClickListener(this);
        box8.setOnClickListener(this);
        box9.setOnClickListener(this);
    }
}