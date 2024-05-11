package com.example.fingermastergame.ui.deletePlayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fingermastergame.R;
import com.example.fingermastergame.databinding.FragmentDeletePlayerBinding;
import com.example.fingermastergame.ui.playerModels.PlayerModel;
import com.example.fingermastergame.ui.utils.PlayersListRecyclerViewGeneralAdapter;
import com.example.fingermastergame.ui.playerModels.PlayersListModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DeletePlayerFragment extends Fragment {
    private FragmentDeletePlayerBinding binding;
    private static PlayersListRecyclerViewGeneralAdapter adapter;
    private static PlayersListModel playersListModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeletePlayerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        configure();
        return root;
    }


    private void configure() {
        playersListModel = new PlayersListModel(loadPlayers());
        adapter = new PlayersListRecyclerViewGeneralAdapter(playersListModel);
        this.binding.deletePlayerRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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

