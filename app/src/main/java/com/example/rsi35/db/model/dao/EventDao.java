package com.example.rsi35.db.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rsi35.db.model.pojo.Event;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Query("DELETE FROM event")
    void nukeTable();

    @Insert
    void insertAll(Event... events);

    @Delete
    void delete(Event event);
}
