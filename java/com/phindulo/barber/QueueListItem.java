package com.phindulo.barber;

import com.google.firebase.Timestamp;

public class QueueListItem {
    private String name;
    private String position;
    private Timestamp timestamp;

    public QueueListItem(String name, String position, Timestamp timestamp) {
        this.name = name;
        this.position = position;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
