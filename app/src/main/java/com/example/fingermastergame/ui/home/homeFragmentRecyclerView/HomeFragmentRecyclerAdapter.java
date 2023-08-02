package com.example.fingermastergame.ui.home.homeFragmentRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;
import java.util.ArrayList;

public class HomeFragmentRecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<PlayerDataModel> players;

    public HomeFragmentRecyclerAdapter(ArrayList<PlayerDataModel> players) {
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
        final PlayerDataModel playerDataModel = players.get(position);
        final HomeFragmentViewHolder homeFragmentViewHolder = ((HomeFragmentViewHolder) holder);
        homeFragmentViewHolder.getTextView().setText(playerDataModel.getName());
        homeFragmentViewHolder.setPlayerDataModel(playerDataModel);
        paintChecksBoxes(homeFragmentViewHolder, playerDataModel);
    }



    @Override
    public int getItemCount() {
        if (players != null){
            return players.size();
        }else{
            return 0;
        }
    }

    private void paintChecksBoxes(HomeFragmentViewHolder homeFragmentViewHolder, PlayerDataModel playerDataModel) {
        int fingers = playerDataModel.getFingers();
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
