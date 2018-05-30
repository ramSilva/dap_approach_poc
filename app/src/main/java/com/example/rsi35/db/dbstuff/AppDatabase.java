package com.example.rsi35.db.dbstuff;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.rsi35.db.model.dao.DapEventDao;
import com.example.rsi35.db.model.pojo.DapEvent;

@Database(entities = {DapEvent.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DapEventDao eventDao();
}
