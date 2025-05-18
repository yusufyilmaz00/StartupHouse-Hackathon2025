package com.yusufyilmaz00.edulai.model;

public class SolvedQuestion {
    private String lessonName;
    private String topicName;
    private String questionText;
    private String date;         // Örn: "17 Mayıs 2025"
    private boolean isCorrect;

    public SolvedQuestion(String lessonName, String topicName, String questionText, String date, boolean isCorrect) {
        this.lessonName = lessonName;
        this.topicName = topicName;
        this.questionText = questionText;
        this.date = date;
        this.isCorrect = isCorrect;
    }

    public String getLessonName() { return lessonName; }
    public String getTopicName() { return topicName; }
    public String getQuestionText() { return questionText; }
    public String getDate() { return date; }
    public boolean isCorrect() { return isCorrect; }
}
