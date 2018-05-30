package com.example.rsi35.db.dbstuff;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.rsi35.db.model.dao.EventDao;
import com.example.rsi35.db.model.pojo.Event;

@Database(entities = {Event.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
}
