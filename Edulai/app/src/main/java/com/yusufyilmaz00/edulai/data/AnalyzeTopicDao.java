package com.yusufyilmaz00.edulai.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnalyzeTopicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AnalyzeTopic topic);

    @Query("SELECT * FROM analyze_topics")
    List<AnalyzeTopic> getAll();

    @Query("DELETE FROM analyze_topics WHERE topicName = :topic")
    void deleteTopic(String topic);
}
