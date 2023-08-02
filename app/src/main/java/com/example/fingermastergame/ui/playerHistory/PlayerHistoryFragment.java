package com.example.fingermastergame.ui.playerHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.fingermastergame.databinding.FragmentPlayerHistoryBinding;

public class PlayerHistoryFragment extends Fragment {
//FragmentGalleryBinding
private FragmentPlayerHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        PlayerHistoryViewModel playerHistoryViewModel =
                new ViewModelProvider(this).get(PlayerHistoryViewModel.class);

    binding = FragmentPlayerHistoryBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        playerHistoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}