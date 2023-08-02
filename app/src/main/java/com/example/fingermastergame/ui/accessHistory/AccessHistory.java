package com.example.fingermastergame.ui.accessHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.fingermastergame.databinding.FragmentAccessHistoryBinding;

public class AccessHistory extends Fragment {

private FragmentAccessHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        AccesHistoryViewModel accesHistoryViewModel =
                new ViewModelProvider(this).get(AccesHistoryViewModel.class);

    binding = FragmentAccessHistoryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        accesHistoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}