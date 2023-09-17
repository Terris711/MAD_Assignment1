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
    UserProfileData userProfileDataViewModel;
    FragmentManager fragmentManager = getSupportFragmentManager();
    ChooseSymbolFrag chooseSymbolFrag = new ChooseSymbolFrag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userProfileDataViewModel = new ViewModelProvider(this).get(UserProfileData.class);
        initialiseDataFromBundle();
        loadAvatarFragment();

        userProfileDataViewModel.getClicked().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {

                if(userProfileDataViewModel.getClickedValue().equals("avatar")){
                    loadAvatarFragment();
                }
                if(userProfileDataViewModel.getClickedValue().equals("selected")){
                    loadSelectedAvatarFrag();
                }
                if (userProfileDataViewModel.getClicked().getValue().equals("boardChoosing")) {
                    loadBoardChoosingFragment();
                }
                if (userProfileDataViewModel.getClicked().getValue().equals("chooseOX")) {
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
            userProfileDataViewModel.setTotalPlayer(1);
            userProfileDataViewModel.setPlayerCount(1);
            userProfileDataViewModel.getPlayer1().setName(player1Name);
        } else {
            userProfileDataViewModel.setTotalPlayer(2);
            userProfileDataViewModel.setPlayerCount(1);
            userProfileDataViewModel.getPlayer1().setName(player1Name);
            userProfileDataViewModel.getPlayer2().setName(player2Name);
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
            fragmentManager.beginTransaction().add(R.id.mainFrag,chooseSymbolFrag).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.mainFrag,chooseSymbolFrag).commit();
        }
    }
}