package com.example.fingermastergame.ui.playerHistory;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fingermastergame.R;
import com.example.fingermastergame.databinding.FragmentPlayerHistoryBinding;
import com.example.fingermastergame.ui.playerHistory.playerHistoryRecyclerView.PlayerHistoryRecyclerAdapter;
import com.example.fingermastergame.ui.storage.ManageStorage;

import java.util.ArrayList;

public class PlayerHistoryFragment extends Fragment {
//FragmentGalleryBinding
private FragmentPlayerHistoryBinding binding;
private static Context c;
private ArrayList<String> names = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        PlayerHistoryViewModel playerHistoryViewModel =
                new ViewModelProvider(this).get(PlayerHistoryViewModel.class);

        binding = FragmentPlayerHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        c = super.getContext();
        names = new PlayerHistoryViewModel().getPlayersNames(c);
        binding.playerHistoryRecyclerView.setAdapter(new PlayerHistoryRecyclerAdapter(names));

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}