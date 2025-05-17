package com.yusufyilmaz00.edulai.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.yusufyilmaz00.edulai.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView welcomeText = root.findViewById(R.id.text_welcome);
        ImageButton notificationIcon = root.findViewById(R.id.notification_icon);
        CardView uploadShortcut = root.findViewById(R.id.card_upload_shortcut);
        Button solveButton = root.findViewById(R.id.button_solve);

        // Kullanıcı adı gösterimi (örnek amaçlı sabit metin)
        welcomeText.setText("Hoş Geldin, Yusuf");

        // Bildirim ikonuna tıklama
        notificationIcon.setOnClickListener(v -> {
            // Buraya bildirim popup veya yönlendirme eklersin
        });

        /* // Soru Yükle sayfasına yönlendirme
        uploadShortcut.setOnClickListener(v -> {
            Log.d("DebugClick", "Kullanıcı soru yükle kısayoluna tıkladı.");
            Navigation.findNavController(v).navigate(R.id.navigation_upload);
        });
        */

        /* // Çözümle butonu → örnek olarak Upload sayfasına gidiyor
        solveButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.navigation_upload);
        });
        */

        return root;
    }
}