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
        ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
        final File file = new File(getContext().getFilesDir(), getResources().getString(R.string.player_data_file_name));
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
}