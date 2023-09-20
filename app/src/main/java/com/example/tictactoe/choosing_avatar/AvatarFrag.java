package com.example.tictactoe.choosing_avatar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.R;

import java.util.ArrayList;
import java.util.List;

public class AvatarFrag extends Fragment implements SelectListener {

    RecyclerView recyclerView;
    ProgramAdapter programAdapter;
    List<Avatar> avatarList;
    RecyclerView.LayoutManager layoutManager;
    UserProfileData userProfileDataViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        avatarList = new ArrayList<>();
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
        programAdapter.setSelectedPos(RecyclerView.NO_POSITION);
        recyclerView.setAdapter(programAdapter);
        TextView chooseAvatarText = rootView.findViewById(R.id.chooseAvatarText);
        TextView avatarError = rootView.findViewById(R.id.avatarError);
        userProfileDataViewModel = new ViewModelProvider(getActivity()).get(UserProfileData.class);
        Button goToNextBtn = rootView.findViewById(R.id.goToNextBtn);

        //Print player 1 name in textview
        chooseAvatarText.setText("Choose " +  userProfileDataViewModel.getModifyingPlayer().getName() + "'s Avatar");

        goToNextBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                //user vs AI
                if (userProfileDataViewModel.getTotalPlayer() == 1) {
                    //set player 1 avatar and error handling
                    userProfileDataViewModel.checkPlayerAvatar(userProfileDataViewModel.getPlayer1(), "boardChoosing", avatarError);
                }

                //player1 vs player2
                if (userProfileDataViewModel.getTotalPlayer() == 2) {
                    if (userProfileDataViewModel.getModifyingPlayer().noAvatarImage()) {
                        //error message
                        avatarError.setText("Please choose " + userProfileDataViewModel.getModifyingPlayer().getName()+ "'s avatar");
                        return;
                    }
                    //reset error message
                    avatarError.setText("");
                    //player2
                    if (userProfileDataViewModel.getPlayerCount() == 2) {
                        userProfileDataViewModel.setClickedValue("boardChoosing");
                        userProfileDataViewModel.setPlayerCount(1);
                    }
                    //player 1
                    if (userProfileDataViewModel.getPlayerCount() == 1) {
                        userProfileDataViewModel.setClickedValue("avatar");
                        userProfileDataViewModel.setModifyingPlayer(userProfileDataViewModel.getPlayer2());
                        userProfileDataViewModel.setPlayerCount(2);
                    }
                }
                //print player 2 name in textview
                chooseAvatarText.setText("Choose " +  userProfileDataViewModel.getModifyingPlayer().getName() + "'s Avatar");

            }
        });

        return rootView;
    }

    @Override
    public void onItemClicked(Avatar avatar) {
        userProfileDataViewModel.getModifyingPlayer().setAvatarImage(avatar.getImage());
    }
}