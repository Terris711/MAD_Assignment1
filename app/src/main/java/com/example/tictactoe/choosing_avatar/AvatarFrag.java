package com.example.tictactoe.choosing_avatar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.List;

public class AvatarFrag extends Fragment implements SelectListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    List<Avatar> avatarList;
    RecyclerView.LayoutManager layoutManager;
    MainActivityData mainActivityDataViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        avatarList = new ArrayList<Avatar>();
        avatarList.add(new Avatar("Avatar1", R.drawable.avatar1));
        avatarList.add(new Avatar("Avatar2", R.drawable.avatar2));
        avatarList.add(new Avatar("Avatar3", R.drawable.avatar3));
        avatarList.add(new Avatar("Avatar4", R.drawable.avatar4));
        avatarList.add(new Avatar("Avatar5", R.drawable.avatar5));
        avatarList.add(new Avatar("Avatar6", R.drawable.avatar6));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_avatar, container, false);
        recyclerView = rootView.findViewById(R.id.rvProgram);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        programAdapter = new ProgramAdapter(requireContext(), avatarList, this);
        recyclerView.setAdapter(programAdapter);
        TextView chooseAvatarText = rootView.findViewById(R.id.chooseAvatarText);
        TextView avatarError = rootView.findViewById(R.id.avatarError);
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        Button goToNextBtn = rootView.findViewById(R.id.goToNextBtn);

        //Print the username in the textView to indicate which player's avatar is being selected.
        chooseAvatarText.setText("Choose " +  mainActivityDataViewModel.getPlayer1().getName() + "'s Avatar");
        goToNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user vs AI
                if (mainActivityDataViewModel.getTotalPlayer() == 1) {
                    chooseAvatarText.setText("Choose " +  mainActivityDataViewModel.getPlayer1().getName() + "'s Avatar");
                    //set player 1 avatar and error handling
                    mainActivityDataViewModel.checkPlayerAvatar(mainActivityDataViewModel.getPlayer1(), "boardChoosing", 1, avatarError);
                }

                //player1 vs player2
                if (mainActivityDataViewModel.getTotalPlayer() == 2) {
                    //set player 1 avatar and error handling
                    if (mainActivityDataViewModel.getPlayerCount() == 1) {
                        mainActivityDataViewModel.checkPlayerAvatar(mainActivityDataViewModel.getPlayer1(), "avatar", 2, avatarError);
                    }
                    //set player 2 avatar and error handling
                    if (mainActivityDataViewModel.getPlayerCount() == 2) {
                        chooseAvatarText.setText("Choose " + mainActivityDataViewModel.getPlayer2().getName() + "'s Avatar");
                        mainActivityDataViewModel.checkPlayerAvatar(mainActivityDataViewModel.getPlayer2(),"boardChoosing", 1, avatarError);
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onItemClicked(Avatar avatar) {
        Toast.makeText(requireContext(), avatar.getName(), Toast.LENGTH_SHORT).show();
        //set an avatar of player 1
        if (mainActivityDataViewModel.getPlayerCount()==1) {
            mainActivityDataViewModel.getPlayer1().setAvatarImage(avatar.getImage());
        }
        //set an avatar of player 1
        else {mainActivityDataViewModel.getPlayer2().setAvatarImage(avatar.getImage());}
    }
}