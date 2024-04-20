package com.example.fingermastergame.ui.playerData;

import java.io.Serializable;
import java.util.Date;

public class IssueModel  implements Serializable {
    private String description = "";

    public IssueModel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
