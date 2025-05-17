package com.yusufyilmaz00.edulai.ui.chat;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yusufyilmaz00.edulai.R;

import java.io.IOException;

public class ChatFragment extends Fragment {

    private TextView questionHeader, questionContent, aiResponse1, aiResponse2;
    private ImageView questionImage;

    public ChatFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        questionHeader = view.findViewById(R.id.text_question_header);
        questionContent = view.findViewById(R.id.text_question_content);
        aiResponse1 = view.findViewById(R.id.text_ai_response);
        aiResponse2 = view.findViewById(R.id.text_ai_response_2);
        questionImage = view.findViewById(R.id.image_question);

        Bundle args = getArguments();
        if (args != null) {
            String subject = args.getString("subject", "");
            String questionText = args.getString("questionText", "");
            String imageUri = args.getString("imageUri", null);
            boolean solveLater = args.getBoolean("solveLater", false);

            questionHeader.setText("Soru (" + subject + "):");
            questionContent.setText(questionText.isEmpty() ? "(Yalnızca görsel yüklenmiş)" : questionText);

            if (imageUri != null) {
                try {
                    Uri uri = Uri.parse(imageUri);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
                    questionImage.setVisibility(View.VISIBLE);
                    questionImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!solveLater) {
                aiResponse1.setText("Bu sorunun çözümü için önce verilenleri değerlendirelim...");
                aiResponse2.setText("Çözüm: x + y = 12 denkleminde... Cevap: x = 7, y = 5");
            } else {
                aiResponse1.setText("Bu soru daha sonra çözülmek üzere kaydedildi.");
                aiResponse2.setVisibility(View.GONE);
            }
        }
    }
}
