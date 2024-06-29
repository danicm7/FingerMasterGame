package com.example.fingermastergame.ui.playerData;

import java.io.Serializable;
import java.util.Date;

public class FingerModel implements Serializable {

    private String description = "";
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FingerModel(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
}
