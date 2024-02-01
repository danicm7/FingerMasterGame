package com.example.fingermastergame.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.databinding.FragmentHomeBinding;
import com.example.fingermastergame.ui.home.homeFragmentRecyclerView.HomeFragmentRecyclerAdapter;
import com.example.fingermastergame.ui.storage.ManageStorage;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
private HomeViewModel homeViewModel;
private RecyclerView recyclerView;
private HomeFragmentRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        configureRecyclerView();
        return root;
    }

    private void configureRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView = binding.fragmentHomeRecyclerview;
        this.recyclerView.setLayoutManager(layoutManager);
        this.adapter = new HomeFragmentRecyclerAdapter(loadData());
        this.recyclerView.setAdapter(this.adapter);

    }

    private ArrayList loadData() {
        return new ManageStorage(getContext()).getAllPlayers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }
    private void refreshData(){
        configureRecyclerView();
    }
}