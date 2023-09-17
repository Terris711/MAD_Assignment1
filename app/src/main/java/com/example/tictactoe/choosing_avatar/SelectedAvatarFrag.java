package com.example.tictactoe.choosing_avatar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;
import com.example.tictactoe.choosing_avatar.MainActivityData;
import com.example.tictactoe.gameplay.InGameActivity;

public class SelectedAvatarFrag extends Fragment {
    public static final String BOARD_SIZE_KEY = "BOARD_SIZE";
    public static final String PLAYER_1 = "player_1";
    public static final String PLAYER_2 = "player_2";
    public static final String WIN_CONDITION = "win_condition";

    View rootView;
    MainActivityData mainActivityDataViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_selected_avatar, container, false);
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        TextView boardSize = rootView.findViewById(R.id.board_size);
        if (mainActivityDataViewModel.getBoardSize() == BoardSize.ThreeXThree) {
            boardSize.setText("3 X 3");
        } else if (mainActivityDataViewModel.getBoardSize() == BoardSize.FourXFour) {
            boardSize.setText("4 X 4");
        } else {
            boardSize.setText("5 X 5");
        }
        if (mainActivityDataViewModel.getTotalPlayer()==1){
            player1profile();
            AIprofile();

        }
        if (mainActivityDataViewModel.getTotalPlayer()==2){
            player1profile();
            player2profile();
        }
        Button playButton = rootView.findViewById(R.id.playBtn);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), InGameActivity.class);
                Intent intent2 = new Intent(getActivity().getApplicationContext(), BoardChoosingFragment.class);
                intent.putExtra(BOARD_SIZE_KEY, mainActivityDataViewModel.getBoardSize());
                intent.putExtra(PLAYER_1, mainActivityDataViewModel.getPlayer1());
                intent.putExtra(PLAYER_2, mainActivityDataViewModel.getPlayer2());
                intent2.putExtra(PLAYER_1, mainActivityDataViewModel.getPlayer1());
                intent2.putExtra(PLAYER_2, mainActivityDataViewModel.getPlayer2());
                intent.putExtra(BOARD_SIZE_KEY, mainActivityDataViewModel.getBoardSize());
                intent.putExtra(PLAYER_1, mainActivityDataViewModel.getPlayer1());
                intent.putExtra(PLAYER_2, mainActivityDataViewModel.getPlayer2());
                intent.putExtra(WIN_CONDITION, mainActivityDataViewModel.getWinCond());
                startActivity(intent);
            }
        });

        return rootView;
    }
    private void player1profile(){
        TextView playerName = rootView.findViewById(R.id.player1name);
        ImageView playerAvatar = rootView.findViewById(R.id.player1avatar);
        ImageView p1symbol = rootView.findViewById(R.id.p1symbol);
        playerName.setText(mainActivityDataViewModel.getPlayer1().getName());
        playerAvatar.setImageResource(mainActivityDataViewModel.getPlayer1().getAvatarImage());
        p1symbol.setImageResource(mainActivityDataViewModel.getPlayer1().getSymbol());
    }
    private void player2profile() {
        TextView playerName = rootView.findViewById(R.id.player2name);
        ImageView playerAvatar = rootView.findViewById(R.id.player2avatar);
        ImageView p2symbol = rootView.findViewById(R.id.p2symbol);
        playerName.setText(mainActivityDataViewModel.getPlayer2().getName());
        playerAvatar.setImageResource(mainActivityDataViewModel.getPlayer2().getAvatarImage());
        p2symbol.setImageResource(mainActivityDataViewModel.getPlayer2().getSymbol());
    }

    private void AIprofile() {
        TextView playerName = rootView.findViewById(R.id.player2name);
        ImageView playerAvatar = rootView.findViewById(R.id.player2avatar);
        ImageView p2symbol = rootView.findViewById(R.id.p2symbol);
        mainActivityDataViewModel.getPlayer2().setName("AI");
        playerName.setText(mainActivityDataViewModel.getPlayer2().getName());
        mainActivityDataViewModel.getPlayer2().setAvatarImage(R.drawable.avatar1);
        playerAvatar.setImageResource(mainActivityDataViewModel.getPlayer2().getAvatarImage());
        p2symbol.setImageResource(mainActivityDataViewModel.getPlayer2().getSymbol());
    }

}