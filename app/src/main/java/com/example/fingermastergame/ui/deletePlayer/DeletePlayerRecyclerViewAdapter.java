package com.example.fingermastergame.ui.deletePlayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerModels.PlayersListModel;
import com.example.fingermastergame.ui.utils.PlayersListRecyclerViewGeneralAdapter;

public class DeletePlayerRecyclerViewAdapter extends PlayersListRecyclerViewGeneralAdapter {

    public DeletePlayerRecyclerViewAdapter(PlayersListModel playersListModel) {
        super(playersListModel);
    }

    @NonNull
    @Override
    public DeletePlayerViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_delete_player_view_holder, parent, false);
        return new DeletePlayerViewHolderList(view);
    }

    public static class DeletePlayerViewHolderList extends PlayerListGeneralViewHolder {

        public DeletePlayerViewHolderList(View view) {
            super(view);
            super.textView = view.findViewById(R.id.custom_delete_player_view_holder_player_name);
            configure();
        }

        @Override
        protected void configure()  {
            if (super.textView == null){
                return;
            }
            super.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("testttt");
                }
            });
        }

        public TextView getNameTextView() {
            return textView;
        }
    }
}
