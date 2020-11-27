package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel

interface TvShowRepository {
    suspend fun getTvShowsByPage(page: Int = 0): Resource<List<TvShowModel>>
    suspend fun getTvShowsByText(searchText: String): Resource<List<TvShowModel>>
}