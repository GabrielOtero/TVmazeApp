package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.domain.model.TvShowModel

interface GetTvShowByTextUseCase {
    suspend operator fun invoke(searchText: String): Resource<List<TvShowModel>>
}
