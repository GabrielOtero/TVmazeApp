package com.otero.tvmazeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otero.tvmazeapp.data.dao.TvShowDao
import com.otero.tvmazeapp.data.dbo.TvShowDbo

@Database(entities = arrayOf(TvShowDbo::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tvShowDao(): TvShowDao
}