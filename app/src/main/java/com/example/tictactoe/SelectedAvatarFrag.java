package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SelectedAvatarFrag extends Fragment {
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

        if (mainActivityData.getTotalPlayer()==1){
            player1profile();
            //AI profile
            TextView AIname = rootView.findViewById(R.id.player2name);
            ImageView AIavatar = rootView.findViewById(R.id.player2avatar);
            AIname.setText("AI");
            AIavatar.setImageResource(R.drawable.avatar1);
        }
        if (mainActivityData.getTotalPlayer()==2){
            player1profile();
            player2profile();
        }




        return rootView;
    }
    private void player1profile(){
        TextView playerName = rootView.findViewById(R.id.player1name);
        ImageView playerAvatar = rootView.findViewById(R.id.player1avatar);
        playerName.setText(mainActivityData.getPlayer1().getName());
        playerAvatar.setImageResource(mainActivityData.getPlayer1().getAvatarImage());
    }
    private void player2profile() {
        TextView playerName = rootView.findViewById(R.id.player2name);
        ImageView playerAvatar = rootView.findViewById(R.id.player2avatar);
        playerName.setText(mainActivityData.getPlayer2().getName());
        playerAvatar.setImageResource(mainActivityData.getPlayer2().getAvatarImage());
    }
}