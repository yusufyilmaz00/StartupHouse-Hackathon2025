package com.yusufyilmaz00.edulai.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yusufyilmaz00.edulai.R;
import com.yusufyilmaz00.edulai.ui.solution.UnsolvedQuestionsFragment.UnsolvedAdapter;
import com.yusufyilmaz00.edulai.ui.solution.UnsolvedQuestionsFragment.UnsolvedItem;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // İstatistik verileri
        RecyclerView statRecyclerView = view.findViewById(R.id.recycler_stats);
        statRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<StatItem> dummyStats = new ArrayList<>();
        dummyStats.add(new StatItem("14/06", 7));
        dummyStats.add(new StatItem("15/06", 0));
        dummyStats.add(new StatItem("16/06", 6));
        dummyStats.add(new StatItem("17/06", 5));
        dummyStats.add(new StatItem("18/06", 4));
        dummyStats.add(new StatItem("19/06", 9));
        dummyStats.add(new StatItem("20/06", 7));
        statRecyclerView.setAdapter(new StatAdapter(dummyStats));

        // Son yüklenen sorular
        RecyclerView lastUploadedRecycler = view.findViewById(R.id.recycler_last_uploaded);
        lastUploadedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<UnsolvedItem> latestQuestions = new ArrayList<>();
        latestQuestions.add(new UnsolvedItem("Matematik – Fonksiyonlar", "f(x) = 2x + 3 fonksiyonunun grafiğini çizer misin?"));
        latestQuestions.add(new UnsolvedItem("Fizik – Newton’un Yasaları", "Bir cisme 10 N kuvvet uygulanırsa ivmesi nasıl değişir?"));
        latestQuestions.add(new UnsolvedItem("Kimya – Asit Baz", "NaOH ve HCl tepkimesi sonucunda ne oluşur?"));
        latestQuestions.add(new UnsolvedItem("Biyoloji – Genetik", "DNA’daki baz eşleşmeleri nasıl olur?"));
        latestQuestions.add(new UnsolvedItem("Tarih – 20. Yüzyıl", "I. Dünya Savaşı'nın nedenleri nelerdir?"));

        lastUploadedRecycler.setAdapter(new UnsolvedAdapter(latestQuestions, this));
    }

    public static class StatItem {
        public final String date;
        public final int count;
        public StatItem(String date, int count) {
            this.date = date;
            this.count = count;
        }
    }

    public static class StatAdapter extends RecyclerView.Adapter<StatAdapter.StatViewHolder> {
        private final List<StatItem> items;
        public StatAdapter(List<StatItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public StatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stat_day, parent, false);
            return new StatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StatViewHolder holder, int position) {
            StatItem item = items.get(position);
            holder.countView.setText(String.valueOf(item.count));
            holder.dateView.setText(item.date);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public static class StatViewHolder extends RecyclerView.ViewHolder {
            TextView countView, dateView;
            public StatViewHolder(@NonNull View itemView) {
                super(itemView);
                countView = itemView.findViewById(R.id.text_stat_count);
                dateView = itemView.findViewById(R.id.text_stat_date);
            }
        }
    }
}
