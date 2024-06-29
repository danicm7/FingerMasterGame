package com.example.fingermastergame.ui.playerHistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.fingermastergame.R;
import com.example.fingermastergame.storage.ManageStorage;
import com.example.fingermastergame.ui.playerData.PlayerModel;
import com.example.fingermastergame.ui.playerData.PlayersListModel;
import java.util.ArrayList;

public class PlayerIssuesController extends AppCompatActivity {

    private String name;
    private PlayersListModel playersListModel;
    private RecyclerView recyclerView;
    private PlayerIssuesRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_issues_resume);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        bindViews();
        configure();
    }

    private void bindViews() {
        this.recyclerView = findViewById(R.id.player_issues_recycler_view);
    }

    private void configure() {
        this.playersListModel = new PlayersListModel(loadPlayers());
        this.adapter = new PlayerIssuesRecyclerAdapter(playersListModel,name);
        recyclerView.setAdapter(adapter);
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
        final ArrayList<PlayerModel> list;
        list = ManageStorage.loadPlayers(getApplicationContext());
        return list;
    }
}