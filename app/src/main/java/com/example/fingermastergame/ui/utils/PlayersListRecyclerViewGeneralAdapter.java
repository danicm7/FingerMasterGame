package com.example.fingermastergame.ui.utils;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerHistory.PlayerIssuesActivityController;
import com.example.fingermastergame.ui.playerModels.PlayerModel;
import com.example.fingermastergame.ui.playerModels.PlayersListModel;
import java.util.ArrayList;

public class PlayersListRecyclerViewGeneralAdapter extends RecyclerView.Adapter<PlayersListRecyclerViewGeneralAdapter.PlayerListGeneralViewHolder>{
    private final ArrayList<PlayerModel> players;

    public PlayersListRecyclerViewGeneralAdapter(PlayersListModel playersListModel) {
        this.players = playersListModel.getAllPlayers();
    }

    @NonNull
    @Override
    public PlayerListGeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_general_view_holder, parent, false);
        return new PlayerListGeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListGeneralViewHolder holder, int position) {
        final String name = players.get(position).getName();
        holder.getNameTextView().setText(name);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class PlayerListGeneralViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        public PlayerListGeneralViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.custom_general_view_holder_player_name);
            configure();
        }

        protected void configure() {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(), PlayerIssuesActivityController.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("name", getNameTextView().getText());
                    view.getContext().startActivity(myIntent);
                }
            });
        }

        public TextView getNameTextView() {
            return textView;
        }
    }

}
