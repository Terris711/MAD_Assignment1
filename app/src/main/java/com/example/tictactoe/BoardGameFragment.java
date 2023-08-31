package com.example.tictactoe;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BoardGameFragment extends Fragment {


    public BoardGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    GameStateViewModel liveData;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        liveData = new ViewModelProvider(getActivity()).get(GameStateViewModel.class);
        int size = liveData.liveBoard.getValue().length;
        liveData.availableMove.setValue(size * size);
        if (size == 3) {
            root = inflater.inflate(R.layout.fragment_board_game3x3, container, false);
        } else if (size == 4) {
            root = inflater.inflate(R.layout.fragment_board_game_fragment4x4, container, false);
        } else {
            root = inflater.inflate(R.layout.fragment_board_game_fragment5x5, container, false);
        }

        for (int i = 1; i <= size * size; i++) {
            int curBox = i;
            String stringId = "img_" + curBox;
            int id = root.getResources().getIdentifier(stringId, "id", root.getContext().getPackageName());
            ImageView curImageView = root.findViewById(id);
            curImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (curImageView.getDrawable() != null) {
                        curImageView.setOnClickListener(null);
                        return;
                    }
                    // decrease total available move
                    liveData.decreaseAvailableMove();

                    TurnDetails turnDetails = liveData.play(curBox);


                    if (turnDetails.getTurn() == Turn.O) {
                        curImageView.setImageResource(R.drawable.zero_icon);
                    } else {
                        curImageView.setImageResource(R.drawable.cross_icon);
                    }
                    if (turnDetails.getGameStatus() == Status.Finished) {
                        liveData.isGameEnded.setValue(true);
                    }
                }
            });
        }



        setGameRestartListener();
        return root;
    }

    private void setGameRestartListener() {
        liveData.isGameEnded.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    int size = liveData.liveBoard.getValue().length;
                    liveData.liveBoard.setValue(new Turn[size][size]);
                    for (int i = 1; i <= size * size; i++) {
                        int curBox = i;
                        String stringId = "img_" + curBox;
                        int id = root.getResources().getIdentifier(stringId, "id", root.getContext().getPackageName());
                        ImageView curImageView = root.findViewById(id);
                        curImageView.setImageDrawable(null);
                    }
                }
            }
        });
    }


}