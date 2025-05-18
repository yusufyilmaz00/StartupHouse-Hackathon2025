package com.yusufyilmaz00.edulai.ui.progress;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yusufyilmaz00.edulai.R;
import com.yusufyilmaz00.edulai.model.SolvedQuestion;
import com.yusufyilmaz00.edulai.model.SolvedQuestionAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgressFragment extends Fragment {

    public ProgressFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 🔵 Progress bar’ları ayarla
        ProgressBar completionProgress = view.findViewById(R.id.completionProgress);
        TextView completionText = view.findViewById(R.id.completionText);
        ProgressBar successProgress = view.findViewById(R.id.successProgress);
        TextView successText = view.findViewById(R.id.successText);

        int completionRate = 70;
        int successRate = 85;

        completionProgress.setProgress(completionRate);
        completionText.setText("Tamamlanma: %" + completionRate);
        successProgress.setProgress(successRate);
        successText.setText("Başarı: %" + successRate);

        // 🟣 Konu etiketleri
        LinearLayout tagContainer = view.findViewById(R.id.topicTagContainer);
        List<String> topics = Arrays.asList("Trigonometri", "Kuvvet", "Periyodik Sistem", "Bitkiler");

        for (String topic : topics) {
            TextView tag = new TextView(getContext());
            tag.setText(topic);
            tag.setTextSize(14);
            tag.setPadding(32, 12, 32, 12);
            tag.setBackgroundResource(R.drawable.tag_background);
            tag.setTextColor(Color.WHITE);
            tag.setTypeface(null, Typeface.BOLD);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 0, 12, 0);
            tag.setLayoutParams(params);

            tag.setOnClickListener(v ->
                    Toast.makeText(getContext(), topic + " filtresi uygulandı", Toast.LENGTH_SHORT).show()
            );

            tagContainer.addView(tag);
        }

        // 🟡 RecyclerView: Çözülen sorular
        RecyclerView recyclerView = view.findViewById(R.id.solvedQuestionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<SolvedQuestion> dummyList = new ArrayList<>();
        dummyList.add(new SolvedQuestion("Matematik", "Trigonometri", "Bir üçgende tanjant oranı nedir?", "17 Mayıs 2025", false));
        dummyList.add(new SolvedQuestion("Fizik", "Kuvvet", "Newton’un ikinci yasası nedir?", "18 Mayıs 2025", true));

        SolvedQuestionAdapter adapter = new SolvedQuestionAdapter(dummyList, question ->
                Toast.makeText(getContext(), "Soru: " + question.getQuestionText(), Toast.LENGTH_SHORT).show()
        );

        recyclerView.setAdapter(adapter);
    }
}
