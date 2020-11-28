package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.datasource.EpisodeRemoteDataSource

class EpisodeRepositoryImpl(
        private val episodeRemoteDataSource: EpisodeRemoteDataSource
) : EpisodeRepository {
    override suspend fun getTvEpisodesByShowId(id: Int) = episodeRemoteDataSource.getTvEpisodesByShowId(id)
}