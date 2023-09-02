package com.example.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    UsernameFrag usernameFrag = new UsernameFrag();
    AvatarFrag avatarFrag = new AvatarFrag();
    SelectedAvatarFrag selectedAvatarFrag = new SelectedAvatarFrag();
    VersusWithWho versusWithWho = new VersusWithWho();
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadVersusWithWho();

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);
        mainActivityDataViewModel.getClicked().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                if(mainActivityDataViewModel.getClickedValue().equals("player")){
                    loadVersusWithWho();
                }
                if(mainActivityDataViewModel.getClickedValue().equals("username")){
                    loadUsernameFragment();
                }
                if(mainActivityDataViewModel.getClickedValue().equals("avatar")){
                    loadAvatarFragment();
                }
                if(mainActivityDataViewModel.getClickedValue().equals("selected")){
                    loadSelectedAvatarFrag();
                }

            }
        });
    }
    private void loadVersusWithWho(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if(frag==null){
            fragmentManager.beginTransaction().add(R.id.mainFrag,versusWithWho).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.mainFrag,versusWithWho).commit();
        }
    }
    private void loadUsernameFragment(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if(frag==null){
            fragmentManager.beginTransaction().add(R.id.mainFrag,usernameFrag).commit();
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.mainFrag,usernameFrag).commit();
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

    private void loadSelectedAvatarFrag(){
        Fragment frag = fragmentManager.findFragmentById(R.id.mainFrag);
        if (frag==null) {
            fragmentManager.beginTransaction().add(R.id.mainFrag,selectedAvatarFrag).commit();
        }
        else {
            fragmentManager.beginTransaction().replace(R.id.mainFrag,selectedAvatarFrag).commit();
        }
    }
}