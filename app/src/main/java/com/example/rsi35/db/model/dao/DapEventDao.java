package com.example.rsi35.db.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rsi35.db.model.pojo.DapEvent;

import java.util.List;

@Dao
public interface DapEventDao {
    @Query("SELECT * FROM DapEvent")
    List<DapEvent> getAll();

    @Query("DELETE FROM DapEvent")
    void nukeTable();

    @Insert
    void insertAll(DapEvent... events);

    @Delete
    void delete(DapEvent event);
}
