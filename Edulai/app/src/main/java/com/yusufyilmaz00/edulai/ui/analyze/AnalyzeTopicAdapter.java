package com.yusufyilmaz00.edulai.ui.analyze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yusufyilmaz00.edulai.R;
import com.yusufyilmaz00.edulai.data.AnalyzeTopic;

import java.util.List;

public class AnalyzeTopicAdapter extends RecyclerView.Adapter<AnalyzeTopicAdapter.TopicViewHolder> {

    private List<AnalyzeTopic> topicList;
    private Context context;

    public interface OnRepeatClickListener {
        void onRepeatClick(AnalyzeTopic topic);
    }

    private OnRepeatClickListener listener;

    public AnalyzeTopicAdapter(Context context, List<AnalyzeTopic> topicList, OnRepeatClickListener listener) {
        this.context = context;
        this.topicList = topicList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_analyze_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        AnalyzeTopic topic = topicList.get(position);
        holder.textLesson.setText(topic.lessonName);
        holder.textTopic.setText(topic.topicName);
        holder.textMistake.setText(topic.mistakeCount + " kez hata yaptın");

        holder.buttonRepeat.setOnClickListener(v -> {
            if (listener != null) listener.onRepeatClick(topic);
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView textLesson, textTopic, textMistake;
        Button buttonRepeat;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            textLesson = itemView.findViewById(R.id.text_lesson);
            textTopic = itemView.findViewById(R.id.text_topic);
            textMistake = itemView.findViewById(R.id.text_mistakes);
            buttonRepeat = itemView.findViewById(R.id.button_repeat);
        }
    }
}
