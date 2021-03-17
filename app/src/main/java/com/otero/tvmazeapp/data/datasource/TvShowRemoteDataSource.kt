package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.model.TvShowModel

interface TvShowRemoteDataSource {
    fun getPagedShows(): TvShowPagingDataSource
    suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>>
    suspend fun getTvShowById(id: Int): Resource<TvShowDetailModel>
}