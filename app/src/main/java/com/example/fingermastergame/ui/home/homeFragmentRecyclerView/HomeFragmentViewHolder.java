package com.example.fingermastergame.ui.home.homeFragmentRecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.createIssue.CreateIssueController;
import com.example.fingermastergame.ui.playerData.PlayerModel;

public  class HomeFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View itemView;
    private TextView textView;
    private ImageView[] checkBoxes;
    private PlayerModel playerModel;

    public HomeFragmentViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.itemView.setOnClickListener(this::onClick);
        this.textView = itemView.findViewById(R.id.name_view_holder_home_fragment);
        this.checkBoxes = new ImageView[] {itemView.findViewById(R.id.checkBox_state_0),
                itemView.findViewById(R.id.checkBox_state_1)
                ,itemView.findViewById(R.id.checkBox_state_2)
                ,itemView.findViewById(R.id.checkBox_state_3)
                ,itemView.findViewById(R.id.checkBox_state_4)};

    }

    public TextView getTextView() {
        return textView;
    }

    public ImageView[] getCheckBoxes() {
        return checkBoxes;
    }

    public void setPlayerDataModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public PlayerModel getPlayerDataModel() {
        return playerModel;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(itemView.getContext(), CreateIssueController.class);
        i.putExtra(itemView.getContext().getString(R.string.player_data_model), getPlayerDataModel());
        itemView.getContext().startActivity(i);
    }
}
