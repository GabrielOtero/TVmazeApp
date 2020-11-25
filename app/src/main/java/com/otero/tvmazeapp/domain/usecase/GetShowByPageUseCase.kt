package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetShowByPageUseCase {
    suspend operator fun invoke(page: Int = 1): Resource<List<TvShowModel>>
}