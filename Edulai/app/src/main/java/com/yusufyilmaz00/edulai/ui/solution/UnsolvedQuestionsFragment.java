package com.yusufyilmaz00.edulai.ui.solution;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yusufyilmaz00.edulai.R;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class UnsolvedQuestionsFragment extends Fragment {

    public UnsolvedQuestionsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unsolved_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_unsolved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String[]> dummyData = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            dummyData.add(new String[]{"Matematik - Denklem Çözme", "x + y = " + (10 + i)});
        }

        recyclerView.setAdapter(new RecyclerView.Adapter<UnsolvedQuestionsFragment.QuestionViewHolder>() {
            @NonNull
            @Override
            public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unsolved_question, parent, false);
                return new QuestionViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
                String[] data = dummyData.get(position);
                holder.subject.setText(data[0]);
                holder.question.setText(data[1]);
                holder.itemView.setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("subject", data[0]);
                    bundle.putString("question", data[1]);
                    NavHostFragment.findNavController(UnsolvedQuestionsFragment.this).navigate(R.id.navigation_solution, bundle);
                });
            }

            @Override
            public int getItemCount() {
                return dummyData.size();
            }
        });
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView subject, question;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.text_subject);
            question = itemView.findViewById(R.id.text_question);
        }
    }

    public static class UnsolvedItem {
        public final String subject;
        public final String question;

        public UnsolvedItem(String subject, String question) {
            this.subject = subject;
            this.question = question;
        }

        public String getSubject() {
            return subject;
        }

        public String getQuestion() {
            return question;
        }
    }

    public static class UnsolvedAdapter extends RecyclerView.Adapter<UnsolvedQuestionsFragment.QuestionViewHolder> {
        private final List<UnsolvedItem> items;
        private final Fragment fragment;

        public UnsolvedAdapter(List<UnsolvedItem> items, Fragment fragment) {
            this.items = items;
            this.fragment = fragment;
        }


        @NonNull
        @Override
        public UnsolvedQuestionsFragment.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unsolved_question, parent, false);
            return new UnsolvedQuestionsFragment.QuestionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UnsolvedQuestionsFragment.QuestionViewHolder holder, int position) {
            UnsolvedItem item = items.get(position);
            holder.subject.setText(item.subject);
            holder.question.setText(item.question);

            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("subject", item.subject);
                bundle.putString("question", item.question);
                NavHostFragment.findNavController(fragment).navigate(R.id.navigation_solution, bundle);
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}