package com.example.project2;

import java.util.ArrayList;

public class Question  {
    private String description;
    private String answer;
private ArrayList<Question> questions =new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
