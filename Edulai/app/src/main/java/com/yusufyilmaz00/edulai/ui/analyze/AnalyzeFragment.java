package com.yusufyilmaz00.edulai.ui.analyze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yusufyilmaz00.edulai.R;
import com.yusufyilmaz00.edulai.data.AnalyzeTopic;
import com.yusufyilmaz00.edulai.data.AppDatabase;
import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.List;

public class AnalyzeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textNoSuggestions;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_analyze, container, false);
        textNoSuggestions = root.findViewById(R.id.text_no_suggestions);;
        recyclerView = root.findViewById(R.id.recycler_analyze);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadAnalyzeTopics();
        return root;
    }

    private void loadAnalyzeTopics() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getContext());
            List<AnalyzeTopic> topicList = db.analyzeTopicDao().getAll();
            Log.d("RoomTest", "TOPIC SAYISI: " + topicList.size());

            requireActivity().runOnUiThread(() -> {
                if (topicList.isEmpty()) {
                    textNoSuggestions.setVisibility(View.VISIBLE);
                } else {
                    textNoSuggestions.setVisibility(View.GONE);
                    recyclerView.setAdapter(new AnalyzeTopicAdapter(getContext(), topicList, topic -> {
                        NavController navController = Navigation.findNavController(requireView());

                        Bundle bundle = new Bundle();
                        bundle.putString("topicName", topic.topicName);
                        bundle.putString("lessonName", topic.lessonName);

                        navController.navigate(R.id.action_navigation_analyze_to_navigation_quiz, bundle);
                    }));
                }

            });
        }).start();
    }
}





