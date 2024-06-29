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
import com.example.fingermastergame.storage.ManageStorage;
import com.example.fingermastergame.ui.home.homeFragmentRecyclerView.HomeFragmentRecyclerAdapter;
import com.example.fingermastergame.ui.playerData.PlayerModel;
import com.example.fingermastergame.ui.playerData.PlayersListModel;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
private PlayersListModel playersListModel;
private RecyclerView recyclerView;
private HomeFragmentRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.playersListModel = new ViewModelProvider(this).get(PlayersListModel.class);
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        final View root = binding.getRoot();
        configure();
        configureRecyclerView();
        return root;
    }

    private void configure() {
        playersListModel = new PlayersListModel(loadPlayers());
    }

    private void configureRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView = binding.fragmentHomeRecyclerview;
        this.recyclerView.setLayoutManager(layoutManager);
        this.adapter = new HomeFragmentRecyclerAdapter(playersListModel.getAllPlayers());
        this.recyclerView.setAdapter(this.adapter);

    }

    private ArrayList<PlayerModel> loadPlayers() {
        final ArrayList<PlayerModel> list;
        list = ManageStorage.loadPlayers(getContext().getApplicationContext());
        return list;
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
        configure();
        configureRecyclerView();
    }
}