package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class VersusWithWho extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_versus_with_who, container, false);
        Button onePlayer = rootView.findViewById(R.id.one1Player);
        Button twoPlayers = rootView.findViewById(R.id.two2Players);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        onePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setTotalPlayer(1);
                mainActivityDataViewModel.setPlayerCount(1);
                mainActivityDataViewModel.setClickedValue("username");
            }
        });

        twoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setTotalPlayer(2);
                mainActivityDataViewModel.setPlayerCount(1);
                mainActivityDataViewModel.setClickedValue("username");
            }
        });

        return rootView;
    }
}