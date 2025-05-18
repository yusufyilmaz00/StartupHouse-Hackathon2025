package com.yusufyilmaz00.edulai.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {AnalyzeTopic.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract AnalyzeTopicDao analyzeTopicDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app-db")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.d("RoomDB", "onCreate çalıştı");

                            Executors.newSingleThreadExecutor().execute(() -> {
                                preloadFromJson(context.getApplicationContext(), INSTANCE);
                            });
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    private static void preloadFromJson(Context context, AppDatabase db) {
        try {
            InputStream is = context.getAssets().open("analyze_topics.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonStr = new String(buffer, "UTF-8");

            Log.d("RoomDB", "JSON içeriği: " + jsonStr); // 🔍 Log ekle

            Gson gson = new Gson();
            Type listType = new TypeToken<List<AnalyzeTopic>>() {}.getType();
            List<AnalyzeTopic> topicList = gson.fromJson(jsonStr, listType);

            for (AnalyzeTopic topic : topicList) {
                db.analyzeTopicDao().insert(topic);
            }

            Log.d("RoomDB", "Veriler JSON'dan yüklendi. Toplam: " + topicList.size());

        } catch (Exception e) {
            Log.e("RoomDB", "JSON preload hatası", e);
        }
    }

}
