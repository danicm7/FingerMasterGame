package com.example.fingermastergame.ui.playerData;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class PlayersListModel extends ViewModel {
    private ArrayList<PlayerModel> playerModels;
    public PlayersListModel(ArrayList<PlayerModel> playerModels) {
        this.playerModels = playerModels;
        this.playerModels = sortByName(playerModels);

    }
    private PlayersListModel(){
    }
    public ArrayList<PlayerModel> getPlayersNames() {
        return playerModels;
    }
    public PlayerModel getPlayerByName(String name){
        final Iterator<PlayerModel> it = playerModels.iterator();
        while (it.hasNext()){
            final PlayerModel player = it.next();
            if (player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }
    public String getIssuesByName(String name){
        final Iterator<PlayerModel> it = playerModels.iterator();
        String issues = "";
        while (it.hasNext()){
            final PlayerModel player = it.next();
            if (player.getName().equals(name)){
                for(int i = 0; i < player.getIssue().size(); i++){
                    issues = issues + player.getIssue().get(i).getDescription() + "\n";
                }
            }
        }
        return issues;
    }
    public int getSize(){
        return playerModels.size();
    }
    private ArrayList<PlayerModel> sortByName(ArrayList<PlayerModel> playerModels){
        playerModels.sort(Comparator.comparing(PlayerModel::getName));
        return playerModels;
        /*Another way to sort*/
       /* Collections.sort(playerModels, new Comparator<PlayerModel>() {
            @Override
            public int compare(PlayerModel lhs, PlayerModel rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getIssue().size() < rhs.getIssue().size() ? -1 : (lhs.getIssue().size() > rhs.getIssue().size()) ? 1 : 0;
            }
        });
        return playerModels*/
    }
    public ArrayList<PlayerModel> getAllPlayers() {
        return this.playerModels;
    }
}