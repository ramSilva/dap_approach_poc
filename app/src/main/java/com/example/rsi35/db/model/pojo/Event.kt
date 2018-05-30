package com.example.rsi35.db.model.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Event(key: String, value: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null

    @ColumnInfo(name = "key")
    var key = key

    @ColumnInfo(name = "value")
    var value = value
}
