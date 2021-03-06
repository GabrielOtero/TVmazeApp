package com.otero.tvmazeapp.domain.usecase.interfaces

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.EpisodeBySeasonModel

interface GetEpisodeByShowUseCase {
    suspend operator fun invoke(id: Int): Resource<EpisodeBySeasonModel>
}