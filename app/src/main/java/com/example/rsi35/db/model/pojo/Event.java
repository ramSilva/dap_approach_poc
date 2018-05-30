package com.example.rsi35.db.model.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "eventJson")
    private String eventJson;

    public int getUid() {
        return uid;
    }

    public void setUid(final int uid) {
        this.uid = uid;
    }

    public String getEventJson() {
        return eventJson;
    }

    public void setEventJson(final String eventJson) {
        this.eventJson = eventJson;
    }
}
