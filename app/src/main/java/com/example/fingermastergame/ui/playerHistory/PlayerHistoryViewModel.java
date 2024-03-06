package com.example.fingermastergame.ui.playerHistory;

import android.content.Context;
import android.icu.text.Edits;

import androidx.lifecycle.ViewModel;
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
}