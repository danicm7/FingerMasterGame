package com.example.fingermastergame.ui.playerHistory;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayerModel;

import java.util.ArrayList;

public class PlayersListRecyclerAdapter extends RecyclerView.Adapter<PlayersListRecyclerAdapter.PlayerHistoryViewHolder>{
    private final ArrayList<PlayerModel> players;

    public PlayersListRecyclerAdapter(ArrayList<PlayerModel> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_history_view_holder, parent, false);
        return new PlayerHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHistoryViewHolder holder, int position) {
        final String name = players.get(position).getName();
        holder.getTextView().setText(name);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class PlayerHistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public PlayerHistoryViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.name_view_holder_players_list);
            configure();
        }

        private void configure() {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(), PlayerIssuesController.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("name", getTextView().getText());
                    view.getContext().startActivity(myIntent);
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
