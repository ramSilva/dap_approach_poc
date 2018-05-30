package com.example.rsi35.db.dbstuff;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.rsi35.db.model.pojo.Event;

import java.util.List;

//This is used to guarantee a single instance of AppDatabse (as per instructed by google)
//It could also be achieved with dagger (and it should be done that way)
public class DatabaseConnector {
    private static DatabaseConnector instance;

    private AppDatabase db;

    private DatabaseConnector(AppDatabase db) {
        this.db = db;
    }

    public static void create(Context context) {
        if (instance == null) {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "events-db").build();
            instance = new DatabaseConnector(db);
        }
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CreateDatabaseConnector must be called before getInstance");
        }
        return instance;
    }

    public List<Event> getAllEvents() {
        return db.eventDao().getAll();
    }

    public void insertAll(Event... events) {
        db.eventDao().insertAll(events);
    }

    public void nukeTable() {
        db.eventDao().nukeTable();
    }

    public void deleteEvent(Event event) {
        db.eventDao().delete(event);
    }
}
