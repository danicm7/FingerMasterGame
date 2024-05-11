package com.example.fingermastergame.ui.playerModels;

import java.io.Serializable;

public class IssueModel  implements Serializable {
    private String description = "";

    public IssueModel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
