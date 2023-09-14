package com.example.tictactoe.gameplay;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tictactoe.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardGameFragment#} factory method to
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
                        liveData.gameStatus.setValue(Status.Finished);
                    } else if (turnDetails.getGameStatus() == Status.DRAW) {
                        liveData.gameStatus.setValue(Status.DRAW);
                    }
                }
            });
        }
        aiPlay();
        setGameRestartListener();
        return root;
    }
    public void undoMove() {
        TurnHistory turnHistory = liveData.undo();
        int boxNumber= liveData.calculateBoxNumber(turnHistory.x, turnHistory.y);
        resetImageView(boxNumber);
    }

    private void setGameRestartListener() {
        liveData.gameStatus.observe(getActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status == Status.ONGOING) {
                    int size = liveData.liveBoard.getValue().length;
                    liveData.liveBoard.setValue(new Turn[size][size]);
                    for (int i = 1; i <= size * size; i++) {
                        resetImageView(i);
                    }
                }
            }
        });
    }

    private void resetImageView(int boxNumber) {
        String stringId = "img_" + boxNumber;
        int id = root.getResources().getIdentifier(stringId, "id", root.getContext().getPackageName());
        ImageView curImageView = root.findViewById(id);
        curImageView.setImageDrawable(null);
    }
    private void aiPlay() {
        if (liveData.isAI.getValue()) {
            liveData.liveTurn.observe(getActivity(), new Observer<Turn>() {
                @Override
                public void onChanged(Turn turn) {
                    if (turn == Turn.X && liveData.gameStatus.getValue() == Status.ONGOING) {
                        int size = liveData.liveBoard.getValue().length;
                        int am = liveData.availableMove.getValue();
                        if (am <= 0) return;
                        int availableMove = new Random().nextInt(am) + 1;
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                if (liveData.liveBoard.getValue()[i][j] == null) {
                                    availableMove--;
                                    if (availableMove == 0) {
                                        int boxNumber = liveData.calculateBoxNumber(i, j);
                                        String stringId = "img_" + boxNumber;
                                        int id = root.getResources().getIdentifier(stringId, "id", root.getContext().getPackageName());
                                        ImageView curImageView = root.findViewById(id);
                                        curImageView.performClick();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}