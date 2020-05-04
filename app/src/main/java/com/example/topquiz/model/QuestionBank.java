package com.example.topquiz.model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> questionList;
    private int nextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        this.questionList = questionList;
        // Shuffle the question list
        Collections.shuffle(questionList);
        // Set the index to 0
        nextQuestionIndex = 0;
    }

    public Question getQuestion() {
        // Ensure we loop over the questions
        if (nextQuestionIndex == questionList.size()) {
            nextQuestionIndex = 0;
        }
        // Get the question, set the next question to +1 then return
        Question question = questionList.get(nextQuestionIndex);
        nextQuestionIndex++;
        return question;
    }
}
