package com.yusufyilmaz00.edulai.ui.solution;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.yusufyilmaz00.edulai.R;

public class SolutionFragment extends Fragment {

    public SolutionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solution, container, false);

        Button backButton = view.findViewById(R.id.button_back_home);
        backButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(SolutionFragment.this).popBackStack();
        });

        return view;
    }
}
