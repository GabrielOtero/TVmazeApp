package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.repository.TvShowRepository

class GetTvShowById(
        private val tvShowRepository: TvShowRepository
) : GetTvShowByIdUseCase {
    override suspend fun invoke(id: Int) = tvShowRepository.getTvShowById(id)
}