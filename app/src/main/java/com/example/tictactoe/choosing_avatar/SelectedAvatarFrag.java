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
    MainActivityData mainActivityData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_selected_avatar, container, false);
        mainActivityData = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        TextView boardSize = rootView.findViewById(R.id.board_size);
        if (mainActivityData.getBoardSize() == BoardSize.ThreeXThree) {
            boardSize.setText("3 X 3");
        } else if (mainActivityData.getBoardSize() == BoardSize.FourXFour) {
            boardSize.setText("4 X 4");
        } else {
            boardSize.setText("5 X 5");
        }
        if (mainActivityData.getTotalPlayer()==1){
            player1profile();
            //AI profile
            ImageView p2symbol = rootView.findViewById(R.id.p2symbol);
            mainActivityData.getPlayer2().setName("AI");
            p2symbol.setImageResource(mainActivityData.getAIsymbol());
            mainActivityData.getPlayer2().setAvatarImage(R.drawable.avatar1);
            player2profile();

        }
        if (mainActivityData.getTotalPlayer()==2){
            player1profile();
            player2profile();
        }
        Button playButton = rootView.findViewById(R.id.playBtn);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), InGameActivity.class);
                intent.putExtra(BOARD_SIZE_KEY, mainActivityData.getBoardSize());
                intent.putExtra(PLAYER_1, mainActivityData.getPlayer1());
                intent.putExtra(PLAYER_2, mainActivityData.getPlayer2());
                intent.putExtra(WIN_CONDITION, mainActivityData.getWinCond());
                startActivity(intent);
            }
        });

        return rootView;
    }
    private void player1profile(){
        TextView playerName = rootView.findViewById(R.id.player1name);
        ImageView playerAvatar = rootView.findViewById(R.id.player1avatar);
        ImageView p1symbol = rootView.findViewById(R.id.p1symbol);
        playerName.setText(mainActivityData.getPlayer1().getName());
        playerAvatar.setImageResource(mainActivityData.getPlayer1().getAvatarImage());
        p1symbol.setImageResource(mainActivityData.getPlayer1().getSymbol());
    }
    private void player2profile() {
        TextView playerName = rootView.findViewById(R.id.player2name);
        ImageView playerAvatar = rootView.findViewById(R.id.player2avatar);
        ImageView p2symbol = rootView.findViewById(R.id.p2symbol);
        playerName.setText(mainActivityData.getPlayer2().getName());
        playerAvatar.setImageResource(mainActivityData.getPlayer2().getAvatarImage());
        p2symbol.setImageResource(mainActivityData.getPlayer2().getSymbol());
    }
}