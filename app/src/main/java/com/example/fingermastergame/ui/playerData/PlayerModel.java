package com.example.fingermastergame.ui.playerData;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerModel implements Serializable {
    private int fingers = -1;
    private String name = "";
    private ArrayList<FingerModel> fingerModel = new ArrayList<FingerModel>();

    public PlayerModel(String name, int fingers) {
        this.name = name;
        this.fingers = fingers;
    }

    public PlayerModel() {
    }

    public int getFingers() {
        return fingers-getIssue().size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FingerModel> getIssue() {
        return fingerModel;
    }

    public void setIssue(FingerModel fingerModel) {
        this.fingerModel.add(fingerModel);
    }
}
