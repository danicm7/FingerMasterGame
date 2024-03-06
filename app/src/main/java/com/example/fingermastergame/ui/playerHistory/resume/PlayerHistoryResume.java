package com.example.fingermastergame.ui.playerHistory.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fingermastergame.R;
import com.example.fingermastergame.navigationDrawerMenu;
import com.example.fingermastergame.ui.playerData.IssueModel;
import com.example.fingermastergame.ui.playerHistory.PlayerHistoryViewModel;
import com.example.fingermastergame.ui.storage.ManageStorage;

import java.util.ArrayList;

public class PlayerHistoryResume extends AppCompatActivity {

    private TextView textView;
    private String name;
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

        final Context c = new navigationDrawerMenu().getContext();
        final PlayerHistoryViewModel playerHistoryViewModel = new PlayerHistoryViewModel();
        final ArrayList<IssueModel> issues = playerHistoryViewModel.getIssuesByName(name, c);
        String print = "";
        for (int i = 0; issues.size() > i; i++){
            print = print + "\n\n-" + issues.get(i).getDescription();
        }

        textView.setText(print);
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
}