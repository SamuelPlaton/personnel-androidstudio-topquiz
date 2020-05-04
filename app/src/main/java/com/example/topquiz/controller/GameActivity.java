package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    // Our questions
    private QuestionBank questionBank;

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
}
