package com.example.fingermastergame.ui.home;

import androidx.lifecycle.ViewModel;

import com.example.fingermastergame.ui.playerData.PlayerModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final ArrayList<PlayerModel> livePlayerModel;

    private HomeViewModel() {
        livePlayerModel = new ArrayList<PlayerModel>();
    }

    public HomeViewModel(ArrayList<PlayerModel> livePlayerModel) {
        this.livePlayerModel = livePlayerModel;
    }

    public ArrayList<PlayerModel> getAllPlayers() {
        return livePlayerModel;
    }

}