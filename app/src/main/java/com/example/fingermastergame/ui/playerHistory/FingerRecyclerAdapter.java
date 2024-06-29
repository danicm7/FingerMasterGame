package com.example.fingermastergame.ui.playerHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayersListModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import java.util.ArrayList;
import java.util.Objects;

public class FingerRecyclerAdapter extends RecyclerView.Adapter<FingerRecyclerAdapter.ViewHolder> {

    private PlayersListModel playersListModel;
    private ArrayList<String> issues;
    private String playerName;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.name_view_holder_players_list);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    public FingerRecyclerAdapter(PlayersListModel playersListModel, String playerName) {
        this.playersListModel = playersListModel;
        this.playerName = playerName;
        configure();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_general_view_holder, viewGroup, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText("- " + issues.get(position));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int margins = (int) viewHolder.getTextView().getContext().getResources().getDimension(R.dimen.player_issues_view_holder_custom_margins);
        params.setMargins(margins,0, margins,0);
        viewHolder.getTextView().setLayoutParams(params);
    }
    @Override
    public int getItemCount() {
        return issues.size();
    }
    private void configure(){
        final String issues  = this.playersListModel.getIssuesByName(playerName);
        final String[] issuesArray =
                !Objects.equals(issues, "") ? issues.split("\n") : new String[0];
        this.issues = (ArrayList<String>) ManageFingersUtils.arrayToArrayList(issuesArray);
    }
}
