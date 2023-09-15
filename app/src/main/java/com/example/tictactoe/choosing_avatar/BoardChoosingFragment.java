package com.example.tictactoe.choosing_avatar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.tictactoe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardChoosingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardChoosingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BoardChoosingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoardChoosingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardChoosingFragment newInstance(String param1, String param2) {
        BoardChoosingFragment fragment = new BoardChoosingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_board_choosing, container, false);
        Button playButton = root.findViewById(R.id.play_button);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = root.findViewById(R.id.radioGroup);
                if (radioGroup.getCheckedRadioButtonId() == R.id.board_33) {
                    mainActivityDataViewModel.setBoardSize(BoardSize.ThreeXThree);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.board_44) {
                    mainActivityDataViewModel.setBoardSize(BoardSize.FourXFour);
                } else {
                    mainActivityDataViewModel.setBoardSize(BoardSize.FiveXFive);
                }
                RadioGroup radioGroupWinCond = root.findViewById(R.id.radioGroup1);
                if (radioGroupWinCond.getCheckedRadioButtonId() == R.id.win_by_3) {
                    mainActivityDataViewModel.setWinCond(3);
                } else if (radioGroupWinCond.getCheckedRadioButtonId() == R.id.win_by_4) {
                    mainActivityDataViewModel.setWinCond(4);
                } else {
                    mainActivityDataViewModel.setWinCond(5);
                }
                mainActivityDataViewModel.setClickedValue("selected");
            }
        });
        return root;
    }
}