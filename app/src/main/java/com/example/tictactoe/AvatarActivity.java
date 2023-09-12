package com.example.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class AvatarActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;

    String[] avatarNames = {"Avatar1", "Avatar2", "Avatar3", "Avatar4","Avatar5","Avatar6"};
    String[] avatarDescription = {"001","002", "003", "004", "005", "006"};

    int[] avatarImages = {R.drawable.avater1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avarta4, R.drawable.pic5, R.drawable.pic6};


    @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        recyclerView = findViewById(R.id.rvProgram);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        programAdapter = new ProgramAdapter(this, avatarNames, avatarDescription, avatarImages);
        recyclerView.setAdapter(programAdapter);
    }
}













