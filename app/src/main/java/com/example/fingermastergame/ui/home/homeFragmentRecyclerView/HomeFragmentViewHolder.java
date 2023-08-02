package com.example.fingermastergame.ui.home.homeFragmentRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.manageFingers.ManagePlayersFingers;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;

public  class HomeFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View itemView;
    private TextView textView;
    private ImageView[] checkBoxes;
    private PlayerDataModel playerDataModel;

    public HomeFragmentViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.itemView.setOnClickListener(this::onClick);
        this.textView = itemView.findViewById(R.id.text_view_view_holder);
        this.checkBoxes = new ImageView[] {itemView.findViewById(R.id.checkBox_state_0),
                itemView.findViewById(R.id.checkBox_state_1)
                ,itemView.findViewById(R.id.checkBox_state_2)
                ,itemView.findViewById(R.id.checkBox_state_3)
                ,itemView.findViewById(R.id.checkBox_state_4)
                ,itemView.findViewById(R.id.checkBox_state_5)
                ,itemView.findViewById(R.id.checkBox_state_6)
                ,itemView.findViewById(R.id.checkBox_state_7)
                ,itemView.findViewById(R.id.checkBox_state_8)
                ,itemView.findViewById(R.id.checkBox_state_9)};

    }

    public TextView getTextView() {
        return textView;
    }

    public ImageView[] getCheckBoxes() {
        return checkBoxes;
    }

    public void setPlayerDataModel(PlayerDataModel playerDataModel) {
        this.playerDataModel = playerDataModel;
    }

    public PlayerDataModel getPlayerDataModel() {
        return playerDataModel;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(itemView.getContext(), ManagePlayersFingers.class);
        i.putExtra(itemView.getContext().getString(R.string.player_data_model), getPlayerDataModel());
        itemView.getContext().startActivity(i);
    }
}
