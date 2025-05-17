package com.yusufyilmaz00.edulai.ui.mentor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusufyilmaz00.edulai.R;
import androidx.fragment.app.Fragment;

public class MentorFragment extends Fragment {

    public MentorFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mentor, container, false);
    }
}




