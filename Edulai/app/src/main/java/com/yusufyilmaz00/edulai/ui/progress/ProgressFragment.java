package com.yusufyilmaz00.edulai.ui.progress;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.yusufyilmaz00.edulai.R;
import com.yusufyilmaz00.edulai.model.SolvedQuestion;
import com.yusufyilmaz00.edulai.model.SolvedQuestionAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ProgressFragment extends Fragment {

    public ProgressFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 📊 İstatistik Widget'ları
        TextView textTotalQuestions = view.findViewById(R.id.textTotalQuestions);
        TextView textSolvedQuestions = view.findViewById(R.id.textSolvedQuestions);
        TextView textTopicCount = view.findViewById(R.id.textTopicCount);
        TextView textIncompleteTopics = view.findViewById(R.id.textIncompleteTopics);
        ProgressBar completionProgress = view.findViewById(R.id.completionProgress);
        ProgressBar successProgress = view.findViewById(R.id.successProgress);
        TextView completionText = view.findViewById(R.id.completionText);
        TextView successText = view.findViewById(R.id.successText);

        // 🟡 RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.solvedQuestionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 📚 Veri Listesi
        List<SolvedQuestion> dummyList = generateDummyQuestions();

        // ✅ İstatistik Hesaplama
        int total = dummyList.size();
        int solved = total;
        int correct = 0;
        Set<String> topicSet = new HashSet<>();
        Set<String> incorrectTopicSet = new HashSet<>();

        for (SolvedQuestion q : dummyList) {
            topicSet.add(q.getTopicName());
            if (q.isCorrect()) correct++;
            else incorrectTopicSet.add(q.getTopicName());
        }

        int completionRate = 100;
        int successRate = (int)((double) correct / total * 100);

        // 🧠 UI Güncelle
        textTotalQuestions.setText("Toplam Yüklenen: " + total);
        textSolvedQuestions.setText("Çözülen: " + solved);
        textTopicCount.setText("Farklı Konu: " + topicSet.size());
        textIncompleteTopics.setText("Eksik Konu: " + incorrectTopicSet.size());
        completionProgress.setProgress(completionRate);
        successProgress.setProgress(successRate);
        completionText.setText("Tamamlanma: %" + completionRate);
        successText.setText("Başarı: %" + successRate);

        // ♻️ Adapter
        SolvedQuestionAdapter adapter = new SolvedQuestionAdapter(dummyList, question ->
                Toast.makeText(getContext(), "Soru: " + question.getQuestionText(), Toast.LENGTH_SHORT).show()
        );
        recyclerView.setAdapter(adapter);

        // 🏷️ Etiketler (Flexbox)
        FlexboxLayout tagContainer = view.findViewById(R.id.topicTagContainer);
        for (String topic : topicSet) {
            TextView tag = new TextView(getContext());
            tag.setText(topic);
            tag.setTextSize(14);
            tag.setPadding(32, 12, 32, 12);
            tag.setBackgroundResource(R.drawable.tag_background);
            tag.setTextColor(Color.WHITE);
            tag.setTypeface(null, Typeface.BOLD);

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 0, 12, 8); // satır arası boşluk
            tag.setLayoutParams(params);

            tag.setOnClickListener(v ->
                    Toast.makeText(getContext(), topic + " etiketi tıklandı", Toast.LENGTH_SHORT).show()
            );

            tagContainer.addView(tag);
        }
    }

    // 🔁 Örnek veri üreten yardımcı fonksiyon
    private List<SolvedQuestion> generateDummyQuestions() {
        List<SolvedQuestion> list = new ArrayList<>();
        list.add(new SolvedQuestion("Matematik", "Trigonometri", "Bir üçgende tanjant oranı nedir?", "17 Mayıs 2025", false));
        list.add(new SolvedQuestion("Fizik", "Kuvvet", "Newton’un ikinci yasası nedir?", "18 Mayıs 2025", true));
        list.add(new SolvedQuestion("Kimya", "Periyodik Sistem", "Grup ve periyot neyi ifade eder?", "18 Mayıs 2025", true));
        list.add(new SolvedQuestion("Biyoloji", "Bitkiler", "Fotosentez hangi organelde gerçekleşir?", "19 Mayıs 2025", false));
        list.add(new SolvedQuestion("Coğrafya", "İklim Tipleri", "Muson iklimi nerelerde görülür?", "19 Mayıs 2025", true));
        list.add(new SolvedQuestion("Tarih", "İnkılaplar", "Atatürk’ün ilkeleri nelerdir?", "20 Mayıs 2025", true));
        list.add(new SolvedQuestion("Matematik", "Logaritma", "log(x^2) = ? ifadesinin sonucu nedir?", "20 Mayıs 2025", false));
        list.add(new SolvedQuestion("Fizik", "Elektrik", "Ohm kanunu nedir?", "20 Mayıs 2025", true));
        list.add(new SolvedQuestion("Din Kültürü", "İslam Tarihi", "Hicret ne zaman gerçekleşmiştir?", "21 Mayıs 2025", true));
        list.add(new SolvedQuestion("Edebiyat", "Şiir Bilgisi", "Serbest şiir ne demektir?", "21 Mayıs 2025", false));
        list.add(new SolvedQuestion("Matematik", "Denklem", "2x + 3 = 7 denklemini çözünüz.", "22 Mayıs 2025", true));
        list.add(new SolvedQuestion("Fizik", "Hareket", "Sabit ivmeli hareketin formülü nedir?", "22 Mayıs 2025", false));
        list.add(new SolvedQuestion("Kimya", "Asit-Baz", "pH değeri 7'nin altı neyi ifade eder?", "22 Mayıs 2025", true));
        list.add(new SolvedQuestion("Biyoloji", "Hücre", "Mitokondri ne işe yarar?", "22 Mayıs 2025", true));
        list.add(new SolvedQuestion("Tarih", "Osmanlı", "Osmanlı Devleti ne zaman kuruldu?", "23 Mayıs 2025", false));
        list.add(new SolvedQuestion("Coğrafya", "Kıtalar", "En büyük kıta hangisidir?", "23 Mayıs 2025", true));
        list.add(new SolvedQuestion("Felsefe", "Bilgi Felsefesi", "Empirizm nedir?", "23 Mayıs 2025", false));
        list.add(new SolvedQuestion("Din Kültürü", "İbadetler", "Namaz kaç vakittir?", "23 Mayıs 2025", true));
        list.add(new SolvedQuestion("Edebiyat", "Tanzimat", "Tanzimat edebiyatının ilk temsilcisi kimdir?", "24 Mayıs 2025", false));
        list.add(new SolvedQuestion("Psikoloji", "Davranış", "Koşullanma nedir ve örnek veriniz.", "24 Mayıs 2025", true));
        return list;
    }
}
