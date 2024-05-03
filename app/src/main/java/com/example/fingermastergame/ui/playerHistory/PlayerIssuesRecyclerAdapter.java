package com.example.fingermastergame.ui.playerHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import java.util.ArrayList;

public class PlayerIssuesRecyclerAdapter extends RecyclerView.Adapter<PlayerIssuesRecyclerAdapter.ViewHolder> {

    private PlayersListViewModel playersListViewModel;
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
    public PlayerIssuesRecyclerAdapter(PlayersListViewModel playersListViewModel, String playerName) {
        this.playersListViewModel = playersListViewModel;
        this.playerName = playerName;
        configure();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.player_history_view_holder, viewGroup, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(issues.get(position));
    }
    @Override
    public int getItemCount() {
        return issues.size();
    }
    private void configure(){
        final String issues  = this.playersListViewModel.getIssuesByName(playerName);
        final String[] issuesArray = issues.split("\n");
        this.issues = ManageFingersUtils.arrayToArrayList(issuesArray);
    }
}
