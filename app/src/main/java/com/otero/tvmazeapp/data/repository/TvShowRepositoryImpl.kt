package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.datasource.TvShowLocalDataSource
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource
import com.otero.tvmazeapp.domain.model.TvShowModel
import io.reactivex.Observable

class TvShowRepositoryImpl(
    private val tvShowRemote: TvShowRemoteDataSource,
    private val tvShowLocal: TvShowLocalDataSource
) : TvShowRepository {
    override suspend fun getTvShowsByPage(page: Int) = tvShowRemote.getShowsByPage(page)
    override suspend fun getTvShowsByText(searchText: String) =
        tvShowRemote.getTvShowsByText(searchText)
    override suspend fun getTvShowById(id: Int) = tvShowRemote.getTvShowById(id)
    override suspend fun saveFavoriteTvShow(tvShowModel: TvShowModel) =
        tvShowLocal.insertTvShow(tvShowModel)
    override suspend fun getFavoriteTvShowById(id: Int) = tvShowLocal.getById(id)
    override suspend fun removeFavoriteTvShowById(id: Int) = tvShowLocal.removeTvShow(id)
    override suspend fun getAllFavoriteTvShow() = tvShowLocal.getAllTvShow()


    override suspend fun getTvShowsByPageRx(page: Int) = tvShowRemote.getShowsByPageRx(page)
}