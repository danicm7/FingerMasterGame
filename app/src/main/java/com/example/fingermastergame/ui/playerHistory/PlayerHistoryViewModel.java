package com.example.fingermastergame.ui.playerHistory;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.fingermastergame.navigationDrawerMenu;
import com.example.fingermastergame.ui.playerData.IssueModel;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;
import com.example.fingermastergame.ui.storage.ManageStorage;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerHistoryViewModel extends ViewModel {
    private ArrayList<PlayerDataModel> getAllPlayersData(Context c){
        return new ManageStorage(c).getAllPlayers();
    }
    protected ArrayList<String> getPlayersNames(Context c) {
        final ArrayList<PlayerDataModel> n = this.getAllPlayersData(c);
        ArrayList<String> names = new ArrayList<String>();
        final Iterator<PlayerDataModel> it = n.iterator();

        while (it.hasNext()) {
            names.add(it.next().getName());
        }
        return names;
    }
    public String getIssuesByName(String name, Context c){
        final ArrayList<PlayerDataModel> n = this.getAllPlayersData(c);
        final Iterator<PlayerDataModel> it = n.iterator();
        String issues = "";

        while (it.hasNext()){
            final PlayerDataModel player = it.next();
            if (player.getName().equals(name)){
                for(int i = 0; i < player.getIssue().size(); i++){
                    issues = issues + "\n- " + player.getIssue().get(i).getDescription();
                }
            }
        }
        return issues;
    }

    protected PlayerDataModel getPlayerByName(String name){
        final Context c = new navigationDrawerMenu().getContext();
        final ArrayList<PlayerDataModel> n = new ManageStorage(c).getAllPlayers();
        final Iterator<PlayerDataModel> it = n.iterator();

        while (it.hasNext()){
            final PlayerDataModel player = it.next();
            if (player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }
}