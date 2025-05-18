package com.yusufyilmaz00.edulai.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "analyze_topics")
public class AnalyzeTopic {

    @PrimaryKey
    @NonNull
    public String topicName;

    public String lessonName;
    public int mistakeCount;

    public AnalyzeTopic(String topicName, String lessonName, int mistakeCount) {
        this.topicName = topicName;
        this.lessonName = lessonName;
        this.mistakeCount = mistakeCount;
    }
}
