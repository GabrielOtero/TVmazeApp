package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.domain.model.TvShowModel

interface TvShowLocalDataSource {
    suspend fun insertTvShow(tvShowDbo: TvShowModel)
    suspend fun getById(id: Int) :TvShowModel?
    suspend fun removeTvShow(id: Int)
    fun getAllTvShow(): TvShowPagingDataSource
}