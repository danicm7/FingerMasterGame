package com.example.fingermastergame.ui.playerHistory.playerHistoryRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import java.util.ArrayList;

public class PlayerHistoryRecyclerAdapter extends RecyclerView.Adapter<PlayerHistoryViewHolder>{
    private final ArrayList<String> players;

    public PlayerHistoryRecyclerAdapter(ArrayList<String> players) {
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
        final String name = players.get(position);
        holder.getTextView().setText(name);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }



}
