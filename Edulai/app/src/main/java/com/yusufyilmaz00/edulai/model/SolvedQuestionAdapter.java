package com.yusufyilmaz00.edulai.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yusufyilmaz00.edulai.R;

import java.util.List;

public class SolvedQuestionAdapter extends RecyclerView.Adapter<SolvedQuestionAdapter.ViewHolder> {

    private List<SolvedQuestion> questionList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(SolvedQuestion question);
    }

    public SolvedQuestionAdapter(List<SolvedQuestion> questionList, OnItemClickListener listener) {
        this.questionList = questionList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTopic, textDate, textQuestion;
        ImageView iconIncorrect;

        public ViewHolder(View itemView) {
            super(itemView);
            textTopic = itemView.findViewById(R.id.textTopic);
            textDate = itemView.findViewById(R.id.textDate);
            textQuestion = itemView.findViewById(R.id.textQuestion);
            iconIncorrect = itemView.findViewById(R.id.iconIncorrect);
        }

        public void bind(SolvedQuestion question, OnItemClickListener listener) {
            textTopic.setText(question.getLessonName() + " • " + question.getTopicName());
            textDate.setText(question.getDate());
            textQuestion.setText(question.getQuestionText());

            iconIncorrect.setVisibility(question.isCorrect() ? View.GONE : View.VISIBLE);

            itemView.setOnClickListener(v -> listener.onItemClick(question));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_solved_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(questionList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
