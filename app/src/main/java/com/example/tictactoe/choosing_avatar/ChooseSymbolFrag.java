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

public class ChooseSymbolFrag extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_choose_symbol, container, false);
        UserProfileData userProfileDataViewModel = new ViewModelProvider(getActivity()).get(UserProfileData.class);
        Button symbolBtn = rootView.findViewById(R.id.symbolBtn);

        //Print player1 name in textview
        symbolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup symbolRadio = rootView.findViewById(R.id.symbolRadio);

                //set players' symbol
                if (symbolRadio.getCheckedRadioButtonId() == R.id.oxRadio) {
                    userProfileDataViewModel.setPlayersSymbol(userProfileDataViewModel.getPlayer1(), userProfileDataViewModel.getPlayer2(), R.drawable.zero_icon, R.drawable.cross_icon);
                } if (symbolRadio.getCheckedRadioButtonId() == R.id.dog_catRadio) {
                    userProfileDataViewModel.setPlayersSymbol(userProfileDataViewModel.getPlayer1(), userProfileDataViewModel.getPlayer2(), R.drawable.dog, R.drawable.cat);
                } if (symbolRadio.getCheckedRadioButtonId() == R.id.SM_BMradio) {
                    userProfileDataViewModel.setPlayersSymbol(userProfileDataViewModel.getPlayer1(), userProfileDataViewModel.getPlayer2(), R.drawable.spiderman, R.drawable.batman);
                }
                userProfileDataViewModel.setClickedValue("selected");

            }
        });

        return rootView;
    }
}