package com.yusufyilmaz00.edulai.model;

public class QuizQuestion {
    public String questionText;
    public String[] choices;
    public int correctAnswerIndex;

    public QuizQuestion(String questionText, String[] choices, int correctAnswerIndex) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
