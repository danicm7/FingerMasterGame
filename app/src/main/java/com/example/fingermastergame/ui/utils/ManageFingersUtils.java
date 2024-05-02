package com.example.fingermastergame.ui.utils;

import com.example.fingermastergame.ui.playerData.PlayerModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageFingersUtils {
    private static ManageFingersUtils instance;

    private ManageFingersUtils() {
    }
    public static ManageFingersUtils getInstance() {
        if (instance == null) {
            instance = new ManageFingersUtils();
        }
        return instance;
    }
    public static PlayerModel[] arrayListToArray(ArrayList<PlayerModel> playerModelArrayList){
        final int size= playerModelArrayList.size();
        return playerModelArrayList.toArray(new PlayerModel[size]);
    }

    public static ArrayList<PlayerModel> arrayToArrayList (PlayerModel[] array){
        return new ArrayList<PlayerModel>(Arrays.asList(array));
    }
    public static ArrayList<String> arrayToArrayList (String[] array){
        return new ArrayList<String>(Arrays.asList(array));
    }
}
