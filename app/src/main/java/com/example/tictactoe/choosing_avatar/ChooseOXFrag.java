package com.example.tictactoe.choosing_avatar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoe.R;

public class ChooseOXFrag extends Fragment {

    MainActivityData mainActivityDataViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_choose_o_x, container, false);
        mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        Button ox_Btn = rootView.findViewById(R.id.ox_Btn);
        TextView ox_textView = rootView.findViewById(R.id.ox_textView);

        //Print the username in the textView to indicate which player's symbol is being selected.
        ox_textView.setText("Choose " +  mainActivityDataViewModel.getPlayer1().getName() + "'s symbol");

        ox_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set player 1 symbol (player vs AI)
                RadioGroup radioGroup = rootView.findViewById(R.id.ox_radioGroup);

                if(mainActivityDataViewModel.getTotalPlayer() == 1) {
                    ox_textView.setText("Choose " +  mainActivityDataViewModel.getPlayer1().getName() + "'s symbol");
                    if (radioGroup.getCheckedRadioButtonId() == R.id.o_radioBtn) {
                       mainActivityDataViewModel.playerVSAIsymbol(mainActivityDataViewModel.getPlayer1(),requireContext(), R.drawable.zero_icon, R.drawable.cross_icon);
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.x_radioBtn) {
                        mainActivityDataViewModel.playerVSAIsymbol(mainActivityDataViewModel.getPlayer1(),requireContext(), R.drawable.cross_icon, R.drawable.zero_icon);
                    }
                }

                //set player 1 symbol ( player1 vs player2)
                else if (mainActivityDataViewModel.getTotalPlayer() == 2){
                    if (radioGroup.getCheckedRadioButtonId() == R.id.o_radioBtn) {
                        mainActivityDataViewModel.playerVSplayerSymbol(mainActivityDataViewModel.getPlayer1(), mainActivityDataViewModel.getPlayer2(), requireContext(), R.drawable.zero_icon, R.drawable.cross_icon);
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.x_radioBtn) {
                        mainActivityDataViewModel.playerVSplayerSymbol(mainActivityDataViewModel.getPlayer1(), mainActivityDataViewModel.getPlayer2(), requireContext(),  R.drawable.cross_icon, R.drawable.zero_icon);

                    }
                }
                mainActivityDataViewModel.setClickedValue("selected");
            }
        });

        return rootView;
    }
}