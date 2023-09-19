package com.example.tictactoe.starting_page;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.R;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        Bundle extra = getIntent().getExtras();
        String click = "newGame";

        if (extra != null){
            click = extra.getString("click");
        }

        // Replace the fragment container with MainMenuFragment when the app starts

        if (savedInstanceState == null) {
            if (click.equals("newGame")) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new MainMenuFragment())
                        .commit();
            }if (click.equals("customise")){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new UserCustomizationFragment())
                        .commit();
            }
        }
    }
}
