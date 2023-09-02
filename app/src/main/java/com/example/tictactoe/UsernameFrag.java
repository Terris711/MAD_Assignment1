package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UsernameFrag extends Fragment {
    MainActivityData mainActivityDataViewModel;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_username, container, false);
        Button goToAvatarBtn = rootView.findViewById(R.id.goToAvatarBtn);
        EditText userName = rootView.findViewById(R.id.userName);

        goToAvatarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
                String ErrorUserName = userName.getText().toString();
                //set the username of player 1
                if (mainActivityDataViewModel.getPlayerCount()==1) {
                    if (ErrorUserName.equals("")) {
                        Toast.makeText(requireContext(), "Error: Please enter your name!", Toast.LENGTH_LONG).show();
                    } else {
                        mainActivityDataViewModel.getPlayer1().setName(userName.getText().toString());
                        mainActivityDataViewModel.setClickedValue("avatar");
                    }
                }
                //set the username of player 2
                if (mainActivityDataViewModel.getPlayerCount()==2){
                    if (ErrorUserName.equals("")) {
                        Toast.makeText(requireContext(), "Error: Please enter your name!", Toast.LENGTH_LONG).show();
                    } else {
                        mainActivityDataViewModel.getPlayer2().setName(userName.getText().toString());
                        mainActivityDataViewModel.setClickedValue("avatar");
                    }
                }
            }
        });

        return rootView;
    }
}