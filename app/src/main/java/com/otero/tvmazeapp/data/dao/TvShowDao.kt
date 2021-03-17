package com.otero.tvmazeapp.data.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.otero.tvmazeapp.data.dbo.TvShowDbo

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshowdbo ORDER BY name")
    fun getAll(): List<TvShowDbo>

    @Query("SELECT * FROM tvshowdbo WHERE id == :id LIMIT 1")
    fun getById(id: Int): TvShowDbo?

    @Insert
    fun insert(vararg tvShow: TvShowDbo)

    @Query("DELETE FROM tvshowdbo WHERE id == :id ")
    fun delete(id: Int)
}