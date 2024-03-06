package com.example.fingermastergame.ui.newPlayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.IssueModel;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;
import com.example.fingermastergame.ui.storage.ManageStorage;

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
                final PlayerDataModel playerDataModel = new PlayerDataModel(name, fingers);
                final ManageStorage manageStorage = new ManageStorage(getApplicationContext());
                manageStorage.saveNewPlayerData(playerDataModel);
                finish();
            }
        });
    }

    private boolean checkNewPlayer(String name) {
        final ManageStorage manageStorage = new ManageStorage(getApplicationContext());
        final ArrayList<PlayerDataModel> allPlayers = manageStorage.getAllPlayers();
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