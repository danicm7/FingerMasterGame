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
import com.example.fingermastergame.R;
import com.example.fingermastergame.databinding.FragmentHomeBinding;
import com.example.fingermastergame.ui.home.homeFragmentRecyclerView.HomeFragmentRecyclerAdapter;
import com.example.fingermastergame.ui.playerData.PlayerModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
        configure();
        configureRecyclerView();
        return root;
    }

    private void configure() {
        homeViewModel = new HomeViewModel(loadPlayers());
    }

    private void configureRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView = binding.fragmentHomeRecyclerview;
        this.recyclerView.setLayoutManager(layoutManager);
        this.adapter = new HomeFragmentRecyclerAdapter(homeViewModel.getAllPlayers());
        this.recyclerView.setAdapter(this.adapter);

    }

    private ArrayList<PlayerModel> loadPlayers() {
        ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
        final File file = new File(getContext().getFilesDir(), getContext().getResources().getString(R.string.player_data_file_name));
        final Gson gson = new Gson();
        if (!file.exists()){
            return playersList;
        }
        try {
            final FileInputStream fis = getContext().openFileInput(file.getName());
            final InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            final StringBuilder stringBuilder = new StringBuilder();
            final BufferedReader reader = new BufferedReader(inputStreamReader);
            final ManageFingersUtils utils =  ManageFingersUtils.getInstance();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            final String contents = stringBuilder.toString();
            final PlayerModel[] data = gson.fromJson(contents, PlayerModel[].class);

            fis.close();
            inputStreamReader.close();
            reader.close();
            playersList = utils.arrayToArrayList(data);
            return playersList;
        } catch (IOException e) {
            e.printStackTrace();
            return playersList;
        }
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