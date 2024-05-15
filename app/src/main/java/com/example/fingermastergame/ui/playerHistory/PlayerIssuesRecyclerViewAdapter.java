package com.example.fingermastergame.ui.playerHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerModels.PlayersListModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import com.example.fingermastergame.ui.utils.PlayersListRecyclerViewGeneralAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerIssuesRecyclerViewAdapter extends PlayersListRecyclerViewGeneralAdapter {

    private PlayersListModel playersListModel;
    private ArrayList<String> issues;
    private String playerName;

    public class PlayerIssuesViewHolder extends PlayerListGeneralViewHolder {

        public PlayerIssuesViewHolder(View view) {
            super(view);
        }

    }
    public PlayerIssuesRecyclerViewAdapter(PlayersListModel playersListModel, String playerName) {
        super(playersListModel);
        this.playersListModel = playersListModel;
        this.playerName = playerName;
        configure();
    }

    @Override
    public PlayerIssuesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_general_view_holder, viewGroup, false);

        return new PlayerIssuesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(PlayerListGeneralViewHolder viewHolder, final int position) {
        viewHolder.getNameTextView().setText("- " + issues.get(position));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int margins = (int) viewHolder.getNameTextView().getContext().getResources().getDimension(R.dimen.player_issues_view_holder_custom_margins);
        params.setMargins(margins,0, margins,0);
        viewHolder.getNameTextView().setLayoutParams(params);
    }
    @Override
    public int getItemCount() {
        return issues.size();
    }
    private void configure(){
        final String issues  = this.playersListModel.getIssuesByName(playerName);
        final String[] issuesArray =
                !Objects.equals(issues, "") ? issues.split("\n") : new String[0];
        this.issues = ManageFingersUtils.arrayToArrayList(issuesArray);
    }
}
