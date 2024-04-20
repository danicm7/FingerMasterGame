package com.example.fingermastergame.ui.playerData;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerModel implements Serializable {
    private int fingers = -1;
    private String name = "";
    private ArrayList<IssueModel> issueModel = new ArrayList<IssueModel>();

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

    public ArrayList<IssueModel> getIssue() {
        return issueModel;
    }

    public void setIssue(IssueModel issueModel) {
        this.issueModel.add(issueModel);
    }
}
