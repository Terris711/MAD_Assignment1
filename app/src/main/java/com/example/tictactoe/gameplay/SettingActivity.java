package com.example.tictactoe.gameplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tictactoe.R;
import com.example.tictactoe.starting_page.StartingActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_menu);
        setNewGameHandler();
        setCustomiseHandler();
        setQuitHandler();
    }

    private void setQuitHandler() {
        Button exitButton = findViewById(R.id.button_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private void setCustomiseHandler(){
        Button customiseButton = findViewById(R.id.button_custom);
        customiseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
                intent.putExtra("click", "customise");

                startActivity(intent);
            }
        });
    }

    private void setNewGameHandler() {
        Button newGameButton = findViewById(R.id.button_new_game);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartingActivity.class);
                intent.putExtra("click", "newGame");
                startActivity(intent);
            }
        });
    }
}