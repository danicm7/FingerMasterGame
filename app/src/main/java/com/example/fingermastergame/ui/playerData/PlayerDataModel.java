package com.example.fingermastergame.ui.playerData;

import java.io.Serializable;

public class PlayerDataModel implements Serializable {
    private int fingers = 5;
    private String name = "";
    private IssueModel issueModel = new IssueModel("");

    public PlayerDataModel(int fingers, String name, IssueModel issueModel) {
        this.fingers = fingers;
        this.name = name;
        this.issueModel = issueModel;
    }

    public PlayerDataModel() {
    }

    public int getFingers() {
        return fingers;
    }

    public void setFingers(int fingers) {
        this.fingers = fingers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IssueModel getIssue() {
        return issueModel;
    }

    public void setIssue(IssueModel issueModel) {
        this.issueModel = issueModel;
    }
}
