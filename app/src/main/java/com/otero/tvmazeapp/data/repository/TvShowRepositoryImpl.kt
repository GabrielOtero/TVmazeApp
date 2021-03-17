package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.datasource.TvShowLocalDataSource
import com.otero.tvmazeapp.data.datasource.TvShowPagingDataSource
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowRepositoryImpl(
    private val tvShowRemote: TvShowRemoteDataSource,
    private val tvShowLocal: TvShowLocalDataSource,
    private val pagingDataSource: TvShowPagingDataSource
) : TvShowRepository {
    override fun getTvShowsByPage() = pagingDataSource
    override suspend fun getTvShowsByText(searchText: String) =
        tvShowRemote.getTvShowsByText(searchText)
    override suspend fun getTvShowById(id: Int) = tvShowRemote.getTvShowById(id)
    override suspend fun saveFavoriteTvShow(tvShowModel: TvShowModel) =
        tvShowLocal.insertTvShow(tvShowModel)
    override suspend fun getFavoriteTvShowById(id: Int) = tvShowLocal.getById(id)
    override suspend fun removeFavoriteTvShowById(id: Int) = tvShowLocal.removeTvShow(id)
    override suspend fun getAllFavoriteTvShow() = tvShowLocal.getAllTvShow()
}