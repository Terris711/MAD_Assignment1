package com.example.tictactoe.starting_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;

public class UserCustomizationFragment extends Fragment {

    private EditText setPlayer1Username;
    private EditText setPlayer2Username;
    private Button btnStartGame;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_customization_fragment, container, false);

        setPlayer1Username = view.findViewById(R.id.setPlayer1Username);
        setPlayer2Username = view.findViewById(R.id.setPlayer2Username);
        btnStartGame = view.findViewById(R.id.btnStartGame);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Load previously stored usernames
        String player1Username = viewModel.getPlayer1Username();
        String player2Username = viewModel.getPlayer2Username();

        // Set the EditText fields to the loaded usernames
        setPlayer1Username.setText(player1Username);
        setPlayer2Username.setText(player2Username);

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
                mainMenuFragment.setArguments(bundle);

                // Replace the UserCustomizationFragment with MainMenuFragment
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, mainMenuFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
