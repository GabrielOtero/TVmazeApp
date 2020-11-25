package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.ShowModel

interface ShowRemoteDataSource {
    suspend fun getShowsByPage(page: Int = 1): Resource<List<ShowModel>>
}