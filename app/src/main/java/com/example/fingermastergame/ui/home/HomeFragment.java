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
import com.example.fingermastergame.ui.playerData.IssueModel;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;

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
        return loadTestdata();
    }

    private ArrayList loadTestdata() {
        ArrayList data = new ArrayList<PlayerDataModel>();
        PlayerDataModel playerDataModel = new PlayerDataModel(3,"Laura", new IssueModel("issue description"));
        PlayerDataModel playerDataModel2 = new PlayerDataModel(2,"Jose", new IssueModel("issue description"));
        PlayerDataModel playerDataModel3 = new PlayerDataModel(4,"Dani", new IssueModel("issue description"));
        PlayerDataModel playerDataModel4 = new PlayerDataModel(5,"Adam", new IssueModel("issue description"));
        PlayerDataModel playerDataModel5 = new PlayerDataModel(1,"Oscar", new IssueModel("issue description"));
        PlayerDataModel playerDataModel6 = new PlayerDataModel(3,"Maria", new IssueModel("issue description"));
        PlayerDataModel playerDataModel7 = new PlayerDataModel(1,"Jorge", new IssueModel("issue description"));
        PlayerDataModel playerDataModel8 = new PlayerDataModel(5,"Judith", new IssueModel("issue description"));

        data.add(playerDataModel);
        data.add(playerDataModel2);
        data.add(playerDataModel3);
        data.add(playerDataModel4);
        data.add(playerDataModel5);
        data.add(playerDataModel6);
        data.add(playerDataModel7);
        data.add(playerDataModel8);

        return data;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}