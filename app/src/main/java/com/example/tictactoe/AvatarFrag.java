package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AvatarFrag extends Fragment implements SelectListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    List<Avatar> avatarList;
    RecyclerView.LayoutManager layoutManager;
    MainActivityData mainActivityDataViewModel;
    Player player1;
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

        Button goToNextBtn = rootView.findViewById(R.id.goToNextBtn);
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        goToNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user vs AI
                if (mainActivityDataViewModel.getTotalPlayer()==1) {
                    //check if user sets an avatar and go to the next page
                    if (mainActivityDataViewModel.getPlayer1().getAvatarImage() == 0) {
                        Toast.makeText(requireContext(), "Error: Please choose your avatar!", Toast.LENGTH_LONG).show();
                    } else { mainActivityDataViewModel.setClickedValue("selected");}
                }

                //player1 vs player2
                if (mainActivityDataViewModel.getTotalPlayer() == 2) {
                    //check if user sets an avatar of player 1 and go to the username page to set an avatar of player 2
                    if (mainActivityDataViewModel.getPlayerCount()==1) {
                        if (mainActivityDataViewModel.getPlayer1().getAvatarImage() == 0) {
                            Toast.makeText(requireContext(), "Error: Please choose your avatar!", Toast.LENGTH_LONG).show();
                        } else {
                            mainActivityDataViewModel.setClickedValue("username");
                            mainActivityDataViewModel.setPlayerCount(2);
                        }
                    }
                    //check if user sets an avatar of player 2 and go to the next page
                    if (mainActivityDataViewModel.getPlayerCount()==2){
                        if (mainActivityDataViewModel.getPlayer2().getAvatarImage() == 0) {
                            Toast.makeText(requireContext(), "Error: Please choose your avatar!", Toast.LENGTH_LONG).show();
                        } else {mainActivityDataViewModel.setClickedValue("selected");}
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