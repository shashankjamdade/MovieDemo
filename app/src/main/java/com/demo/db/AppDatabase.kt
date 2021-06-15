package com.demo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.db.dao.MovieDao
import com.demo.model.response.MovieResultsItem

@Database(entities = [ MovieResultsItem::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}