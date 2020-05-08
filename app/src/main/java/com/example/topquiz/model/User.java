package com.example.topquiz.model;

public class User {

    private String firstName; // Name of the user
    private int score; // His score

    public User() {
        this.score = 0; // Set score to 0
    }



    // Getter and Setter
    public int getScore() {
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



}
