package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.datasource.TvShowPagingDataSource
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.model.TvShowModel

interface TvShowRepository {
    fun getTvShowsByPage(): TvShowPagingDataSource
    suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>>
    suspend fun getTvShowById(id: Int): Resource<TvShowDetailModel>
    suspend fun saveFavoriteTvShow(tvShowModel: TvShowModel)
    suspend fun getFavoriteTvShowById(id: Int) : TvShowModel?
    suspend fun removeFavoriteTvShowById(id: Int)
    fun getAllFavoriteTvShow(): TvShowPagingDataSource
}