package com.otero.tvmazeapp.data.datasource

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.EpisodeModel
import com.otero.tvmazeapp.domain.model.TvShowDetailModel
import com.otero.tvmazeapp.domain.model.TvShowModel

interface EpisodeRemoteDataSource {
    suspend fun getTvEpisodesByShowId(page: Int = 0): Resource<List<EpisodeModel>>
}