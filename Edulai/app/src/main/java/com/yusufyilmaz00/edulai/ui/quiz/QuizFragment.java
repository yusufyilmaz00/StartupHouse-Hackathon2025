package com.yusufyilmaz00.edulai.ui.quiz;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yusufyilmaz00.edulai.R;
import com.yusufyilmaz00.edulai.model.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {

    private TextView textQuestion, textFeedback, textResultTopic, textResultStats;
    private Button[] choiceButtons = new Button[4];
    private Button buttonSkip, buttonNext;
    private LinearLayout resultCard;

    private List<QuizQuestion> questionList;
    private int currentQuestionIndex = 0;
    private int correctCount = 0, wrongCount = 0, emptyCount = 0;
    private String topicName = "", lessonName = "";
    private int selectedChoiceIndex = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_quiz, container, false);

        textQuestion = root.findViewById(R.id.text_question);
        textFeedback = root.findViewById(R.id.text_feedback);
        textResultTopic = root.findViewById(R.id.text_result_topic);
        textResultStats = root.findViewById(R.id.text_result_stats);
        resultCard = root.findViewById(R.id.result_card);

        choiceButtons[0] = root.findViewById(R.id.button_choice_0);
        choiceButtons[1] = root.findViewById(R.id.button_choice_1);
        choiceButtons[2] = root.findViewById(R.id.button_choice_2);
        choiceButtons[3] = root.findViewById(R.id.button_choice_3);
        buttonSkip = root.findViewById(R.id.button_skip);
        buttonNext = root.findViewById(R.id.button_next);

        Bundle args = getArguments();
        if (args != null) {
            topicName = args.getString("topicName", "Konu");
            lessonName = args.getString("lessonName", "Ders");
        }

        generateDummyQuestions();
        loadQuestion();

        for (int i = 0; i < 4; i++) {
            final int index = i;
            choiceButtons[i].setOnClickListener(v -> selectChoice(index));
        }

        buttonSkip.setOnClickListener(v -> handleSkip());
        buttonNext.setOnClickListener(v -> handleNext());

        return root;
    }

    private void generateDummyQuestions() {
        questionList = new ArrayList<>();

        if (topicName.equalsIgnoreCase("Parabol")) {
            questionList.add(new QuizQuestion("Parabol grafiği hangi eksende simetriktir?",
                    new String[]{"X Ekseni", "Y Ekseni", "Z Ekseni", "Orijin"}, 1));
            questionList.add(new QuizQuestion("Parabol denkleminin genel hali nedir?",
                    new String[]{"y = ax^2 + bx + c", "y = mx + n", "x = a + by", "y = abx"}, 0));
            questionList.add(new QuizQuestion("Parabolün tepe noktası nedir?",
                    new String[]{"x = -b/2a", "x = b/2a", "x = -a/2b", "x = a^2 + b^2"}, 0));
            questionList.add(new QuizQuestion("Parabolün kolları neye göre yönlenir?",
                    new String[]{"a'nın işaretine", "b'nin işaretine", "c'nin büyüklüğüne", "abc'nin çarpımına"}, 0));
            questionList.add(new QuizQuestion("a < 0 ise parabol nasıldır?",
                    new String[]{"Kollar yukarı", "Kollar aşağı", "Simetrik değil", "Grafik çizilemez"}, 1));
        } else if (topicName.equalsIgnoreCase("Elektrik Akımı")) {
            questionList.add(new QuizQuestion("Elektrik akımının birimi nedir?",
                    new String[]{"Volt", "Watt", "Amper", "Ohm"}, 2));
            questionList.add(new QuizQuestion("Elektrik akımı ne ile ölçülür?",
                    new String[]{"Voltmetre", "Ampermetre", "Ohmmetre", "Termometre"}, 1));
            questionList.add(new QuizQuestion("Direnç arttıkça akım ne olur?",
                    new String[]{"Artar", "Azalır", "Değişmez", "Sıfır olur"}, 1));
            questionList.add(new QuizQuestion("V = I * R denkleminde I nedir?",
                    new String[]{"Gerilim", "Güç", "Akım", "Direnç"}, 2));
            questionList.add(new QuizQuestion("İletken bir telde elektronlar hangi yönde hareket eder?",
                    new String[]{"Artıdan eksiye", "Eksiden artıya", "Yukarı", "Aşağı"}, 1));
        }
    }

    private void loadQuestion() {
        if (currentQuestionIndex >= questionList.size()) {
            showResults();
            return;
        }

        QuizQuestion q = questionList.get(currentQuestionIndex);
        textQuestion.setText(q.questionText);
        for (int i = 0; i < 4; i++) {
            choiceButtons[i].setText(q.choices[i]);
            choiceButtons[i].setEnabled(true);
            choiceButtons[i].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }
        textFeedback.setVisibility(View.GONE);
        buttonSkip.setEnabled(true);
        buttonNext.setEnabled(true);
        selectedChoiceIndex = -1;
    }

    private void selectChoice(int index) {
        selectedChoiceIndex = index;
        for (int i = 0; i < 4; i++) {
            choiceButtons[i].setBackgroundColor(getResources().getColor(i == index ? android.R.color.holo_green_light : android.R.color.holo_blue_light));
        }
    }

    private void handleSkip() {
        disableChoices();
        emptyCount++;
        QuizQuestion current = questionList.get(currentQuestionIndex);
        showFeedback(false, current.choices[current.correctAnswerIndex]);
        new Handler().postDelayed(this::nextQuestion, 3000);
    }

    private void handleNext() {
        disableChoices();
        QuizQuestion current = questionList.get(currentQuestionIndex);
        if (selectedChoiceIndex == -1) {
            emptyCount++;
            showFeedback(false, current.choices[current.correctAnswerIndex]);
        } else {
            boolean correct = (selectedChoiceIndex == current.correctAnswerIndex);
            if (correct) correctCount++; else wrongCount++;
            showFeedback(correct, current.choices[current.correctAnswerIndex]);
        }
        new Handler().postDelayed(this::nextQuestion, 3000);
    }

    private void showFeedback(boolean correct, String correctAnswer) {
        textFeedback.setText(correct ? "Doğru!" : "Yanlış! Doğru cevap: " + correctAnswer);
        textFeedback.setVisibility(View.VISIBLE);
    }

    private void disableChoices() {
        for (Button btn : choiceButtons) btn.setEnabled(false);
        buttonSkip.setEnabled(false);
        buttonNext.setEnabled(false);
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        loadQuestion();
    }

    private void showResults() {
        textQuestion.setVisibility(View.GONE);
        for (Button btn : choiceButtons) btn.setVisibility(View.GONE);
        buttonSkip.setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);
        textFeedback.setVisibility(View.GONE);

        resultCard.setVisibility(View.VISIBLE);
        textResultTopic.setText("Ders: " + lessonName + "\nKonu: " + topicName);
        textResultStats.setText("Doğru: " + correctCount + " | Yanlış: " + wrongCount + " | Boş: " + emptyCount);
    }
}
