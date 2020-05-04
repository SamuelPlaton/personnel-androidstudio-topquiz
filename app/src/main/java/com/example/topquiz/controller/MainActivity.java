package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    // Our User
    private User user;

    // Our 3 main visible elements
    private TextView welcomeText;
    private EditText usernameInput;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References of our elements
        welcomeText = (TextView) findViewById(R.id.activity_main_welcome_text);
        usernameInput = (EditText) findViewById(R.id.activity_main_username_input);
        loginButton = (Button) findViewById(R.id.activity_main_start_btn);

        // Disable start btn at the beginning
        loginButton.setEnabled(false);
        loginButton.setBackgroundColor(Color.GRAY);

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
                user.setFirstName(usernameInput.getText().toString());
                // Launch the game activity
                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivity);
            }
        });
    }
}
