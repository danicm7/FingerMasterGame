package com.example.fingermastergame.ui.playerHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.fingermastergame.R;
import com.example.fingermastergame.databinding.FragmentPlayersListBinding;
import com.example.fingermastergame.storage.ManageStorage;
import com.example.fingermastergame.ui.playerData.PlayerModel;
import com.example.fingermastergame.ui.playerData.PlayersListModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PlayersListFragment extends Fragment {
private FragmentPlayersListBinding binding;
private PlayersListModel playersListModel;
private ArrayList<PlayerModel> names = new ArrayList<PlayerModel>();

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        playersListModel = new ViewModelProvider(this).get(PlayersListModel.class);
        binding = FragmentPlayersListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        configure();
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
    private void configure(){
        this.playersListModel = new PlayersListModel(loadPlayers());
        this.names = playersListModel.getPlayersNames();
        this.binding.playersListRecyclerView.setAdapter(new PlayersListRecyclerAdapter(names));

    }

    private ArrayList<PlayerModel> loadPlayers() {
        final ArrayList<PlayerModel> list;
        list = ManageStorage.loadPlayers(getContext().getApplicationContext());
        return list;
    }
}