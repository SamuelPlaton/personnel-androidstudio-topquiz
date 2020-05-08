package com.example.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    // Our request code for the game activity
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    // Our preferences
    private SharedPreferences preferences;

    // Our User
    private User user = new User();

    // Our 3 main visible elements
    private TextView welcomeText;
    private EditText usernameInput;
    private Button loginButton;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){ // We check if the request code and the result code are the one expected
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0); // We get the score back
            user.setScore(score); // We set the score of the user

            // Store the score of the user
            preferences.edit().putInt("score", score).apply();

            // We check if the user is set to display the new message
            greetUser();

        }
    }

    // Check if the user is set, if it is, say a welcome back message
    private void greetUser(){
        // We check if the user is already settled
        String firstname = preferences.getString("firstname", null);
        if (firstname != null){
            int lastScore = preferences.getInt("score", 0); // If yes, we get back his score
            String welcomeSentence = "Rebonjour "+firstname+", la dernière fois ton score était de "+lastScore+" sur 9";
            welcomeText.setText(welcomeSentence); // Modify the welcome sentence
            usernameInput.setText(firstname); // Fullfill the input
            usernameInput.setSelection(firstname.length()); // And set the cursor to the end of the username
            loginButton.setEnabled(true); // Activate the button
            loginButton.setBackgroundColor(Color.parseColor("#FCBF1E"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Our data stocked
        preferences = getPreferences(MODE_PRIVATE);

        // References of our elements
        welcomeText = (TextView) findViewById(R.id.activity_main_welcome_text);
        usernameInput = (EditText) findViewById(R.id.activity_main_username_input);
        loginButton = (Button) findViewById(R.id.activity_main_start_btn);

        // Disable start btn at the beginning
        loginButton.setEnabled(false);
        loginButton.setBackgroundColor(Color.GRAY);

        // We check if the user is set
        greetUser();

        // Listen to the input
        usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int textSize = s.toString().length(); // Size of the username
                if (textSize == 0){ // If no username, disable button
                    loginButton.setEnabled(true);
                    loginButton.setBackgroundColor(Color.GRAY);
                }else { // else activate it
                    loginButton.setEnabled(true);
                    loginButton.setBackgroundColor(Color.parseColor("#FCBF1E"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Listen to the button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // When the user click on it
            public void onClick(View v) {
                // Set the user name
                String firstName = usernameInput.getText().toString();
                user.setFirstName(firstName);

                // Store the first name of the user
                preferences.edit().putString("firstname", user.getFirstName()).apply();

                // Launch the game activity
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

}
