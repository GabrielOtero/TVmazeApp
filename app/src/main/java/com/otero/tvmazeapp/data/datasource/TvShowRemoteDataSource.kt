package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel

interface TvShowRemoteDataSource {
    suspend fun getShowsByPage(page: Int = 1): Resource<List<TvShowModel>>
}