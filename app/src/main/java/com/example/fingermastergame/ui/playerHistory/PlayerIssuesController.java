package com.example.fingermastergame.ui.playerHistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fingermastergame.R;
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

public class PlayerIssuesController extends AppCompatActivity {

    private TextView textView;
    private String name;
    private PlayerHistoryViewModel playerHistoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_history_resume);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        bindViews();
        configure();
    }

    private void bindViews() {
        textView = findViewById(R.id.player_history_list_view);
    }

    private void configure() {
        this.playerHistoryViewModel = new PlayerHistoryViewModel(loadPlayers());
        final String issues = playerHistoryViewModel.getIssuesByName(name);
        textView.setText(issues);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private ArrayList<PlayerModel> loadPlayers() {
        ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
        final File file = new File(getFilesDir(), getResources().getString(R.string.player_data_file_name));
        final Gson gson = new Gson();
        if (!file.exists()){
            return playersList;
        }
        try {
            final FileInputStream fis = openFileInput(file.getName());
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