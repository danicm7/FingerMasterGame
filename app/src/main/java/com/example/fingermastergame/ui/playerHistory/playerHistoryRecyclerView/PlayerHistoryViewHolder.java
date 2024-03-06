package com.example.fingermastergame.ui.playerHistory.playerHistoryRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.navigationDrawerMenu;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;
import com.example.fingermastergame.ui.playerHistory.resume.PlayerHistoryResume;


public class PlayerHistoryViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;
    public PlayerHistoryViewHolder(View view) {
        super(view);
        textView = (TextView) view.findViewById(R.id.name_view_holder_player_history);
        configure();
    }

    private void configure() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context c =  new navigationDrawerMenu().getContext();
                Intent myIntent = new Intent(c, PlayerHistoryResume.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("name", getTextView().getText());
                c.startActivity(myIntent);
            }
        });
    }

    public TextView getTextView() {
        return textView;
    }
}

