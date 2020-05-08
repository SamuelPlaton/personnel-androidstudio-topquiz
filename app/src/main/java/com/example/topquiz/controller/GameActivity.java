package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    // Our questions
    private QuestionBank questionBank;
    private Question currentQuestion;

    // Decide if the game is set on pause or on a question
    private boolean onQuestion = true;
    private int numberOfQuestionsLeft; // Number of questions that will be decremented
    private int numberofQuestionsTotal; // Total number of questions
    private int score; // Score of the user

    // Our main visual elements
    private TextView question;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // References of our elements
        question = (TextView) findViewById(R.id.activity_game_question_text);
        answer1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        answer2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        answer3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        answer4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        // Create our question bank
        questionBank = this.generateQuestions();

        // Set our total number of questions and the user score
        numberofQuestionsTotal = 9;
        numberOfQuestionsLeft = numberofQuestionsTotal;
        score = 0;

        // Use the same listener for the four buttons
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);

        // Use the tage property to name the buttons by their index
        answer1.setTag(0);
        answer2.setTag(1);
        answer3.setTag(2);
        answer4.setTag(3);

        // Display the 1st question
       currentQuestion = questionBank.getQuestion();
       displayQuestion(currentQuestion);

    }

    public void displayQuestion(final Question theQuestion){

        // We display the question and the answers on the view
        question.setText(theQuestion.getQuestion());

        //Reset background colors
        answer1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        answer2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        answer3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        answer4.setBackgroundColor(Color.parseColor("#FFFFFF"));

        answer1.setText(theQuestion.getChoiceList().get(0));
        answer2.setText(theQuestion.getChoiceList().get(1));
        answer3.setText(theQuestion.getChoiceList().get(2));
        answer4.setText(theQuestion.getChoiceList().get(3));
    }

    // Our function used to generate and shuffle questions
    public QuestionBank generateQuestions(){
        Question question1 = new Question("Quel est le nom du président de la France en 2019 ?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("Combien de pays figurent dans l'union européenne ?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Qui est le création du système d'exploitation Android ?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("Quand est-ce que le 1er homme s'est posé sur la lune ?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("Quelle est la capitale de la Roumanie?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Qui a peint Mona Lisa ?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("Dans quelle ville le compositeur Frédéric Chopin a-t-il été enterré ?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("Quel est le nom de domaine de la Belgique ?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("Quel est le numéro de maison des Simpson ?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        QuestionBank questionBank = new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));

        return questionBank;
    }

    @Override
    public void onClick(View v) {
        if(onQuestion){ // If the game is on a question, we show the correct answer
            // Get the index of the button
            int responseIndex = (int) v.getTag();

            // If the answer is correct
            if(responseIndex == currentQuestion.getAnswerIndex() ){
                v.setBackgroundColor(Color.parseColor("#42f557")); // Set the button to green
                score++; // Incrementation of the score
            }
            else{ // Else if it's wrong
                v.setBackgroundColor(Color.parseColor("#f7301e")); // Set color to red

                // We retrieve the four buttons to determine which one will be green
                Button result1 = (Button) findViewById(R.id.activity_game_answer1_btn);
                Button result2 = (Button) findViewById(R.id.activity_game_answer2_btn);
                Button result3 = (Button) findViewById(R.id.activity_game_answer3_btn);
                Button result4 = (Button) findViewById(R.id.activity_game_answer4_btn);
                // Set bg color on green only on the correct answer
                switch(currentQuestion.getAnswerIndex()){
                    case 0: result1.setBackgroundColor(Color.parseColor("#42f557"));
                        break;

                    case 1: result2.setBackgroundColor(Color.parseColor("#42f557"));
                        break;

                    case 2: result3.setBackgroundColor(Color.parseColor("#42f557"));
                        break;

                    case 3: result4.setBackgroundColor(Color.parseColor("#42f557"));
                        break;

                    default: break;
                }
            }
            onQuestion = false;
            numberOfQuestionsLeft--;

        }else if (numberOfQuestionsLeft == 0){
            // If there is no questions remaining, we stop the game
            // And display the score of the user
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Bien joué !")
                    .setMessage("Ton score est de " + score + " sur " + numberofQuestionsTotal)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create()
                    .show();

        }else{
            // Else we skip to the next question
            // We set the next question then display it
            currentQuestion = questionBank.getQuestion();
            displayQuestion(currentQuestion);
            onQuestion = true;
        }


    }
}
