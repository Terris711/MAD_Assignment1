package com.example.tictactoe.choosing_avatar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

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
        UserProfileData userProfileDataViewModel = new ViewModelProvider(getActivity()).get(UserProfileData.class);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = root.findViewById(R.id.radioGroup);
                RadioGroup radioGroupWinCond = root.findViewById(R.id.radioGroup1);
                int checkedWinConditionId = radioGroupWinCond.getCheckedRadioButtonId();


                if (radioGroup.getCheckedRadioButtonId() == R.id.board_33) {
                    userProfileDataViewModel.setBoardSize(BoardSize.ThreeXThree);
                    if (checkedWinConditionId != R.id.win_by_3){
                        Toast.makeText(getActivity(), "You can only win by 3 for 3x3 board size",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else if (radioGroup.getCheckedRadioButtonId() == R.id.board_44) {
                    userProfileDataViewModel.setBoardSize(BoardSize.FourXFour);
                    if (checkedWinConditionId != R.id.win_by_3 && checkedWinConditionId != R.id.win_by_4){
                        Toast.makeText(getActivity(), "You can only win by 3 or 4 for 4x4 board size",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else {
                    userProfileDataViewModel.setBoardSize(BoardSize.FiveXFive);
                }


                if (radioGroupWinCond.getCheckedRadioButtonId() == R.id.win_by_3) {
                    userProfileDataViewModel.setWinCond(3);
                } else if (radioGroupWinCond.getCheckedRadioButtonId() == R.id.win_by_4) {
                    userProfileDataViewModel.setWinCond(4);
                } else {
                    userProfileDataViewModel.setWinCond(5);
                }
                userProfileDataViewModel.setClickedValue("chooseOX");
            }
        });
        return root;
    }
}