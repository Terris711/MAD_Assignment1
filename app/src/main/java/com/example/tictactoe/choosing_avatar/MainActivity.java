package com.example.tictactoe.choosing_avatar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    AvatarFrag avatarFrag = new AvatarFrag();
    BoardChoosingFragment boardChoosingFragment = new BoardChoosingFragment();
    SelectedAvatarFrag selectedAvatarFrag = new SelectedAvatarFrag();
    MainActivityData mainActivityDataViewModel;
    FragmentManager fragmentManager = getSupportFragmentManager();
    ChooseOXFrag chooseOXFrag = new ChooseOXFrag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);
        initialiseDataFromBundle();
        loadAvatarFragment();

        mainActivityDataViewModel.getClicked().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                if(mainActivityDataViewModel.getClickedValue().equals("avatar")){
                    loadAvatarFragment();
                }
                if(mainActivityDataViewModel.getClickedValue().equals("selected")){
                    loadSelectedAvatarFrag();
                }
                if (mainActivityDataViewModel.getClicked().getValue().equals("boardChoosing")) {
                    loadBoardChoosingFragment();
                }
                if (mainActivityDataViewModel.getClicked().getValue().equals("chooseOX")) {
                    loadChooseOXFragment();
                }

            }
        });
    }

    private void initialiseDataFromBundle() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) return;
        boolean isAi = extras.getBoolean("isAI");
        String player1Name = extras.getString("player1Username");
        String player2Name = extras.getString("player2Username");
        if (isAi) {
            mainActivityDataViewModel.setTotalPlayer(1);
            mainActivityDataViewModel.setPlayerCount(1);
            mainActivityDataViewModel.getPlayer1().setName(player1Name);
        } else {
            mainActivityDataViewModel.setTotalPlayer(2);
            mainActivityDataViewModel.setPlayerCount(1);
            mainActivityDataViewModel.getPlayer1().setName(player1Name);
            mainActivityDataViewModel.getPlayer2().setName(player2Name);
        }

    }


    private void loadAvatarFragment(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if(frag==null){
            fragmentManager.beginTransaction().add(R.id.mainFrag,avatarFrag).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.mainFrag,avatarFrag).commit();
        }
    }
    private void loadBoardChoosingFragment(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if(frag==null){
            fragmentManager.beginTransaction().add(R.id.mainFrag,boardChoosingFragment).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.mainFrag,boardChoosingFragment).commit();
        }
    }

    private void loadSelectedAvatarFrag(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if (frag==null) {
            fragmentManager.beginTransaction().add(R.id.mainFrag,selectedAvatarFrag).commit();
        }
        else {
            fragmentManager.beginTransaction().replace(R.id.mainFrag,selectedAvatarFrag).commit();
        }
    }
    private void loadChooseOXFragment(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if(frag==null){
            fragmentManager.beginTransaction().add(R.id.mainFrag,chooseOXFrag).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.mainFrag,chooseOXFrag).commit();
        }
    }
}