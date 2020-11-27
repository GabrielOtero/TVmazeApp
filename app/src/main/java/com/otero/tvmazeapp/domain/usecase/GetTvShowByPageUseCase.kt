package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetTvShowByPageUseCase {
    suspend operator fun invoke(page: Int = 0): Resource<List<TvShowModel>>
}