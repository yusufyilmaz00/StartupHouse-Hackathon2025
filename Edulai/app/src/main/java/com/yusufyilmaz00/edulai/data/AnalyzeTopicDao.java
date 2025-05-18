package com.yusufyilmaz00.edulai.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnalyzeTopicDao {

    @Query("SELECT * FROM analyze_topics")
    List<AnalyzeTopic> getAll();

    @Insert
    void insert(AnalyzeTopic topic);

    @Query("DELETE FROM analyze_topics WHERE topicName = :topicName AND lessonName = :lessonName")
    void deleteByTopicAndLesson(String topicName, String lessonName);
}
