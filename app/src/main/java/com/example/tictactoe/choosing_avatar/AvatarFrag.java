package com.example.tictactoe.choosing_avatar;

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
    MainActivityData mainActivityDataViewModel;

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
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        Button goToNextBtn = rootView.findViewById(R.id.goToNextBtn);

        //Print the username in the textView to indicate which player's avatar is being selected.
        chooseAvatarText.setText("Choose " +  mainActivityDataViewModel.getModifyingPlayer().getName() + "'s Avatar");
        goToNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user vs AI
                if (mainActivityDataViewModel.getTotalPlayer() == 1) {
                    //set player 1 avatar and error handling
                    mainActivityDataViewModel.checkPlayerAvatar(mainActivityDataViewModel.getPlayer1(), "boardChoosing", 1, avatarError);
                }

                //player1 vs player2
                if (mainActivityDataViewModel.getTotalPlayer() == 2) {
                    if (mainActivityDataViewModel.getModifyingPlayer().noAvatarImage()) {
                        avatarError.setText("Please choose " + mainActivityDataViewModel.getModifyingPlayer().getName()+ "'s avatar");
                        return;
                    }

                    avatarError.setText("");
                    if (mainActivityDataViewModel.getPlayerCount() == 2) {
                        mainActivityDataViewModel.setClickedValue("boardChoosing");
                    }

                    if (mainActivityDataViewModel.getPlayerCount() == 1) {
                        mainActivityDataViewModel.setClickedValue("avatar");
                        mainActivityDataViewModel.setModifyingPlayer(mainActivityDataViewModel.getPlayer2());
                        mainActivityDataViewModel.setPlayerCount(2);
                    }
                }
                chooseAvatarText.setText("Choose " +  mainActivityDataViewModel.getModifyingPlayer().getName() + "'s Avatar");

            }
        });

        return rootView;
    }

    @Override
    public void onItemClicked(Avatar avatar) {
        mainActivityDataViewModel.getModifyingPlayer().setAvatarImage(avatar.getImage());
    }
}