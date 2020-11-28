package com.otero.tvmazeapp.data.repository

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.EpisodeModel

interface EpisodeRepository {
    suspend fun getTvEpisodesByShowId(id: Int): Resource<List<EpisodeModel>>
}