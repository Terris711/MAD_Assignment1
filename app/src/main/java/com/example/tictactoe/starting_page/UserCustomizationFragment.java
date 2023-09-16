package com.example.tictactoe.starting_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;
import com.example.tictactoe.choosing_avatar.MainActivity;

public class UserCustomizationFragment extends Fragment {

    private EditText setPlayer1Username;
    private EditText setPlayer2Username;
    private Button btnStartGame;
    private SharedViewModel viewModel;
    private SharedViewModel liveData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_customization_fragment, container, false);
        liveData = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        setPlayer1Username = view.findViewById(R.id.setPlayer1Username);
        setPlayer2Username = view.findViewById(R.id.setPlayer2Username);
        btnStartGame = view.findViewById(R.id.btnStartGame);

        btnStartGame.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_pink));
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Load previously stored usernames
        String player1Username = viewModel.getPlayer1Username();
        String player2Username = viewModel.getPlayer2Username();

        // Set the EditText fields to the loaded usernames
        setPlayer1Username.setText(player1Username);
        setPlayer2Username.setText(player2Username);

        if (liveData.isAI.getValue()) {
            setPlayer2Username.setVisibility(View.GONE);
        }
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1Username = setPlayer1Username.getText().toString();
                String player2Username = setPlayer2Username.getText().toString();

                // Store the entered usernames in ViewModel
                viewModel.setPlayer1Username(player1Username);
                viewModel.setPlayer2Username(player2Username);

                // Navigate back to the MainMenuFragment
                MainMenuFragment mainMenuFragment = new MainMenuFragment();

                // Pass player names to MainMenuFragment
                Bundle bundle = new Bundle();
                bundle.putString("player1Username", player1Username);
                bundle.putString("player2Username", player2Username);
                bundle.putBoolean("isAI", liveData.isAI.getValue());
                mainMenuFragment.setArguments(bundle);

                // Go to avatar choosing activity
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }
}
