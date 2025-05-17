package com.yusufyilmaz00.edulai.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.yusufyilmaz00.edulai.R;

import java.io.IOException;
import java.util.Arrays;

public class UploadFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 101;
    private ImageView imagePreview;
    private EditText editTextQuestion;
    private Switch switchNewType, switchSolveLater;
    private Spinner spinnerSubject;
    private Uri selectedImageUri;

    public UploadFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imagePreview = view.findViewById(R.id.image_preview);
        editTextQuestion = view.findViewById(R.id.edit_text_question);
        switchNewType = view.findViewById(R.id.switch_new_type);
        switchSolveLater = view.findViewById(R.id.switch_solve_later);
        spinnerSubject = view.findViewById(R.id.spinner_subject);
        Button buttonSelectImage = view.findViewById(R.id.button_select_image);
        Button buttonSubmit = view.findViewById(R.id.button_submit);

        // Spinner ders listesi
        String[] subjects = {
                "Biyoloji", "Coğrafya", "Din Kültürü", "Felsefe", "Fizik",
                "Kimya", "Matematik", "Tarih", "Türkçe"
        };
        Arrays.sort(subjects);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubject.setAdapter(adapter);

        // Görsel seçimi
        buttonSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        });

        // Gönder butonu
        buttonSubmit.setOnClickListener(v -> {
            String questionText = editTextQuestion.getText().toString().trim();
            boolean isNewType = switchNewType.isChecked();
            boolean solveLater = switchSolveLater.isChecked();
            String subject = spinnerSubject.getSelectedItem().toString();

            // Geçici uyarı
            Toast.makeText(getContext(), "Soru gönderiliyor...", Toast.LENGTH_SHORT).show();

            // Bundle ile Chat ekranına veri gönder
            Bundle bundle = new Bundle();
            bundle.putString("subject", subject);
            bundle.putString("questionText", questionText);
            bundle.putBoolean("newType", isNewType);
            bundle.putBoolean("solveLater", solveLater);
            if (selectedImageUri != null) {
                bundle.putString("imageUri", selectedImageUri.toString());
            }
            Navigation.findNavController(view).navigate(R.id.navigation_chat, bundle);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);
                imagePreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

