package com.example.tictactoe.choosing_avatar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;
import com.example.tictactoe.choosing_avatar.MainActivityData;

public class UsernameFrag extends Fragment {
    MainActivityData mainActivityDataViewModel;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_username, container, false);
        TextView whichPlayer = rootView.findViewById(R.id.whichPlayer);
        Button goToAvatarBtn = rootView.findViewById(R.id.goToAvatarBtn);
        EditText userName = rootView.findViewById(R.id.userName);
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        //To see which player's profile it is making
        if (mainActivityDataViewModel.getPlayerCount() == 1){
            if (mainActivityDataViewModel.getTotalPlayer()==1){
                whichPlayer.setText("Hi,");
            }else {
                whichPlayer.setText("Hi, Player 1");
            }
        }else {
            whichPlayer.setText("Hi, Player 2");
        }


        goToAvatarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set the username of player 1
                mainActivityDataViewModel.checkPlayerName(mainActivityDataViewModel.getPlayer1(), 1, requireContext(), userName);

                //set the username of player 2
                mainActivityDataViewModel.checkPlayerName(mainActivityDataViewModel.getPlayer2(), 2, requireContext(), userName);

            }
        });

        return rootView;
    }
}