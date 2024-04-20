package com.example.fingermastergame.ui.newPlayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayerModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class NewPlayerActivityController extends AppCompatActivity {

    private ImageButton saveButton;
    private TextView playerNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bindViews();
        configure();
        configureClicklisteners();
    }

    private void configure() {
        playerNameTextView.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
    }

    private void bindViews() {
        this.saveButton = findViewById(R.id.new_player_save_button);
        this.playerNameTextView = findViewById(R.id.manage_fingers_issue_textview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureClicklisteners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = String.valueOf(playerNameTextView.getText()).replaceAll(" ","");
                if(!checkNewPlayer(name)){
                    return;
                }

                final int fingers = getResources().getInteger(R.integer.initial_fingers);
                final PlayerModel playerModel = new PlayerModel(name, fingers);
                saveNewPlayerData(playerModel);
                finish();
            }
        });
    }
    public void saveNewPlayerData(PlayerModel playerModel){
        ArrayList<PlayerModel> arrayList = loadPlayers();
        arrayList.add(playerModel);
        final PlayerModel[] list = ManageFingersUtils.arrayListToArray(arrayList);
        final Gson gson = new Gson();
        final String json = gson.toJson(list);
        final String playerDataFileName = getResources().getString(R.string.player_data_file_name);

        try{
            FileOutputStream fos = openFileOutput(playerDataFileName, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    private boolean checkNewPlayer(String name) {
        final ArrayList<PlayerModel> allPlayers = loadPlayers();
        if(name.isEmpty() || name.isBlank()){
            Toast.makeText(this, getBaseContext().getString(R.string.error_empty_name), Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i = 0; i < allPlayers.size(); i++){
            if (Objects.equals(allPlayers.get(i).getName(), name)){
                Toast.makeText(this, getBaseContext().getString(R.string.error_existing_name), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}