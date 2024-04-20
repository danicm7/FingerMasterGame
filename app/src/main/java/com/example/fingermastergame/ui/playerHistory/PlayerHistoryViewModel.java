package com.example.fingermastergame.ui.playerHistory;

import androidx.lifecycle.ViewModel;

import com.example.fingermastergame.ui.playerData.PlayerModel;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerHistoryViewModel extends ViewModel {
    ArrayList<PlayerModel> playerModels;
    protected PlayerHistoryViewModel(ArrayList<PlayerModel> playerModels) {
        this.playerModels = playerModels;
    }
    private PlayerHistoryViewModel(){
    }
    protected ArrayList<PlayerModel> getPlayersNames() {
        return playerModels;
    }
    protected PlayerModel getPlayerByName(String name){
        final Iterator<PlayerModel> it = playerModels.iterator();
        while (it.hasNext()){
            final PlayerModel player = it.next();
            if (player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }
    protected String getIssuesByName(String name){
        final Iterator<PlayerModel> it = playerModels.iterator();
        String issues = "";
        while (it.hasNext()){
            final PlayerModel player = it.next();
            if (player.getName().equals(name)){
                for(int i = 0; i < player.getIssue().size(); i++){
                    issues = issues + "\n- " + player.getIssue().get(i).getDescription();
                }
            }
        }
        return issues;
    }
}