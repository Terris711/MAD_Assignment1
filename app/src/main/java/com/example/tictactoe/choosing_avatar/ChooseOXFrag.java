package com.example.tictactoe.choosing_avatar;

import android.content.Intent;
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
import com.example.tictactoe.gameplay.InGameActivity;

public class ChooseOXFrag extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_choose_o_x, container, false);
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        Button ox_Btn = rootView.findViewById(R.id.ox_Btn);
        TextView ox_textView = rootView.findViewById(R.id.ox_textView);

        //Print player1 name in textview
        ox_textView.setText("Choose " +  mainActivityDataViewModel.getPlayer1().getName() + "'s symbol");

        ox_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = rootView.findViewById(R.id.ox_radioGroup);

                //set players' symbol
                if (radioGroup.getCheckedRadioButtonId() == R.id.o_radioBtn) {
                    mainActivityDataViewModel.setPlayersSymbol(mainActivityDataViewModel.getPlayer1(), mainActivityDataViewModel.getPlayer2(), R.drawable.zero_icon, R.drawable.cross_icon);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.x_radioBtn) {
                    mainActivityDataViewModel.setPlayersSymbol(mainActivityDataViewModel.getPlayer1(), mainActivityDataViewModel.getPlayer2(), R.drawable.cross_icon, R.drawable.zero_icon);
                }
                mainActivityDataViewModel.setClickedValue("selected");

            }
        });

        return rootView;
    }
}