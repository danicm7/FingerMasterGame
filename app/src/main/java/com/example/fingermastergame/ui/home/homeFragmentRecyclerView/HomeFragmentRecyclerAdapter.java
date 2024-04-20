package com.example.fingermastergame.ui.home.homeFragmentRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayerModel;

import java.util.ArrayList;

public class HomeFragmentRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<PlayerModel> players;

    public HomeFragmentRecyclerAdapter(ArrayList<PlayerModel> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_view_holder, parent, false);
        return new HomeFragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final PlayerModel playerModel = players.get(position);
        final HomeFragmentViewHolder homeFragmentViewHolder = ((HomeFragmentViewHolder) holder);
        homeFragmentViewHolder.getTextView().setText(playerModel.getName());
        homeFragmentViewHolder.setPlayerDataModel(playerModel);
        paintChecksBoxes(homeFragmentViewHolder, playerModel);
    }



    @Override
    public int getItemCount() {
        if (players != null){
            return players.size();
        }else{
            return 0;
        }
    }

    private void paintChecksBoxes(HomeFragmentViewHolder homeFragmentViewHolder, PlayerModel playerModel) {
        int fingers = playerModel.getFingers();
        final int checkBoxesLenght = homeFragmentViewHolder.getCheckBoxes().length;
        final ImageView[] checkBoxes = homeFragmentViewHolder.getCheckBoxes();
        for (int cont=0; cont < checkBoxesLenght;cont++){
            if(fingers > 0){
                checkBoxes[cont].setImageResource(R.drawable.check_state_ok);
                fingers--;
            } else {
                checkBoxes[cont].setImageResource(R.drawable.check_state_nok);
                fingers--;
            }
        }
    }
}
