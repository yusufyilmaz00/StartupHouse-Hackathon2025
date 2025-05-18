package com.yusufyilmaz00.edulai.ui.chat;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yusufyilmaz00.edulai.R;

import java.io.IOException;

public class ChatFragment extends Fragment {

    private TextView questionHeader, questionContent;
    private ImageView questionImage;
    private EditText editMessageInput;
    private ImageButton sendButton;
    private LinearLayout messageContainer;

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
        questionImage = view.findViewById(R.id.image_question);
        editMessageInput = view.findViewById(R.id.edit_message_input);
        sendButton = view.findViewById(R.id.button_send_message);
        messageContainer = view.findViewById(R.id.message_container);

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
                // İlk AI mesajı göster
                addAIMessage("Bu sorunun çözümü için önce verilenleri değerlendirelim...");
                addAIMessage("Çözüm: x + y = 12 denkleminde... Cevap: x = 7, y = 5");

                // Kullanıcı mesaj girişi aktif hale getir
                editMessageInput.setEnabled(true);
                sendButton.setEnabled(true);

                sendButton.setOnClickListener(v -> {
                    String userMessage = editMessageInput.getText().toString().trim();
                    if (!userMessage.isEmpty()) {
                        addUserMessage(userMessage);
                        editMessageInput.setText("");

                        // Simüle edilmiş AI cevabı
                        new Handler().postDelayed(() -> {
                            addAIMessage("Bu soruya başka bir açıdan da bakabiliriz...");
                        }, 1500);
                    }
                });
            } else {
                addAIMessage("Bu soru daha sonra çözülmek üzere kaydedildi.");
            }
        }
    }

    private void addUserMessage(String message) {
        TextView textView = new TextView(getContext());
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.bg_user_message);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setTextSize(16);
        textView.setPadding(24, 16, 24, 16);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 8, 0, 8);
        params.gravity = android.view.Gravity.END;
        textView.setLayoutParams(params);
        messageContainer.addView(textView);
    }

    private void addAIMessage(String message) {
        TextView textView = new TextView(getContext());
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.bg_ai_message);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setTextSize(16);
        textView.setPadding(24, 16, 24, 16);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 8, 0, 8);
        params.gravity = android.view.Gravity.START;
        textView.setLayoutParams(params);
        messageContainer.addView(textView);
    }
}
