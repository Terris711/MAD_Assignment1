package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BoardChoosingAcitivity extends AppCompatActivity {
    static final String BOARD_SIZE_KEY = "BOARD_SIZE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_choosing_acitivity);
        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                Intent intent = new Intent(getApplicationContext(), InGameActivity.class);

                if (radioGroup.getCheckedRadioButtonId() == R.id.board_33) {
                    intent.putExtra(BOARD_SIZE_KEY, BoardSize.ThreeXThree);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.board_44) {
                    intent.putExtra(BOARD_SIZE_KEY, BoardSize.FourXFour);
                } else {
                    intent.putExtra(BOARD_SIZE_KEY, BoardSize.FiveXFive);
                }
                startActivity(intent);
            }
        });

    }

}