package com.example.fingermastergame.ui.home;

import androidx.lifecycle.ViewModel;

import com.example.fingermastergame.ui.playerData.PlayerDataModel;

public class HomeViewModel extends ViewModel {

     private final PlayerDataModel livePlayerDataModel = new PlayerDataModel();




    public PlayerDataModel getLivePlayerData() {
        return livePlayerDataModel;
    }
}