package com.example.tictactoe.starting_page;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tictactoe.R;
import com.example.tictactoe.gameplay.GameStateViewModel;

public class MainMenuFragment extends Fragment {

    private TextView titleTextView;
    private Button playerVsPlayerButton;
    private Button playerVsAIButton;
    private ImageView backgroundImage;
    private SharedViewModel liveData;
    private int[] textColorsArray = {
            R.color.white, R.color.red, R.color.fuchsia, R.color.gold, R.color.green
    };
    private int currentTextColorIndex = 0;
    private ValueAnimator textAnimator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        liveData = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        // Find the TextView, Buttons, and ImageView in your fragment's layout
        titleTextView = view.findViewById(R.id.tvTitle);
        playerVsPlayerButton = view.findViewById(R.id.btnPlayerVsPlayer);
        playerVsAIButton = view.findViewById(R.id.btnPlayerVsAI);

        // Set dark background colors for the buttons
        playerVsPlayerButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal));
        playerVsAIButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal));
        // Set up a click listener for the Player Vs Player button
        playerVsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCustomizationFragment userCustomizationFragment = new UserCustomizationFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                liveData.isAI.setValue(false);
                transaction.replace(R.id.fragmentContainer, userCustomizationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Set up a click listener for the Player Vs AI button
        playerVsAIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCustomizationFragment userCustomizationFragment = new UserCustomizationFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                liveData.isAI.setValue(true);
                transaction.replace(R.id.fragmentContainer, userCustomizationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        // Start the color transition animation for the title text
//        animateTextColor(); animate text color causing bug that doesn't let user customisation to go back

        // Retrieve player names from arguments and set button text
        Bundle args = getArguments();
        if (args != null) {
            String player1Username = args.getString("player1Username", "");
            String player2Username = args.getString("player2Username", "");

            // Modify the button text to include HTML formatting for red "Vs"
            playerVsPlayerButton.setText(Html.fromHtml(player1Username + " <font color='red'>Vs</font> " + player2Username));
            playerVsAIButton.setText(Html.fromHtml(player1Username + " <font color='red'>Vs</font> AI"));
        }

        return view;
    }

    private void animateTextColor() {
        int startColorResource = textColorsArray[(currentTextColorIndex + 2)];
        int endColorResource = textColorsArray[(currentTextColorIndex + 1)];
        int endColorResource2 = textColorsArray[(currentTextColorIndex + 0)];
        int endColorResource3 = textColorsArray[(currentTextColorIndex + 3)];
        int endColorResource4 = textColorsArray[(currentTextColorIndex + 4)];

        int endColor3 = ContextCompat.getColor(requireContext(), endColorResource3);
        int startColor = ContextCompat.getColor(requireContext(), startColorResource);
        int endColor = ContextCompat.getColor(requireContext(), endColorResource);
        int endColor2 = ContextCompat.getColor(requireContext(), endColorResource2);
        int endColor4 = ContextCompat.getColor(requireContext(), endColorResource4);

        textAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor, endColor2, endColor3, endColor4);
        textAnimator.setDuration(2000); // 2 seconds duration
        textAnimator.setRepeatCount(ValueAnimator.INFINITE);
        textAnimator.setRepeatMode(ValueAnimator.REVERSE);
        textAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int animatedColor = (int) animator.getAnimatedValue();
                titleTextView.setTextColor(animatedColor);
            }
        });
        textAnimator.start();

        currentTextColorIndex = (currentTextColorIndex + 1) % textColorsArray.length;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (textAnimator != null && textAnimator.isRunning()) {
            textAnimator.cancel();
        }
    }
}
